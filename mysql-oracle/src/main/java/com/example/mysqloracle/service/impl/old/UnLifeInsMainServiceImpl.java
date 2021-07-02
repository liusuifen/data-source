package com.example.mysqloracle.service.impl.old;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;

import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.old.UnHrDeptMapper;
import com.example.mysqloracle.dao.old.UnLifeInsMainMapper;
import com.example.mysqloracle.dao.old.UnLifeInsStateMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.entity.old.*;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.PartnerEnum;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.param.ProgressEnum;
import com.example.mysqloracle.param.RelationEnum;
import com.example.mysqloracle.param.StatusEnum;

import com.example.mysqloracle.service.eums.BasicEnumService;
import com.example.mysqloracle.service.news.LifePolicyService;
import com.example.mysqloracle.service.news.LifeProductService;
import com.example.mysqloracle.service.news.ProductCategoryService;
import com.example.mysqloracle.service.news.UserService;
import com.example.mysqloracle.service.old.*;
import com.example.mysqloracle.util.DateUtil;
import com.example.mysqloracle.util.IdUtil;
import com.example.mysqloracle.util.ProPertiesUtil;
import com.example.mysqloracle.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 寿险保单-投保单主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Service
@Slf4j
public class UnLifeInsMainServiceImpl extends ServiceImpl<UnLifeInsMainMapper, UnLifeInsMain> implements UnLifeInsMainService {

    @Autowired
    private UnLifeInsMainMapper unLifeInsMainMapper;

    @Autowired
    private UnLifeInsStateMapper unLifeInsStateMapper;

    @Autowired
    private Config config;


    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private LifePolicyMapper lifePolicyMapper;

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private LifePolicyProgressMapper lifePolicyProgressMapper;

    @Autowired
    private UnLifeInsStateService unLifeInsStateService;

    @Autowired
    private UnLifeInsContentService unLifeInsContentService;

    @Autowired
    private LifeProductService lifeProductService;

    @Autowired
    private BasicEnumService basicEnumService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private UnLifeInsInsuredService unLifeInsInsuredService;

    @Autowired
    private UnLifeInsBeneficiaryService unLifeInsBeneficiaryService;

    @Autowired
    private UnLifeProductAllService unLifeProductAllService;

    @Autowired
    private UnBasicDistrictService unBasicDistrictService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LifePolicyService lifePolicyService;

    @Autowired
    private LifeProductMapper lifeProductMapper;


    @Autowired
    private LifePolicyHolderMapper lifePolicyHolderMapper;

    @Autowired
    private LifePolicyInsuredMapper lifePolicyInsuredMapper;

    @Autowired
    private LifePolicyBeneficiaryMapper lifePolicyBeneficiaryMapper;

    @Autowired
    private LifePolicyProductMapper lifePolicyProductMapper;

    @Autowired
    private LifePolicyStatusMapper lifePolicyStatusMapper;

    @Autowired
    private LifePolicyDocumentMapper lifePolicyDocumentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private UnHrDeptMapper unHrDeptMapper;

    private static Integer lifeProcess_承保 = 15;//1:保单

    private static List<Integer> processResult = Arrays.asList(13, 16, 18, 19, 20, 21, 22);

    private static List<Integer> levelCode = Arrays.asList(1, 2, 3);

    /**
     * 获取老系统un_life_ins_main 表数据
     *
     * @return
     */
    @Override
    public CommonResult getAll(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnLifeInsMain> all=unLifeInsMainMapper.getAll(param.getChannelId(), DateUtil.strToInteger(param.getStartDate()), DateUtil.strToInteger(param.getEndDate()));
        for (UnLifeInsMain unLifeInsMain : all) {
            try {
                saveNewPartner(unLifeInsMain, param);
            } catch (Exception e) {
                insertMigrationLog(intToLong(unLifeInsMain.getId()), MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(), param);
            }
        }
        return new CommonResult("保单迁移数据迁移成功");
    }

    @Override
    public CommonResult getFail(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> errorPolicy = migrationLogMapper.getErrorPolicy(intToLong(param.getChannelId()));

        for (Integer policyId : errorPolicy) {
//            try {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
            UnLifeInsMain unLifeInsMain = unLifeInsMainMapper.getByPolicyId(policyId);
            if (ReflectUtil.isNotNull(unLifeInsMain)) {
                saveNewPartner(unLifeInsMain, param);
            }
//            } catch (Exception e) {
//                insertMigrationLog(intToLong(policyId),MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(),param);
//            }
        }
        return new CommonResult("错误迁移保单数据迁移成功");
    }

    @Override
    public CommonResult changeHesitateDate(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> successPolicy = migrationLogMapper.getSuccessPolicy(intToLong(param.getChannelId()));
        for (Integer policyId : successPolicy) {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifeVo lifeVo = lifePolicyMapper.selectHesitateDate(intToLong(policyId));
            LifePolicy lifePolicy = lifePolicyMapper.getByIds(intToLong(policyId));
            if(ReflectUtil.isNotNull(lifePolicy)){
                if(ReflectUtil.isNull(lifeVo)){
                    lifePolicy.setIsAfterHesitate(0);
                }else {
                    lifePolicy.setIsAfterHesitate(1);//有数据说明已过犹豫期
                }
                lifePolicyMapper.updateById(lifePolicy);
                log.info("是否已过犹豫期修改成功，保单号id{}",policyId);
            }
        }
        return new CommonResult("是否已过犹豫期修改完成");
    }

    @Override
    public CommonResult createCustomer(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> successful = migrationLogMapper.getSuccessPolicy(intToLong(param.getChannelId()));
        for (Integer policyId : successful) {
//            try {
                createCustomerData(policyId);
//            } catch (Exception e) {
//                log.info("根据保单和投保人生成客户数据失败，保单号id{}", policyId);
//            }
        }
        return new CommonResult("根据保单和投保人生成客户数据成功");
    }


    public void createCustomerData(Integer id) {
        LifePolicy lifePolicy = lifePolicyMapper.getByIds(intToLong(id));

        LifePolicyHolder lifePolicyHolders = lifePolicyHolderMapper.getById(intToLong(id));
        //用来收集获取身份证件号码
        List<String> insuredConcat = new ArrayList<>();

        List<LifePolicyInsured> lifePolicyInsureds = lifePolicyInsuredMapper.selectByPolicyId(intToLong(id));
        if (!CollectionUtils.isEmpty(lifePolicyInsureds)) {
            for (LifePolicyInsured lifePolicyInsured : lifePolicyInsureds) {
                insuredConcat.add(lifePolicyInsured.getIdConcat());
            }
        }
        /**
         * 投保人表处理逻辑
         */
        if (insuredConcat.contains(lifePolicyHolders.getIdConcat())) {
            Customer customer = new Customer();
            customer.setCustomerType("[1,2]");//被保人&&投保人
            insertCustomerHolders(id, customer, lifePolicyHolders, lifePolicy.getSalesUserId());
        } else {
            Customer customer = new Customer();
            customer.setCustomerType("[1]");//投保人
            insertCustomerHolders(id, customer, lifePolicyHolders, lifePolicy.getSalesUserId());
        }
        /**
         * 被保人处理逻辑
         */
        for (LifePolicyInsured lifePolicyInsured : lifePolicyInsureds) {
            if (lifePolicyInsured.getIdConcat().equals(lifePolicyHolders.getIdConcat())) {//同一个人，即是投保人也是被保人
            } else {
                Customer customer = new Customer();
                customer.setCustomerType("[2]");//被保人
                insertCustomerInsured(id, customer, lifePolicyInsured, lifePolicy.getSalesUserId());
            }
        }

    }

