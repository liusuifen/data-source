package com.example.mysqloracle.service.impl.old;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.old.UnLifeInsMainMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.entity.old.*;
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
import com.example.mysqloracle.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
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



    private static Integer  migration_status_sucessful= 1;//迁移成功

    private static Integer  migration_status_error= 2;//迁移失败

    private static Integer  migration_type_policy= 1;//1:保单

    /**
     * 获取老系统un_life_ins_main 表数据
     *
     * @return
     */
    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public CommonResult getAll(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnLifeInsMain> all = unLifeInsMainMapper.getAll(param.getChannelId());
        for (UnLifeInsMain unLifeInsMain : all) {
            try{
                saveNewPartner(unLifeInsMain,param);
            }catch (Exception e){
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                MigrationLog log = migrationLogMapper.getByOldId(intToLong(unLifeInsMain.getId()));
                if(ReflectUtil.isNull(log)) {
                    log = new MigrationLog();
                    log.setId(IdUtil.generateId());
                    log.setOldId(intToLong(unLifeInsMain.getId()));
                    log.setType(migration_type_policy);
                    log.setSourcePartnerId(intToLong(param.getChannelId()));
                    log.setStatus(migration_status_error);
                    log.setCreatedAt(LocalDateTime.now());
                    log.setUpdatedAt(LocalDateTime.now());
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    migrationLogMapper.insert(log);
                }else {
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    migrationLogMapper.updateAt(LocalDateTime.now(),migration_status_error,intToLong(unLifeInsMain.getId()));
                }
            }
        }
        return new CommonResult("保单迁移数据迁移成功");
    }

    @Override
    public CommonResult getFail(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> errorPolicy = migrationLogMapper.getErrorPolicy(intToLong(param.getChannelId()));
//        List<Integer> errorPolicy=new ArrayList<>();
//        errorPolicy.add(29457);
        for (Integer policyId : errorPolicy) {
            try{
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
                UnLifeInsMain unLifeInsMain = unLifeInsMainMapper.getByPolicyId(policyId);
                if(ReflectUtil.isNotNull(unLifeInsMain)){
                    saveNewPartner(unLifeInsMain,param);
                }
            }catch (Exception e){
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                MigrationLog log = migrationLogMapper.getByOldId(intToLong(policyId));
                if(ReflectUtil.isNull(log)) {
                    log = new MigrationLog();
                    log.setId(IdUtil.generateId());
                    log.setOldId(intToLong(policyId));
                    log.setType(migration_type_policy);
                    log.setSourcePartnerId(intToLong(param.getChannelId()));
                    log.setStatus(migration_status_error);
                    log.setCreatedAt(LocalDateTime.now());
                    log.setUpdatedAt(LocalDateTime.now());
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    migrationLogMapper.insert(log);
                }else {
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    migrationLogMapper.updateAt(LocalDateTime.now(),migration_status_error,intToLong(policyId));
                }
            }
        }
        return new CommonResult("错误迁移保单数据迁移成功");
    }

    @Override
    public CommonResult getTaxType() {
        Set<String> set=new HashSet<>();
        List<String> extendItems = unLifeInsMainMapper.getExtendItems();
        for (String item : extendItems) {
            JSONObject json = JSONObject.parseObject(item);
            if (json != null && json.get("isTaxResidents") != null) {
                String payDate = json.get("isTaxResidents").toString();
                if (payDate != null && !"".equals(payDate)) {
                    set.add(payDate);
                }
            }
        }
        return new CommonResult(set);
    }

    /**
     * 把数据保存入新系统合作方数据库中
     * 枚举需要特殊处理
     */
    public void saveNewPartner(UnLifeInsMain unLifeInsMain,Param param) {
        Long oldId=intToLong(unLifeInsMain.getId());
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        LifePolicy lifePolicy = lifePolicyService.getById(oldId);
        if (ReflectUtil.isNull(lifePolicy)) {
            LifePolicy policy = new LifePolicy();
            policy.setId(oldId);
            policy.setSourcePartnerId(intToLong(unLifeInsMain.getChannelId()));
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
            if(unLifeInsMain.getValDate()!=null && !"".equals(unLifeInsMain.getValDate())){
                policy.setDateStart(LocalDate.parse(unLifeInsMain.getValDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
//            policy.setDateEnd();不处理
            if(unLifeInsMain.getExtendItems()!=null && !"[]".equals(unLifeInsMain.getExtendItems())){
                JSONObject json = JSONObject.parseObject(unLifeInsMain.getExtendItems());
                if (json != null && json.get("pay_date") != null) {
                    String payDate = json.get("pay_date").toString();
                    if (payDate != null && !"".equals(payDate) && payDate.length()==10) {
                        policy.setDateDeduct(DateUtil.strToLocalDate(payDate));
                    }
                }
            }
            /**
             * 状态表
             */
            //防止重复迁移生成数据
            LifePolicyStatus status = lifePolicyStatusMapper.getByPolicyId(oldId);
            if (ReflectUtil.isNull(status)) {
                Integer oldstate = unLifeInsMain.getState();
                LifePolicyStatus lifePolicyStatus = new LifePolicyStatus();
                lifePolicyStatus.setId(IdUtil.generateId());
                lifePolicyStatus.setLifePolicyId(oldId);
                lifePolicyStatus.setRemark("");
                lifePolicyStatus.setStatus(StatusEnum.getNewByCode(oldstate));
                if (oldstate == 0) {//老系统保单创建日期
                    lifePolicyStatus.setDate(DateUtil.convertTimeToLocalDate(intToLong(unLifeInsMain.getCreateTime())));
                } else if (oldstate == 1 || oldstate == 7 || oldstate == 10) {//老系统保单生效日期
                    if(unLifeInsMain.getValDate()!=null && !"".equals(unLifeInsMain.getValDate()) && unLifeInsMain.getValDate().length()==10){
                        lifePolicyStatus.setDate(DateUtil.strToLocalDate(unLifeInsMain.getValDate()));
                    }
                } else {//老系统保单修改日期
                    lifePolicyStatus.setDate(DateUtil.convertTimeToLocalDate(intToLong(unLifeInsMain.getModifyTime())));
                }
                if(lifePolicyStatus.getDate()!=null && !"".equals(lifePolicyStatus.getDate())){
                    lifePolicyStatus.setCreatedAt(DateUtil.localDateToLocalDateTime(lifePolicyStatus.getDate()));
                }

                lifePolicyStatus.setUpdateUser("{}");//默认为空
                lifePolicyStatus.setUpdateUserId(0L);//默认为空
                lifePolicyStatusMapper.insert(lifePolicyStatus);
                log.info("老系统保单号:{},保单状态表迁移成功", unLifeInsMain.getId());
            } else {
                log.info("老系统保单号:{},保单状态表[life_policy_status]已迁移过，忽略", unLifeInsMain.getId());
            }

            /**
             * 文档
             */
            if (!"".equals(unLifeInsMain.getImgJson()) &&
                    unLifeInsMain.getImgJson()!=null &&
                    unLifeInsMain.getImgJson().indexOf("customer_notification")!=-1) {
                JSONObject imgJson = JSONObject.parseObject(unLifeInsMain.getImgJson());
                //老系统主表img_json的"customer_notification"，包含则已签
                if (imgJson != null && !"".equals(imgJson)) {
                    if (imgJson.containsKey("customer_notification")) {
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        Integer document = lifePolicyDocumentMapper.getByPolicyIdAndType(oldId);
                        if (document == 0) {
                            String context = imgJson.get("customer_notification").toString();
                            String url = "https://www.luckyins.com" + context;
                            LifePolicyDocument lifePolicyDocument = new LifePolicyDocument();
                            lifePolicyDocument.setId(IdUtil.generateId());
                            lifePolicyDocument.setLifePolicyId(oldId);
                            lifePolicyDocument.setName("客户告知书");
                            lifePolicyDocument.setUrl(url);
                            lifePolicyDocument.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getCreateTime())));
                            lifePolicyDocument.setType("106");//客户告知书状态
                            lifePolicyDocument.setIsDeleted(0);
                            lifePolicyDocumentMapper.insert(lifePolicyDocument);
                            log.info("老系统保单号:{},保单文档迁移成功", unLifeInsMain.getId());
                        } else {
                            log.info("老系统保单号:{},保单文档已迁移过，忽略", unLifeInsMain.getId());
                        }
                    }
                }
            } else {
                log.info("老系统保单号:{},保单文档imgJson不存在客户告知书，忽略", unLifeInsMain.getId());
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
                    policyProgress.setDate(DateUtil.convertTimeToLocalDate(intToLong(unLifeInsState.getCreateTime())));
                    String oldPicture = unLifeInsState.getPicture();
                    if (oldPicture != null && !"".equals(oldPicture)) {
                        String[] split = oldPicture.split(",");
                        String firstLetter = Character.toString(oldPicture.charAt(0));
                        if ("".equals(firstLetter)) {
                            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
                            policyProgress.setFile(split[1]);
                        } else {
                            policyProgress.setFile(split[0]);
                        }
                    } else {
                        policyProgress.setFile("");
                    }
//                    policyProgress.setRemark("");//默认为空字符串
                    policyProgress.setUpdateUser("{}");//默认为空字符串
                    policyProgress.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsState.getCreateTime())));
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
                        Integer num = lifePolicyProgressMapper.getCountById(intToLong(unLifeInsState.getId()));
                        if (num == 0) {
                            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                            lifePolicyProgressMapper.insert(policyProgress);
                            log.info("老系统保单号:{},保单进度id{}，数据迁移成功", unLifeInsMain.getId(), unLifeInsState.getId());
                        } else {
                            log.info("老系统保单号:{},保单进度id{}，数据迁移已迁移过，忽略", unLifeInsMain.getId(), unLifeInsState.getId());
                        }
                    } else {
                        log.info("老系统保单号:{},保单进度表[life_policy_progress]已全部迁移过，忽略", unLifeInsMain.getId());
                    }
                }
            } else {
                log.info("老系统保单号:{},保单进度表[un_life_ins_state]无数据迁移，忽略", unLifeInsMain.getId());
            }
            //记录主险对象
            LifePolicyProduct mainProduct = new LifePolicyProduct();
            //记录保费总合
            BigDecimal stdFeeSum = new BigDecimal(0);
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
                            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                            ProductCategory category = productCategoryService.getByPolicyId(intToLong(lifePolicyProduct.getCategoryId()));
                            if (ReflectUtil.isNotNull(category)) {
                                lifePolicyProduct.setCategoryName(category.getName());
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
                            lifePolicyProduct.setPayYear(unLifeInsContent.getPeriodPayment());
                            if (unLifeInsContent.getPeriodPayment() != null) {
                                if (unLifeInsContent.getPeriodPayment() >= 50) {
                                    lifePolicyProduct.setPayYearUnit(2);//岁
                                } else {
                                    lifePolicyProduct.setPayYearUnit(1);//年
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
                            stdFeeSum = stdFeeSum.add(unLifeInsContent.getStdPremium());
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
                                log.info("老系统保单号:{},保单相关产品id{}数据迁移成功", unLifeInsMain.getId(), unLifeInsContent.getId());
                            } else {
                                log.info("老系统保单号:{},保单相关产品id{}数据迁移已迁移过，忽略", unLifeInsMain.getId(), unLifeInsContent.getId());
                            }
                        }
                    } else {
                        log.info("老系统保单号:{},保单产品表[life_policy_product]数据迁移已迁移过，忽略", unLifeInsMain.getId());
                    }
                }
            } else {
                log.info("老系统保单号:{},保单产品表[un_life_ins_content]无数据，忽略", unLifeInsMain.getId());
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
                        if (beneficiary.getBirthday() != null && !"".equals(beneficiary.getBirthday()) && beneficiary.getBirthday().length()==10) {
                            lifePolicyBeneficiary.setBirthday(DateUtil.strToLocalDate(beneficiary.getBirthday()));
                        }
                        lifePolicyBeneficiary.setIdType(strToInteger(basicEnumService.getNewEnum(beneficiary.getIdType())));
                        lifePolicyBeneficiary.setIdNo(beneficiary.getIdNo());
                        lifePolicyBeneficiary.setIdConcat(lifePolicyBeneficiary.getIdType() + lifePolicyBeneficiary.getIdNo());
                        if (beneficiary.getIdExpireEnd() != null && !"".equals(beneficiary.getIdExpireEnd()) && beneficiary.getIdExpireEnd().length()==10) {
                            lifePolicyBeneficiary.setIdExpiredDate(DateUtil.strToLocalDate(beneficiary.getIdExpireEnd()));
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
                            log.info("老系统保单号:{},保单受益人id{}数据迁移迁成功", unLifeInsMain.getId(), beneficiary.getId());
                        } else {
                            log.info("老系统保单号:{},保单受益人id{}数据迁移已迁移过，忽略", unLifeInsMain.getId(), beneficiary.getId());
                        }
                    }
                } else {
                    log.info("老系统保单号:{},保单受益人表[life_policy_beneficiary]数据迁移已迁移过，忽略", unLifeInsMain.getId());
                }
            } else {
                log.info("老系统保单号:{},保单受益人表[un_life_ins_beneficiary]无数据，忽略", unLifeInsMain.getId());
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
                            if(unLifeInsInsured.getInsuredBirthday()!=null && !"".equals(unLifeInsInsured.getInsuredBirthday()) && unLifeInsInsured.getInsuredBirthday().length()==10){
                            lifePolicyInsured.setBirthday(DateUtil.strToLocalDate(unLifeInsInsured.getInsuredBirthday()));
                            }
                            lifePolicyInsured.setRelation(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getRelInsuredHolder())));
                            lifePolicyInsured.setRelationName(RelationEnum.getNameByCode(lifePolicyInsured.getRelation()));
                            lifePolicyInsured.setIdType(strToInteger(basicEnumService.getNewEnum(unLifeInsInsured.getInsuredIdType())));
                            lifePolicyInsured.setIdNo(unLifeInsInsured.getInsuredIdNo());
                            lifePolicyInsured.setIdConcat(lifePolicyInsured.getIdType() + lifePolicyInsured.getIdNo());
                            if(unLifeInsInsured.getInsuredIdExpireEnd()!=null && !"".equals(unLifeInsInsured.getInsuredIdExpireEnd()) && unLifeInsInsured.getInsuredIdExpireEnd().length()==10){
                                lifePolicyInsured.setIdExpiredDate(DateUtil.strToLocalDate(unLifeInsInsured.getInsuredIdExpireEnd()));
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
                            log.info("老系统保单号:{},保单被保险人id{}数据迁移迁移成功", unLifeInsMain.getId(), unLifeInsInsured.getId());
                        } else {
                            log.info("老系统保单号:{},保单被保险人id{}数据迁移已迁移过，忽略", unLifeInsMain.getId(), unLifeInsInsured.getId());
                        }
                    }
                } else {
                    log.info("老系统保单号:{},保单被保险人表[life_policy_insured]数据迁移已迁移过，忽略", unLifeInsMain.getId());
                }
            } else {
                log.info("老系统保单号:{},保单被保险人表[un_life_ins_insured]无数据，忽略", unLifeInsMain.getId());
            }
            if (policyProgress_15.getDate() != null) {
                policy.setDateAccept(policyProgress_15.getDate());
            }
            if (policyProgress_12.getDate() != null) {
                policy.setDateAdvance(policyProgress_12.getDate());
            }
            policy.setIsSaleSelf(0);//默认为否
            policy.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getCreateTime())));
            policy.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeInsMain.getModifyTime())));