    public void insertCustomerHolders(Integer id, Customer customer, LifePolicyHolder lifePolicyHolders, Long userId) {
        customer.setId(IdUtil.generateId());
        customer.setName(lifePolicyHolders.getName());
        customer.setCertificateType(lifePolicyHolders.getIdType());
        customer.setCertificateNumber(lifePolicyHolders.getIdNo());
        customer.setCertificateConcat(lifePolicyHolders.getIdConcat());
        customer.setSex(lifePolicyHolders.getGender());
        customer.setBirthday(lifePolicyHolders.getBirthday());
        customer.setMobile(lifePolicyHolders.getMobile());
        customer.setProvinceCode(lifePolicyHolders.getProvince());
        customer.setCityCode(lifePolicyHolders.getCity());
        customer.setDistrictCode(lifePolicyHolders.getDistrict());
        customer.setAddress(lifePolicyHolders.getAddress());
        customer.setEmail(lifePolicyHolders.getEmail());
//            customer.setRemark(lifePolicyHolders.get);//默认值
        LifePolicyProgress lifePolicyProgress = lifePolicyProgressMapper.selectByPolicyIdAndProcess(intToLong(id), lifeProcess_承保);
        if (ReflectUtil.isNotNull(lifePolicyProgress)) {
            customer.setType(1);//承保客户
        } else {
            customer.setType(2);//普通客户
        }
        customer.setUserId(userId);
        customer.setIsDeleted(0);//未删除
        Integer num = customerMapper.selectIdTypeNo(customer.getCertificateType(), customer.getCertificateNumber(), customer.getUserId());
        if (num == 0) {
            customerMapper.insert(customer);
            log.info("生成客户数据成功，客户id{}", customer.getId());
        } else {
            log.info("该客户数据已生成过，客户id{}，忽略", customer.getId());
        }
    }

    public void insertCustomerInsured(Integer id, Customer customer, LifePolicyInsured lifePolicyInsured, Long userId) {
        customer.setId(IdUtil.generateId());
        customer.setName(lifePolicyInsured.getName());
        customer.setCertificateType(lifePolicyInsured.getIdType());
        customer.setCertificateNumber(lifePolicyInsured.getIdNo());
        customer.setCertificateConcat(lifePolicyInsured.getIdConcat());
        customer.setSex(lifePolicyInsured.getGender());
        customer.setBirthday(lifePolicyInsured.getBirthday());
        customer.setMobile(lifePolicyInsured.getMobile());
        customer.setProvinceCode(lifePolicyInsured.getProvince());
        customer.setCityCode(lifePolicyInsured.getCity());
        customer.setDistrictCode(lifePolicyInsured.getDistrict());
        customer.setAddress(lifePolicyInsured.getAddress());
        customer.setEmail(lifePolicyInsured.getEmail());
//            customer.setRemark(lifePolicyHolders.get);//默认值
        LifePolicyProgress lifePolicyProgress = lifePolicyProgressMapper.selectByPolicyIdAndProcess(intToLong(id), lifeProcess_承保);
        if (ReflectUtil.isNotNull(lifePolicyProgress)) {
            customer.setType(1);//承保客户
        } else {
            customer.setType(2);//普通客户
        }
        customer.setUserId(userId);
        customer.setIsDeleted(0);//未删除
        Integer num = customerMapper.selectIdTypeNo(customer.getCertificateType(), customer.getCertificateNumber(), customer.getUserId());
        if (num == 0) {
            customerMapper.insert(customer);
            log.info("生成客户数据成功，客户id{}", customer.getId());
        } else {
            log.info("该客户数据已生成过，客户id{}，忽略", customer.getId());
        }
    }


    @Override
    public CommonResult getTaxType() {
        Set<String> set = new HashSet<>();
        List<String> extendItems = unLifeInsMainMapper.getExtendItems();
        for (String item : extendItems) {
            if (item != null && !"".equals(item) && item.indexOf("isTaxResidents") != -1) {
                JSONObject json = JSONObject.parseObject(item);
                if (json != null && json.get("isTaxResidents") != null) {
                    String payDate = json.get("isTaxResidents").toString();
                    if (payDate != null && !"".equals(payDate)) {
                        set.add(payDate);
                    }
                }
            }
        }
        return new CommonResult(set);
    }

    /**
     * 把数据保存入新系统合作方数据库中
     * 枚举需要特殊处理
     */
    public void saveNewPartner(UnLifeInsMain unLifeInsMain, Param param) {

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        UnLifeInsState progress = unLifeInsStateMapper.getByPolicyIdAndProgress(unLifeInsMain.getId(), 2);

        Long oldId = intToLong(unLifeInsMain.getId());
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        LifePolicy lifePolicy = lifePolicyService.getById(oldId);
        if (ReflectUtil.isNull(lifePolicy)) {
            LifePolicy policy = new LifePolicy();
            policy.setId(oldId);
//            policy.setSourcePartnerId(PartnerEnum.getPartnerIdByChannelId(unLifeInsMain.getChannelId()));
            policy.setSourcePartnerId(0L);
            policy.setOrderNo(unLifeInsMain.getOrderNo());
            policy.setPolicyNo(unLifeInsMain.getPolicyNo());
            policy.setPolicySn(unLifeInsMain.getPolicySn());
            policy.setCompany(intToLong(unLifeInsMain.getSupplierId()));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Company company = companyMapper.getById(intToLong(unLifeInsMain.getSupplierId()));
            if (ReflectUtil.isNotNull(company)) {
                policy.setCompanyType(company.getCompanyType());
            }
            policy.setProgress(ProgressEnum.getNewByCode(unLifeInsMain.getProgress()));
            policy.setPeriod(5);//默认为5
            policy.setStatus(StatusEnum.getNewByCode(unLifeInsMain.getState()));//枚举类型不一致
            policy.setIsLegalBeneficiary(unLifeInsMain.getIsLegalBenefic());
            policy.setRemark(unLifeInsMain.getRemark());
            if (unLifeInsMain.getValDate() != null && !"".equals(unLifeInsMain.getValDate()) && unLifeInsMain.getValDate().length() == 10) {
                policy.setDateStart(LocalDate.parse(unLifeInsMain.getValDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }else if(unLifeInsMain.getValDate() != null && !"".equals(unLifeInsMain.getValDate()) && unLifeInsMain.getValDate().length() == 9){
                String date=unLifeInsMain.getValDate();
                date=date.substring(0,5)+"0"+date.substring(5,9);
                policy.setDateStart(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }else{
                if(ReflectUtil.isNotNull(progress)&&progress.getFileTime()!=0){
                    policy.setDateStart(DateUtil.convertTimeToLocalDate(progress.getFileTime()).plusDays(1));
                }
            }
//            policy.setDateEnd();不处理
            if (unLifeInsMain.getExtendItems() != null &&
                    !"[]".equals(unLifeInsMain.getExtendItems()) &&
                    unLifeInsMain.getExtendItems().indexOf("pay_date") != -1) {
                JSONObject json = JSONObject.parseObject(unLifeInsMain.getExtendItems());
                if (json != null && json.get("pay_date") != null) {
                    String payDate = json.get("pay_date").toString();
                    if (payDate != null && !"".equals(payDate) && payDate.length() == 10) {
                        policy.setDateDeduct(DateUtil.strToLocalDate(payDate));
                    }
                }
            }
            /**
             * 状态表(可能存在多条数据)
             */
            Integer oldstate = unLifeInsMain.getState();
            LifePolicyStatus lifePolicyStatus = new LifePolicyStatus();
            lifePolicyStatus.setId(IdUtil.generateId());
            lifePolicyStatus.setLifePolicyId(oldId);
            lifePolicyStatus.setRemark("");
            lifePolicyStatus.setStatus(StatusEnum.getNewByCode(oldstate));
            if (oldstate == 0) {//老系统保单创建日期
                lifePolicyStatus.setDate(DateUtil.convertTimeToLocalDate(intToLong(unLifeInsMain.getCreateTime())));
            } else if (oldstate == 1 || oldstate == 7 || oldstate == 10) {//老系统保单生效日期
                if (unLifeInsMain.getValDate() != null &&
                        !"".equals(unLifeInsMain.getValDate()) &&
                        unLifeInsMain.getValDate().length() == 10) {
                    lifePolicyStatus.setDate(DateUtil.strToLocalDate(unLifeInsMain.getValDate()));
                }
            } else {//老系统保单修改日期
                lifePolicyStatus.setDate(DateUtil.convertTimeToLocalDate(intToLong(unLifeInsMain.getModifyTime())));
            }
            if (lifePolicyStatus.getDate() != null && !"".equals(lifePolicyStatus.getDate())) {
                if(lifePolicyStatus.getDate().isEqual(DateUtil.strToLocalDate("1970-01-01"))){

                }else {
                    lifePolicyStatus.setCreatedAt(DateUtil.localDateToLocalDateTime(lifePolicyStatus.getDate()));
                }

            }
            lifePolicyStatus.setUpdateUser("{}");//默认为空
            lifePolicyStatus.setUpdateUserId(0L);//默认为空
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifePolicyStatus status = lifePolicyStatusMapper.getByPolicyIdAndStatus(oldId, StatusEnum.getNewByCode(oldstate));
            if (ReflectUtil.isNull(status)) {
                lifePolicyStatusMapper.insert(lifePolicyStatus);
                log.info("老系统保单号:{},保单状态表迁移成功", oldId);
            } else {
                log.info("老系统保单号:{},保单状态表已迁移过，忽略", oldId);
            }

            /**
             * 文档
             */
            if (!"".equals(unLifeInsMain.getImgJson()) &&
                    unLifeInsMain.getImgJson() != null &&
                    unLifeInsMain.getImgJson().indexOf("customer_notification") != -1) {
                JSONObject imgJson = JSONObject.parseObject(unLifeInsMain.getImgJson());
                //老系统主表img_json的"customer_notification"，包含则已签
                if (imgJson != null && !"".equals(imgJson)) {
                    if (imgJson.containsKey("customer_notification")) {
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer document = lifePolicyDocumentMapper.getByPolicyIdAndType(oldId);
                        if (document == 0) {
                            String context = imgJson.get("customer_notification").toString();
                            String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
                            String url = value + context;
                            LifePolicyDocument lifePolicyDocument = new LifePolicyDocument();
                            lifePolicyDocument.setId(IdUtil.generateId());
                            lifePolicyDocument.setLifePolicyId(oldId);
                            lifePolicyDocument.setName("客户告知书");
                            lifePolicyDocument.setUrl(url);
//                            lifePolicyDocument.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getCreateTime())));
                            lifePolicyDocument.setCreatedAt(getUploadTime(context));
                            lifePolicyDocument.setType("106");//客户告知书状态
                            lifePolicyDocument.setIsDeleted(0);
                            lifePolicyDocumentMapper.insert(lifePolicyDocument);
                            log.info("老系统保单号:{},保单文档迁移成功", oldId);
                        } else {
                            log.info("老系统保单号:{},保单文档已迁移过，忽略", oldId);
                        }
                    }
                }
            } else {
                log.info("老系统保单号:{},保单文档imgJson不存在客户告知书，忽略", oldId);
            }

            //节点是15承保
            LifePolicyProgress policyProgress_15 = new LifePolicyProgress();
            //节点是12预收
            LifePolicyProgress policyProgress_12 = new LifePolicyProgress();
            //节点是16回执
            LifePolicyProgress policyProgress_16 = new LifePolicyProgress();
            /**
             * 进度表
             */
            List<UnLifeInsState> insStateList = unLifeInsStateService.getByPolicyId(unLifeInsMain.getId());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Integer progressNum = lifePolicyProgressMapper.getCountByPolicyId(oldId);
            if (!CollectionUtils.isEmpty(insStateList)) {
                for (UnLifeInsState unLifeInsState : insStateList) {
                    LifePolicyProgress policyProgress = new LifePolicyProgress();
                    policyProgress.setId(intToLong(unLifeInsState.getId()));
//                        policyProgress.setId(IdUtil.generateId());
                    policyProgress.setLifePolicyId(intToLong(unLifeInsState.getPolicyId()));
                    policyProgress.setProgress(ProgressEnum.getNewByCode(strToInteger(unLifeInsState.getProgress())));
                    policyProgress.setResult(1);//默认成功
                    policyProgress.setDate(DateUtil.convertTimeToLocalDate(unLifeInsState.getFileTime()));
                    String oldPicture = unLifeInsState.getPicture();
                    String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
                    if (oldPicture != null && !"".equals(oldPicture)) {
//                        String[] split = oldPicture.split(",");
//                        String firstLetter = Character.toString(oldPicture.charAt(0));
//                        if ("".equals(firstLetter)) {//以,开头
//                            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
//                            List<String> convert = Convert.convert(List.class, split);
//                            String file="";
//                            for (String s : convert) {
//                                file=value+s+"|";
//                            }
//                            policyProgress.setFile(file);
//                            policyProgress.setFile(value+split[1]);
//                        } else {
//                            policyProgress.setFile(value+split[0]);
//                        }
                        String pic = updateOldpicture(unLifeInsState.getPicture());
                        policyProgress.setFile(pic);
//                        policyProgress.setFileUrls("{" + oldPicture + "}");
                    } else {
                        policyProgress.setFile("");
//                        policyProgress.setFileUrls("{}");
                    }
//                    policyProgress.setRemark("");//默认为空字符串
                    policyProgress.setUpdateUser("{}");//默认为空字符串
                    if(unLifeInsState.getCreateTime()==0){
                        /**
                         * 如果创建时间为0,则取该保单得承保得日期，再则取默认时间:2019-01-01 00:00:00
                         */
                        if(progress!=null&&progress.getFileTime()!=0){
                            policyProgress.setCreatedAt(DateUtil.convertTimeToLocalDateTime(progress.getFileTime()));
                        }else {
                            policyProgress.setCreatedAt(DateUtil.convertTimeToLocalDateTime(1546272000L));
                        }
                    }else {
                        policyProgress.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsState.getCreateTime())));
                    }
                    if (policyProgress.getProgress() != 0 && policyProgress.getProgress() == 15) {
                        policyProgress_15 = policyProgress;
                    }
                    if (policyProgress.getProgress() != 0 && policyProgress.getProgress() == 12) {
                        policyProgress_12 = policyProgress;
                    }
                    if (policyProgress.getProgress() != 0 && policyProgress.getProgress() == 16) {
                        policyProgress_16 = policyProgress;
                    }
//                    policyProgress.setUpdateUserId("");//默认为空字符串
                    if (progressNum != insStateList.size()) {
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer num = lifePolicyProgressMapper.getCountByPolicyIdAndProgress(intToLong(unLifeInsState.getPolicyId()),ProgressEnum.getNewByCode(strToInteger(unLifeInsState.getProgress())));
//                        Integer num = lifePolicyProgressMapper.getCountById(intToLong(unLifeInsState.getId()));
                        if (num == 0) {
                            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                            lifePolicyProgressMapper.insert(policyProgress);
                            log.info("老系统保单号:{},保单进度id{}，数据迁移成功", oldId, unLifeInsState.getId());
                        } else {
                            log.info("老系统保单号:{},保单进度id{}，数据迁移已迁移过，忽略", oldId, unLifeInsState.getId());
                        }
                    } else {
                        log.info("老系统保单号:{},保单进度表[life_policy_progress]已全部迁移过，忽略", oldId);
                    }
                }
            } else {
                log.info("老系统保单号:{},保单进度表[un_life_ins_state]无数据迁移，忽略", oldId);
            }
            //记录主险对象
            LifePolicyProduct mainProduct = new LifePolicyProduct();
            //记录保费总合
            BigDecimal stdFeeSum = new BigDecimal(0);
            BigDecimal amountSum = new BigDecimal(0);
            /**
             * 根据进度节点数据生成状态记录
             */
            if (ReflectUtil.isNotNull(policyProgress_15)) {
                LifePolicyStatus status1 = new LifePolicyStatus();
                status1.setId(IdUtil.generateId());
                status1.setLifePolicyId(oldId);
                status1.setRemark("");
                status1.setStatus(3);
                status1.setUpdateUser("{}");
                status1.setDate(policyProgress_15.getDate());
                if (status1.getDate() != null && !"".equals(status1.getDate())) {
                    status1.setCreatedAt(DateUtil.localDateToLocalDateTime(status1.getDate()));
                }
                lifePolicyStatus.setUpdateUser("{}");//默认为空
                lifePolicyStatus.setUpdateUserId(0L);//默认为空
                LifePolicyStatus oldstatus = lifePolicyStatusMapper.getByPolicyIdAndStatus(oldId, 3);
                if (ReflectUtil.isNull(oldstatus)) {
                    lifePolicyStatusMapper.insert(status1);
                    log.info("老系统保单号:{},保单状态表迁移成功", oldId);
                } else {
                    log.info("老系统保单号:{},保单状态表已迁移过，忽略", oldId);
                }
            }
            /**
             * 产品表
             */
            List<UnLifeInsContent> insContentList = unLifeInsContentService.getByPolicyId(unLifeInsMain.getId());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            //防止重复迁移
            Integer productNum = lifePolicyProductMapper.getCountByPolicyId(oldId);
            if (!CollectionUtils.isEmpty(insContentList)) {
                for (UnLifeInsContent unLifeInsContent : insContentList) {
                    /**
                     * 记录主险产品
                     */
                    if (unLifeInsContent.getIsMain() == 1) {
                        mainProduct.setId(intToLong(unLifeInsContent.getId()));
//                            mainProduct.setId(IdUtil.generateId());
                        mainProduct.setLifeProductId(getNewProductId(unLifeInsContent.getProductId()));
                        mainProduct.setLifePolicyId(intToLong(unLifeInsContent.getPolicyId()));
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        LifeProduct product1 = lifeProductService.getByPolicyId(mainProduct.getLifeProductId());
                        if (ReflectUtil.isNotNull(product1)) {
                            mainProduct.setName(product1.getName());
                            mainProduct.setCode(product1.getCode());
                            mainProduct.setCategoryId(product1.getCategoryId());
                            ProductCategory category1 = productCategoryService.getByPolicyId(intToLong(product1.getCategoryId()));
                            if (ReflectUtil.isNotNull(category1)) {
                                mainProduct.setCategoryName(category1.getName());
                            }
                        }
                        mainProduct.setAmount(unLifeInsContent.getAmount());
                        mainProduct.setSafeYear(unLifeInsContent.getPeriodGuanantee());
                        if (unLifeInsContent.getPeriodGuanantee() != null) {
                            if (unLifeInsContent.getPeriodGuanantee() >= 50) {
                                mainProduct.setSafeYearUnit(2);//岁
                            } else {
                                mainProduct.setSafeYearUnit(1);//年
                            }
                        }
                        mainProduct.setPayYear(unLifeInsContent.getPeriodPayment());
                        if (unLifeInsContent.getPeriodPayment() != null) {
                            if (unLifeInsContent.getPeriodPayment() >= 50) {
                                mainProduct.setPayYearUnit(2);//岁
                            } else {
                                mainProduct.setPayYearUnit(1);//年
                            }
                        }
                        //缴费期间1填1，其他情况填2
                        if (unLifeInsContent.getPeriodPayment() == 1) {
                            mainProduct.setPayWay(1);
                        } else {
                            mainProduct.setPayWay(2);
                        }
                        mainProduct.setPayFrequency(1);//默认为1
                        mainProduct.setFee(unLifeInsContent.getFee());
                        mainProduct.setStdFee(unLifeInsContent.getStdPremium());
                        mainProduct.setRatio(unLifeInsContent.getRatio());
                        mainProduct.setIsPrimary(unLifeInsContent.getIsMain());
                        mainProduct.setIsDeleted(0);//默认为0
//                        lifePolicyProduct.setExtension();//默认为空
                        mainProduct.setHesitateTime(15);//默认为15
                        mainProduct.setHesitateUnit(2);//默认为2
                        mainProduct.setCommissionWay(2);//默认为2
                        mainProduct.setCommissionWayRenewal(2);//默认为2
                    }
                    if (productNum != insContentList.size()) {
                        /**
                         * 产品数据同步
                         */
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());

                        LifePolicyProduct lifePolicyProduct = new LifePolicyProduct();
                        lifePolicyProduct.setId(intToLong(unLifeInsContent.getId()));
                        lifePolicyProduct.setLifePolicyId(intToLong(unLifeInsContent.getPolicyId()));
                        lifePolicyProduct.setLifeProductId(getNewProductId(unLifeInsContent.getProductId()));
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        LifeProduct product = lifeProductService.getByPolicyId(lifePolicyProduct.getLifeProductId());
                        if (ReflectUtil.isNotNull(product)) {
                            lifePolicyProduct.setName(product.getName());
                            lifePolicyProduct.setCode(product.getCode());
                            lifePolicyProduct.setCategoryId(product.getCategoryId());
                            lifePolicyProduct.setHesitateTime(product.getHesitateTime());
                            lifePolicyProduct.setHesitateUnit(product.getHesitateUnit());
                            lifePolicyProduct.setCommissionWay(product.getCommissionWay());
                            lifePolicyProduct.setCommissionWayRenewal(product.getCommissionWayRenewal());
                            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                            ProductCategory category = productCategoryService.getByPolicyId(intToLong(lifePolicyProduct.getCategoryId()));
                            if (ReflectUtil.isNotNull(category)) {
                                lifePolicyProduct.setCategoryName(category.getName());
                            }
                        }
                        lifePolicyProduct.setAmount(unLifeInsContent.getAmount());
                        lifePolicyProduct.setSafeYear(unLifeInsContent.getPeriodGuanantee());
                        if (unLifeInsContent.getPeriodGuanantee() != null) {
                            if (unLifeInsContent.getPeriodGuanantee() >= 50) {
                                lifePolicyProduct.setSafeYearUnit(2);//岁
                            } else {
                                lifePolicyProduct.setSafeYearUnit(1);//年
                            }
                        }
                        if(unLifeInsContent.getPeriodPayment()>100){
                            lifePolicyProduct.setPayYear(1);
                            lifePolicyProduct.setPayYearUnit(1);//年
                        }else {
                            lifePolicyProduct.setPayYear(unLifeInsContent.getPeriodPayment());
                            if (unLifeInsContent.getPeriodPayment() != null) {
                                if (unLifeInsContent.getPeriodPayment() >= 50) {
                                    lifePolicyProduct.setPayYearUnit(2);//岁
                                } else {
                                    lifePolicyProduct.setPayYearUnit(1);//年
                                }
                            }
                        }

                        //缴费期间1填1，其他情况填2
                        if (unLifeInsContent.getPeriodPayment() == 1) {
                            lifePolicyProduct.setPayWay(1);
                        } else {
                            lifePolicyProduct.setPayWay(2);
                        }
                        lifePolicyProduct.setPayFrequency(1);//默认为1
                        lifePolicyProduct.setFee(unLifeInsContent.getFee());
                        lifePolicyProduct.setStdFee(unLifeInsContent.getStdPremium());
                        lifePolicyProduct.setCompanyStdFee(unLifeInsContent.getStdPremium());
                        stdFeeSum = stdFeeSum.add(unLifeInsContent.getStdPremium());
                        amountSum = amountSum.add(unLifeInsContent.getAmount());
                        lifePolicyProduct.setRatio(unLifeInsContent.getRatio());
                        lifePolicyProduct.setIsPrimary(unLifeInsContent.getIsMain());
                        lifePolicyProduct.setIsDeleted(0);//默认为0
//                        lifePolicyProduct.setExtension();//默认为空
                        lifePolicyProduct.setHesitateTime(15);//默认为15
                        lifePolicyProduct.setHesitateUnit(2);//默认为2
                        lifePolicyProduct.setCommissionWay(2);//默认为2
                        lifePolicyProduct.setCommissionWayRenewal(2);//默认为2
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer num = lifePolicyProductMapper.getCountById(intToLong(unLifeInsContent.getId()));
                        if (num == 0) {
                            lifePolicyProductMapper.insert(lifePolicyProduct);
                            log.info("老系统保单号:{},保单相关产品id{}数据迁移成功", oldId, unLifeInsContent.getId());
                        } else {
                            log.info("老系统保单号:{},保单相关产品id{}数据迁移已迁移过，忽略", oldId, unLifeInsContent.getId());
                        }
                    } else {
                        log.info("老系统保单号:{},保单产品表[life_policy_product]数据迁移已迁移过，忽略", oldId);
                    }
                }
            } else {
                log.info("老系统保单号:{},保单产品表[un_life_ins_content]无数据，忽略", oldId);
            }

            /**
             * 收益人
             */
            List<UnLifeInsBeneficiary> beneficiaryList = unLifeInsBeneficiaryService.getByPolicyId(unLifeInsMain.getId());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Integer beneficaryNum = lifePolicyBeneficiaryMapper.getCountBypolicyId(oldId);
            if (!CollectionUtils.isEmpty(beneficiaryList)) {
                if (beneficaryNum != beneficiaryList.size()) {
                    for (UnLifeInsBeneficiary beneficiary : beneficiaryList) {
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        LifePolicyBeneficiary lifePolicyBeneficiary = new LifePolicyBeneficiary();
                        lifePolicyBeneficiary.setId(intToLong(beneficiary.getId()));
                        lifePolicyBeneficiary.setLifePolicyId(intToLong(beneficiary.getPolicyId()));
                        lifePolicyBeneficiary.setName(beneficiary.getFullname());
                        lifePolicyBeneficiary.setGender(strToInteger(basicEnumService.getNewEnum(beneficiary.getGender())));
                        String idType = basicEnumService.getNewEnum(beneficiary.getIdType());
                        if (beneficiary.getBirthday() != null &&
                                !"".equals(beneficiary.getBirthday()) &&
                                beneficiary.getBirthday().length() == 10) {
                            lifePolicyBeneficiary.setBirthday(DateUtil.strToLocalDate(beneficiary.getBirthday()));
                        } else if ("1".equals(idType) && beneficiary.getIdNo().length() == 18) {
                            lifePolicyBeneficiary.setBirthday(DateUtil.strToLocalDate(IdToBirthday(beneficiary.getIdNo())));
                        }
                        lifePolicyBeneficiary.setIdType(strToInteger(idType));
                        lifePolicyBeneficiary.setIdNo(beneficiary.getIdNo());
                        lifePolicyBeneficiary.setIdConcat(lifePolicyBeneficiary.getIdType() + lifePolicyBeneficiary.getIdNo());
                        if (beneficiary.getIdExpireEnd() != null &&
                                !"".equals(beneficiary.getIdExpireEnd()) &&
                                beneficiary.getIdExpireEnd().length() == 10) {
                            if(!beneficiary.getIdExpireEnd().equals("0000-00-00")){
                                lifePolicyBeneficiary.setIdExpiredDate(DateUtil.strToLocalDate(beneficiary.getIdExpireEnd()));
                            }
                        }
//                    lifePolicyBeneficiary.setEmail();//默认为空
                        lifePolicyBeneficiary.setMobile(beneficiary.getTel());
                        lifePolicyBeneficiary.setRelation(strToInteger(basicEnumService.getNewEnum(beneficiary.getBeneficiaryIsInsured())));
                        lifePolicyBeneficiary.setRelationName(RelationEnum.getNameByCode(lifePolicyBeneficiary.getRelation()));
                        lifePolicyBeneficiary.setRank(beneficiary.getSortOrder());
                        lifePolicyBeneficiary.setRatio(beneficiary.getRate());
                        lifePolicyBeneficiary.setProvince(strToInteger(unBasicDistrictService.getCodeById(beneficiary.getProvince())));
                        lifePolicyBeneficiary.setCity(strToInteger(unBasicDistrictService.getCodeById(beneficiary.getCity())));
                        lifePolicyBeneficiary.setDistrict(strToInteger(unBasicDistrictService.getCodeById(beneficiary.getDistrict())));
                        lifePolicyBeneficiary.setAddress(beneficiary.getAddress());
                        lifePolicyBeneficiary.setType(strToInteger(basicEnumService.getNewEnum(beneficiary.getType())));
                        lifePolicyBeneficiary.setIsDeleted(0);//默认为0
//                    lifePolicyBeneficiary.setHeight();//默认为空
//                    lifePolicyBeneficiary.setWeight();//默认为空
                        lifePolicyBeneficiary.setNationality(strToInteger(basicEnumService.getNewEnum(beneficiary.getNation())));
                        lifePolicyBeneficiary.setZip(beneficiary.getZip());
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer num = lifePolicyBeneficiaryMapper.getCountById(intToLong(beneficiary.getId()));
                        if (num == 0) {
                            lifePolicyBeneficiaryMapper.insert(lifePolicyBeneficiary);
                            log.info("老系统保单号:{},保单受益人id{}数据迁移迁成功", oldId, beneficiary.getId());
                        } else {
                            log.info("老系统保单号:{},保单受益人id{}数据迁移已迁移过，忽略", oldId, beneficiary.getId());
                        }
                    }
                } else {
                    log.info("老系统保单号:{},保单受益人表[life_policy_beneficiary]数据迁移已迁移过，忽略", oldId);
                }
            } else {
                log.info("老系统保单号:{},保单受益人表[un_life_ins_beneficiary]无数据，忽略", oldId);
            }

            /**
             * 被保险人
             */
            List<UnLifeInsInsured> insInsuredList = unLifeInsInsuredService.getByPolicyId(unLifeInsMain.getId());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Integer insuredNum = lifePolicyInsuredMapper.getCountBypolicyId(oldId);
            if (!CollectionUtils.isEmpty(insInsuredList)) {
                if (insuredNum != insInsuredList.size()) {
                    for (UnLifeInsInsured unLifeInsInsured : insInsuredList) {
                        LifePolicyInsured lifePolicyInsured = new LifePolicyInsured();
                        lifePolicyInsured.setId(intToLong(unLifeInsInsured.getId()));
//                        lifePolicyInsured.setId(IdUtil.generateId());
                        lifePolicyInsured.setLifePolicyId(intToLong(unLifeInsInsured.getPolicyId()));
                        lifePolicyInsured.setName(unLifeInsInsured.getInsuredName());
                        lifePolicyInsured.setGender(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredGender())));
                        if (unLifeInsInsured.getInsuredBirthday() != null &&
                                !"".equals(unLifeInsInsured.getInsuredBirthday()) &&
                                unLifeInsInsured.getInsuredBirthday().length() == 10) {
                            if(!unLifeInsInsured.getInsuredBirthday().equals("0000-11-30")){
                                lifePolicyInsured.setBirthday(DateUtil.strToLocalDate(unLifeInsInsured.getInsuredBirthday()));
                            }
                        }
                        lifePolicyInsured.setRelation(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getRelInsuredHolder())));
                        lifePolicyInsured.setRelationName(RelationEnum.getNameByCode(lifePolicyInsured.getRelation()));
                        lifePolicyInsured.setIdType(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredIdType())));
                        lifePolicyInsured.setIdNo(unLifeInsInsured.getInsuredIdNo());
                        lifePolicyInsured.setIdConcat(lifePolicyInsured.getIdType() + lifePolicyInsured.getIdNo());
                        if (unLifeInsInsured.getInsuredIdExpireEnd() != null &&
                                !"".equals(unLifeInsInsured.getInsuredIdExpireEnd()) &&
                                unLifeInsInsured.getInsuredIdExpireEnd().length() == 10) {
                            if(unLifeInsInsured.getInsuredIdExpireEnd().equals("0000-00-00")){
//                                lifePolicyInsured.setIdExpiredDate(DateUtil.strToLocalDate("9999-12-31"));
                            }else {
                                lifePolicyInsured.setIdExpiredDate(DateUtil.strToLocalDate(unLifeInsInsured.getInsuredIdExpireEnd()));
                            }
                        }
                        lifePolicyInsured.setHeight(unLifeInsInsured.getInsuredHeight().intValue());
                        lifePolicyInsured.setWeight(unLifeInsInsured.getInsuredWeight());
                        lifePolicyInsured.setMarriage(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredMarriage())));
                        lifePolicyInsured.setMobile(unLifeInsInsured.getInsuredMobile());
                        lifePolicyInsured.setEmail(unLifeInsInsured.getInsuredEmail());
                        String[] hasErro = {"LAB0004", "LAB0016"};
                        List<String> erro = Arrays.asList(hasErro);
                        if (!erro.contains(unLifeInsInsured.getHasInsuredSsid())) {
                            lifePolicyInsured.setIsSsid(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getHasInsuredSsid())));
                        }
                        lifePolicyInsured.setAddress(unLifeInsInsured.getInsuredContactAddress());
                        lifePolicyInsured.setProvince(strToInteger(unBasicDistrictService.getCodeById(unLifeInsInsured.getInsuredContactProvince())));
                        lifePolicyInsured.setCity(strToInteger(unBasicDistrictService.getCodeById(unLifeInsInsured.getInsuredContactCity())));
                        lifePolicyInsured.setDistrict(strToInteger(unBasicDistrictService.getCodeById(unLifeInsInsured.getInsuredContactDistrict())));
                        lifePolicyInsured.setCompany(unLifeInsInsured.getInsuredCompany());
                        lifePolicyInsured.setIncome(unLifeInsInsured.getInsuredSalaryAvg());
                        lifePolicyInsured.setIncomeSource(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredSalaryFrom())));
                        lifePolicyInsured.setIsDeleted(0);//默认为0
                        lifePolicyInsured.setNationality(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredNation())));