//            policy.setServiceUserJson(userService.getServiceUserJson(new Long(unLifeInsMain.getUserId())));//默认出单人,json不处理
            policy.setImportWay(1);//默认为1
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
            if (unLifeInsMain.getImgJson() != null && !"".equals(unLifeInsMain.getImgJson()) && unLifeInsMain.getImgJson().indexOf("customer_notification")!=-1) {
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
            policy.setProgressResult(0);//默认成功
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
            policy.setSalesUserOrgId(intToLong(unLifeInsMain.getSalesDept()));
            if (!"0".equals(unLifeInsMain.getRankId())) {
                policy.setSalesUserRankId(Long.valueOf(unLifeInsMain.getRankId()));
            }
            policy.setIsPartner(1);//默认是
            policy.setChangeType(1);//默认1
//            policy.setPartnerParam();//默认为空
            policy.setServiceAgentType(1);//默认是1
            policy.setServiceUserId(intToLong(unLifeInsMain.getUserId()));//默认出单人
            policy.setIsChannel(0);//默认否
//            policy.setCompanyJson();//默认空

            //犹豫期
            if(policyProgress_16.getDate()!=null){
                LocalDate hesitateDate = DateUtil.Add(policyProgress_16.getDate(), 15);
                LocalDate nowDate = LocalDate.now();
                if (DateUtil.localDateIsBefore(hesitateDate, nowDate)) {//未过犹豫期
                    policy.setIsAfterHesitate(0); //0是未过犹豫期，1是已过犹豫期
                } else {
                    policy.setIsAfterHesitate(1);
                }
            }else {
                policy.setIsAfterHesitate(0);
            }

            policy.setClient(2);//默认为2
            policy.setApprovalStatus(3);//默认3 - 审批通过
//            policy.setApprovalRemark();//默认为空
            if (unLifeInsMain.getIsTemp() == 0) {//0:正式保单
                policy.setDraftStep(100);//老系统非正式保单
            } else {
                policy.setDraftStep(0);
            }
            policy.setContractType(1);//默认为空
            policy.setContractId(0L);//默认为0
            policy.setAmount(new BigDecimal(0.00));//默认为0.00
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
            policy.setPayStatus(1);//枚举类型不一致
            /**
             *投保人信息处理
             */
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            LifePolicyHolder lifePolicyHolder = lifePolicyHolderMapper.getById(oldId);
            if (ReflectUtil.isNull(lifePolicyHolder)) {
                lifePolicyHolder = new LifePolicyHolder();
                lifePolicyHolder.setLifePolicyId(oldId);
                lifePolicyHolder.setName(unLifeInsMain.getHolderName());
                lifePolicyHolder.setGender(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderGender())));
                if(unLifeInsMain.getHolderBirthday()!=null && !"".equals(unLifeInsMain.getHolderBirthday())) {
                    lifePolicyHolder.setBirthday(LocalDate.parse(unLifeInsMain.getHolderBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                lifePolicyHolder.setNationality(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderNation())));
                lifePolicyHolder.setIdType(strToInteger(basicEnumService.getNewEnum(unLifeInsMain.getHolderIdType())));//枚举关系
                lifePolicyHolder.setIdNo(unLifeInsMain.getHolderIdNo());
                lifePolicyHolder.setIdConcat(lifePolicyHolder.getIdType()+lifePolicyHolder.getIdNo());
                if(unLifeInsMain.getHolderIdExpireEnd()!=null && !"".equals(unLifeInsMain.getHolderIdExpireEnd()) && unLifeInsMain.getHolderIdExpireEnd().length()==10){
                    lifePolicyHolder.setIdExpiredDate(LocalDate.parse(unLifeInsMain.getHolderIdExpireEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
                if(unLifeInsMain.getExtendItems()!=null && !"[]".equals(unLifeInsMain.getExtendItems())){
                    JSONObject json = JSONObject.parseObject(unLifeInsMain.getExtendItems());
                    if (json != null && json.get("isTaxResidents") != null) {
                        String isTaxResidents = json.get("isTaxResidents").toString();
                        if (isTaxResidents != null && !"".equals(isTaxResidents)) {
                            lifePolicyHolder.setTaxType(strToInteger(basicEnumService.getNewEnum(isTaxResidents)));
                        }
                    }
                }
                lifePolicyHolder.setAddress(unLifeInsMain.getHolderContactAddress());
                if(!"".equals(unLifeInsMain.getHolderJobCode())){
                    lifePolicyHolder.setJobCode(unLifeInsMain.getHolderJobCode());
                }else {
                    lifePolicyHolder.setJobCode("[]");
                }
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                lifePolicyHolderMapper.insert(lifePolicyHolder);
                log.info("老系统保单号:{},保单投保人表[life_policy_holder]数据迁移迁移成功", unLifeInsMain.getId());
            } else {
                log.info("老系统保单号:{},保单投保人表[life_policy_holder]数据迁移已迁移过，忽略", unLifeInsMain.getId());
            }
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            lifePolicyMapper.insert(policy);
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            MigrationLog log = migrationLogMapper.getByOldId(oldId);
            if(ReflectUtil.isNull(log)){
                log = new MigrationLog();
                log.setId(IdUtil.generateId());
                log.setOldId(oldId);
                log.setStatus(migration_status_sucessful);
                log.setType(migration_type_policy);
                log.setSourcePartnerId(intToLong(param.getChannelId()));
                log.setCreatedAt(LocalDateTime.now());
                log.setUpdatedAt(LocalDateTime.now());
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                migrationLogMapper.insert(log);
            }else {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                migrationLogMapper.updateAt(LocalDateTime.now(),migration_status_sucessful,oldId);
            }

        } else {
            log.info("保单迁移lifePolicy保单号：{}已迁移过,忽略", unLifeInsMain.getId());
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

    public static void main(String[] args) {

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                 LocalDateTime time = LocalDateTime.now();
//                 String localTime = df.format(time);
//                 LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05",df);
//                System.out.println("LocalDateTime转成String类型的时间："+localTime);
//                System.out.println("String类型的时间转成LocalDateTime："+ldt);

    }
}