//                    lifePolicyInsured.setIsTax();//默认为空
                        lifePolicyInsured.setZip(unLifeInsInsured.getInsuredContactZip());
//                    lifePolicyInsured.setExtension();//默认为空
//                            lifePolicyInsured.setTaxType();//默认为空
                        if (!"".equals(unLifeInsInsured.getInsuredJobCode())) {
                            lifePolicyInsured.setJobCode(unLifeInsInsured.getInsuredJobCode());
                        } else {
                            lifePolicyInsured.setJobCode(null);
                        }
                        //json
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer num = lifePolicyInsuredMapper.getCountById(intToLong(unLifeInsInsured.getId()));
                        if (num == 0) {
                            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                            lifePolicyInsuredMapper.insert(lifePolicyInsured);
                            log.info("老系统保单号:{},保单被保险人id{}数据迁移迁移成功", oldId, unLifeInsInsured.getId());
                        } else {
                            log.info("老系统保单号:{},保单被保险人id{}数据迁移已迁移过，忽略", oldId, unLifeInsInsured.getId());
                        }
                    }
                } else {
                    log.info("老系统保单号:{},保单被保险人表[life_policy_insured]数据迁移已迁移过，忽略", oldId);
                }
            } else {
                log.info("老系统保单号:{},保单被保险人表[un_life_ins_insured]无数据，忽略", oldId);
            }
            if (policyProgress_15.getDate() != null) {
                policy.setDateAccept(policyProgress_15.getDate());
            }
            if (policyProgress_12.getDate() != null) {
                policy.setDateAdvance(policyProgress_12.getDate());
            }
            policy.setIsSaleSelf(0);//默认为否
            /**
             * 如果保单创建时间为0，则取承保日期前一天的时间
             */
            if(unLifeInsMain.getCreateTime()==0){

                /**
                 * 如果创建时间为0,则取该保单得承保得日期，再则取默认时间:2019-01-01 00:00:00
                 */
                if(progress!=null&&progress.getFileTime()!=0){
                    policy.setCreatedAt(DateUtil.convertTimeToLocalDateTime(progress.getFileTime()).plusDays(-1));
                }else {
                    policy.setCreatedAt(DateUtil.convertTimeToLocalDateTime(1546272000L));
                }
            }else{
                policy.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getCreateTime())));
            }
            if(unLifeInsMain.getModifyTime()==0){
                /**
                 * 如果创建时间为0,则取该保单得承保得日期，再则取默认时间:2019-01-01 00:00:00
                 */
                if(progress!=null&&progress.getFileTime()!=0){
                    policy.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(progress.getFileTime()));
                }else {
                    policy.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(1546272000L));
                }
            }else {
                policy.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getModifyTime())));
            }
//            policy.setServiceUserJson(userService.getServiceUserJson(new Long(unLifeInsMain.getUserId())));//默认出单人,json不处理
            policy.setImportWay(2);//默认为2
            policy.setSource(1);//默认为1
            policy.setPayFrequency(1);//默认为1
            policy.setPayYear(mainProduct.getPayYear());
            policy.setPayYearUnit(mainProduct.getPayYearUnit());
            policy.setSafeYear(mainProduct.getSafeYear());
            policy.setSafeYearUnit(mainProduct.getSafeYearUnit());
            //缴费期间1填1，其他情况填2
            if (1 == policy.getPayYear()) {
                policy.setPayWay(1);
            } else {
                policy.setPayWay(2);
            }
            if (unLifeInsMain.getImgJson() != null &&
                    !"".equals(unLifeInsMain.getImgJson()) &&
                    unLifeInsMain.getImgJson().indexOf("customer_notification") != -1) {
                JSONObject imgJson = JSONObject.parseObject(unLifeInsMain.getImgJson());
                //老系统主表img_json的"customer_notification"，包含则已签
                if (imgJson != null) {
                    if (imgJson.containsKey("customer_notification")) {
                        policy.setNotificationStatus(2);
                    } else {
                        policy.setNotificationStatus(1);
                    }
                }
            }
            policy.setUpdateUserJson("{\"id\": 0, \"name\": \"\", \"mobile\": \"\", \"org_id\": 0, \"org_eid\": \"\", \"is_channel\": 0}");//不处理
            policy.setImportUserJson("{\"id\": 0, \"name\": \"\", \"mobile\": \"\", \"org_id\": 0, \"org_eid\": \"\", \"is_channel\": 0}");//不处理
//            policy.setHolderJson();//不处理
//            policy.setInsuredJson();//不处理
//            policy.setProductJson();//不处理
            policy.setFee(unLifeInsMain.getTotal());
            policy.setStdFee(stdFeeSum);
            policy.setCompanyStdFee(stdFeeSum);
            if (processResult.contains(policy.getProgress())) {
                policy.setProgressResult(Integer.valueOf(policy.getProgress() + "1"));
            } else {
                policy.setProgressResult(Integer.valueOf(policy.getProgress() + "0"));
            }
            policy.setUpdateUserId(intToLong(unLifeInsMain.getUserId()));
            policy.setProductId(mainProduct.getLifeProductId());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifeProduct product = lifeProductService.getByPolicyId(mainProduct.getLifeProductId());
            if (ReflectUtil.isNotNull(product)) {
                policy.setProductCategoryId(product.getCategoryId());
            }
            policy.setImportUserId(intToLong(unLifeInsMain.getUserId()));
//            policy.setSalesUserJson();//不处理
            policy.setSalesUserId(intToLong(unLifeInsMain.getUserId()));
            if(param.getChannelId()==22){
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
                UnHrDept unHrDept = unHrDeptMapper.getById(unLifeInsMain.getSalesDept());
                if (ReflectUtil.isNotNull(unHrDept)) {
                    if (unHrDept.getLevel() == 4) {
                        UnHrDept level3Dept = getLevel123Dept(unHrDept.getPid());
                        if(ReflectUtil.isNotNull(level3Dept)){
                            policy.setSalesUserOrgId(intToLong(level3Dept.getId()));
                        }
                    } else {
                        policy.setSalesUserOrgId(intToLong(unLifeInsMain.getSalesDept()));
                    }
                }
                if (!"0".equals(unLifeInsMain.getRankId())) {
                    policy.setSalesUserRankId(Long.valueOf(unLifeInsMain.getRankId()));
                }
            }else if(param.getChannelId()==27 || param.getChannelId()==31 || param.getChannelId()==2 || param.getChannelId()==25){
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                User user = userMapper.getById(intToLong(unLifeInsMain.getUserId()));
                if(ReflectUtil.isNotNull(user)){
                    policy.setSalesUserOrgId(user.getOrgId());
                    policy.setSalesUserRankId(user.getJobRank());
                }
            }
            policy.setIsPartner(1);//默认是
            policy.setChangeType(1);//默认1
//            policy.setPartnerParam();//默认为空
            policy.setServiceAgentType(1);//默认是1
            policy.setServiceUserId(intToLong(unLifeInsMain.getUserId()));//默认出单人
            policy.setIsChannel(0);//默认否
//            policy.setCompanyJson();//默认空
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifeVo lifeVo = lifePolicyMapper.selectHesitateDate(intToLong(unLifeInsMain.getId()));
            if(ReflectUtil.isNull(lifeVo)){
                policy.setIsAfterHesitate(0);
            }else {
                policy.setIsAfterHesitate(1);//有数据说明已过犹豫期
            }
//            if (mainProduct.getHesitateTime() != null) {
//                if (policyProgress_16.getDate() != null) {
//                    LocalDate hesitateDate = DateUtil.Add(policyProgress_16.getDate(), mainProduct.getHesitateTime());
//                    LocalDate nowDate = LocalDate.now();
//                    if (DateUtil.localDateIsBefore(hesitateDate, nowDate)) {//未过犹豫期
//                        policy.setIsAfterHesitate(0); //0是未过犹豫期，1是已过犹豫期
//                    } else {
//                        policy.setIsAfterHesitate(1);
//                    }
//                } else {
//                    policy.setIsAfterHesitate(0);
//                }
//            }


            policy.setClient(2);//默认为2
            policy.setApprovalStatus(3);//默认3 - 审批通过
            policy.setDraftStep(100);//默认都展示100
            policy.setContractType(1);//默认为空
            policy.setContractId(0L);//默认为0
            policy.setAmount(amountSum);//累加
            policy.setIsDeleted(0);//默认为0
//            policy.setExtension();//默认为空
            policy.setDeductCompanyCommission(new BigDecimal(0.00));//默认为0.00
            policy.setDeductCommission(new BigDecimal(0.00));//默认为0.00
            policy.setDeductScore(new BigDecimal(0.00));
            policy.setIsDeduct(0);//默认为0
            policy.setSalesTeamId(userService.getTeamById(intToLong(unLifeInsMain.getUserId())));//根据代理人带出
            policy.setSalesChannelId(0L);//默认为0
            policy.setCommissionStatus(2);//默认计算成功
//            policy.setSalesChannelOrgId(0);//默认为空
            policy.setPayStatus(2);//枚举类型不一致
            /**
             *投保人信息处理
             */
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifePolicyHolder lifePolicyHolder = lifePolicyHolderMapper.getById(oldId);
            if (ReflectUtil.isNull(lifePolicyHolder)) {
                Integer IdType = strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderIdType()));
                lifePolicyHolder = new LifePolicyHolder();
                lifePolicyHolder.setLifePolicyId(oldId);
                lifePolicyHolder.setName(unLifeInsMain.getHolderName());
                lifePolicyHolder.setGender(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderGender())));
                if (IdType == 1) {//身份证

                }
                if (unLifeInsMain.getHolderBirthday() != null && !"".equals(unLifeInsMain.getHolderBirthday()) && unLifeInsMain.getHolderBirthday().length() == 10) {
                   if(unLifeInsMain.getHolderBirthday().equals("0000-00-00")||unLifeInsMain.getHolderBirthday().equals("0000-11-30")){

                   }else {
                       lifePolicyHolder.setBirthday(LocalDate.parse(unLifeInsMain.getHolderBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                   }
                }
                lifePolicyHolder.setNationality(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderNation())));
                lifePolicyHolder.setIdType(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderIdType())));//枚举关系
                lifePolicyHolder.setIdNo(unLifeInsMain.getHolderIdNo());
                lifePolicyHolder.setIdConcat(lifePolicyHolder.getIdType() + lifePolicyHolder.getIdNo());
                if (unLifeInsMain.getHolderIdExpireEnd() != null && !"".equals(unLifeInsMain.getHolderIdExpireEnd()) && unLifeInsMain.getHolderIdExpireEnd().length() == 10) {
                    if(unLifeInsMain.getHolderIdExpireEnd().equals("0000-00-00")){
                    }else {
                        lifePolicyHolder.setIdExpiredDate(LocalDate.parse(unLifeInsMain.getHolderIdExpireEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                }

                lifePolicyHolder.setMarriage(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderMarriage())));
                lifePolicyHolder.setHeight(unLifeInsMain.getHolderHeight().intValue());
                lifePolicyHolder.setWeight(unLifeInsMain.getHolderWeight());
                lifePolicyHolder.setIncome(unLifeInsMain.getHolderSalary());
                if (!"".equals(unLifeInsMain.getHolderJobCode())) {
                    lifePolicyHolder.setJobCode(unLifeInsMain.getHolderJobCode()); //需确认职业编码code对应是否一致
                } else {
                    lifePolicyHolder.setJobCode("[]");
                }
                lifePolicyHolder.setCompany(unLifeInsMain.getHolderCompany());
                lifePolicyHolder.setMobile(unLifeInsMain.getHolderMobile());
                lifePolicyHolder.setIncomeSource(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderSalaryFrom())));//枚举确认
                lifePolicyHolder.setProvince(strToInteger(unBasicDistrictService.getCodeById(unLifeInsMain.getHolderContactProvince())));
                lifePolicyHolder.setCity(strToInteger(unBasicDistrictService.getCodeById(unLifeInsMain.getHolderContactCity())));
                lifePolicyHolder.setDistrict(strToInteger(unBasicDistrictService.getCodeById(unLifeInsMain.getHolderContactDistrict())));
                lifePolicyHolder.setZip(unLifeInsMain.getHolderContactZip());
                lifePolicyHolder.setEmail(unLifeInsMain.getHolderEmail());
                lifePolicyHolder.setIsSsid(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderHasSsid())));
                lifePolicyHolder.setBankName(basicEnumService.getNewEnum(unLifeInsMain.getBankName()));
                lifePolicyHolder.setBankNo(unLifeInsMain.getBankNo());
                lifePolicyHolder.setBankHolder(unLifeInsMain.getBankHolder());
//                lifePolicyHolder.setIsTax();//默认为空
                if (unLifeInsMain.getExtendItems() != null &&
                        !"[]".equals(unLifeInsMain.getExtendItems()) &&
                        unLifeInsMain.getExtendItems().indexOf("isTaxResidents") != -1) {
                    JSONObject json = JSONObject.parseObject(unLifeInsMain.getExtendItems());
                    if (json != null && json.get("isTaxResidents") != null) {
                        String isTaxResidents = json.get("isTaxResidents").toString();
                        if (isTaxResidents != null && !"".equals(isTaxResidents)) {
                            lifePolicyHolder.setTaxType(strToInteger(basicEnumService.getNewEnum(isTaxResidents)));
                        }
                    }
                }
                lifePolicyHolder.setAddress(unLifeInsMain.getHolderContactAddress());
                if (!"".equals(unLifeInsMain.getHolderJobCode())) {
                    lifePolicyHolder.setJobCode(unLifeInsMain.getHolderJobCode());
                } else {
                    lifePolicyHolder.setJobCode("[]");
                }
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                lifePolicyHolderMapper.insert(lifePolicyHolder);
                log.info("老系统保单号:{},保单投保人表[life_policy_holder]数据迁移迁移成功", oldId);
            } else {
                log.info("老系统保单号:{},保单投保人表[life_policy_holder]数据迁移已迁移过，忽略", oldId);
            }
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            lifePolicyMapper.insert(policy);
            insertMigrationLog(oldId, MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(), param);
        } else {
            log.info("保单迁移lifePolicy保单号：{}已迁移过,忽略", oldId);
        }
    }

    public Long getNewProductId(Integer id) {
        Long productId = 0L;
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        String oldProduct = unLifeProductAllService.getCodeById(id);
        if (oldProduct != null && !"".equals(oldProduct)) {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            productId = lifeProductMapper.getIdByCode(oldProduct);
        }
        return productId;
    }

    /**
     * Int类型转换为Long
     *
     * @param value
     * @return
     */
    public Long intToLong(Integer value) {
        Long result = new Long(0);
        if (value != null) {
            result = new Long(value);
        }
        return result;
    }

    /**
     * Int类型转换为Long
     *
     * @param str
     * @return
     */
    public Integer strToInteger(String str) {
        Integer result = 0;
        if (str != null && !"".equals(str)) {
            result = Integer.valueOf(str);
        }
        return result;
    }

    public void insertMigrationLog(Long id, Integer status, Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldId(id, MigrationTypeEnum.MIGRATION_TYPE_POLICY.getCode());
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_POLICY.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id, MigrationTypeEnum.MIGRATION_TYPE_POLICY.getCode());
        }
    }


    public String updateOldpicture(String oldPicture){
        String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
        String[] split = oldPicture.split(",");
        String firstLetter = Character.toString(oldPicture.charAt(0));
        if (",".equals(firstLetter)) {//以,开头
            String filePicture=oldPicture.substring(1,oldPicture.length());
            String[] split1 = filePicture.split(",");
            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
            List<String> convert = Convert.convert(List.class, split1);
            String file = "";
            for (String s : convert) {
                file += value + s + "|";
            }
            return file.substring(0,file.length()-1);
        }else {
            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
            List<String> convert = Convert.convert(List.class, split);
            String file = "";
            for (String s : convert) {
                file += value + s + "|";
            }
            return file.substring(0,file.length()-1);
        }
    }

    /**
     * 根据一个机构id查询level3机构
     * @param id
     * @return
     */
    public UnHrDept getLevel123Dept(Integer id) {
        /**
         * 查询老系统机构
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        UnHrDept unHrDept = unHrDeptMapper.getById(id);
        if (ReflectUtil.isNotNull(unHrDept)) {
            if (unHrDept.getLevel() ==4) {
                return getLevel123Dept(unHrDept.getPid());
            }else if(levelCode.contains(unHrDept.getLevel())){
                return unHrDept;
            }
        }
        return unHrDept;
    }


    public String IdToBirthday(String idNo) {
        String birthDayText = idNo.substring(6, 14);
        String birthday = birthDayText.substring(0, 4) + "-" + birthDayText.substring(4, 6) + "-" + birthDayText.substring(6, 8);
        return birthday;
    }

    public LocalDateTime getUploadTime(String str){
        String s = str.substring(str.length() - 18, str.length() - 4);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(s,dtf);
        return ldt;
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.plusDays(-1));

    }
}
