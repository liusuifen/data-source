package com.example.mysqloracle.service.impl.news.admin;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.news.admin.*;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.entity.news.admin.*;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.PartnerEnum;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.news.admin.PartnerLifePolicyService;
import com.example.mysqloracle.util.IdUtil;
import com.example.mysqloracle.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
@Service
@Slf4j
public class PartnerLifePolicyServiceImpl extends ServiceImpl<PartnerLifePolicyMapper, PartnerLifePolicy> implements PartnerLifePolicyService {

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private LifePolicyMapper lifePolicyMapper;

    @Autowired
    private PartnerLifePolicyMapper partnerLifePolicyMapper;

    @Autowired
    private LifePolicyHolderMapper lifePolicyHolderMapper;

    @Autowired
    private PartnerLifePolicyHolderMapper partnerLifePolicyHolderMapper;

    @Autowired
    private LifePolicyBeneficiaryMapper lifePolicyBeneficiaryMapper;

    @Autowired
    private PartnerLifePolicyBeneficiaryMapper partnerLifePolicyBeneficiaryMapper;

    @Autowired
    private LifePolicyInsuredMapper lifePolicyInsuredMapper;

    @Autowired
    private PartnerLifePolicyInsuredMapper partnerLifePolicyInsuredMapper;

    @Autowired
    private LifePolicyProductMapper lifePolicyProductMapper;

    @Autowired
    private PartnerLifePolicyProductMapper partnerLifePolicyProductMapper;

    @Autowired
    private LifePolicyProgressMapper lifePolicyProgressMapper;

    @Autowired
    private PartnerLifePolicyProgressMapper partnerLifePolicyProgressMapper;

    @Autowired
    private LifePolicyStatusMapper lifePolicyStatusMapper;

    @Autowired
    private PartnerLifePolicyStatusMapper partnerLifePolicyStatusMapper;

    @Autowired
    private LifePolicyDocumentMapper lifePolicyDocumentMapper;

    @Autowired
    private PartnerLifePolicyDocumentMapper partnerLifePolicyDocumentMapper;





    @Override
    public CommonResult getAll(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> policys = migrationLogMapper.getSuccessPolicy(intToLong(param.getChannelId()));
        Long partnerId=PartnerEnum.getPartnerIdByChannelId(param.getChannelId());
        for (Integer id : policys) {
            try{
                syncPartnerLifePolicy(id,partnerId,param);
            }catch (Exception e){
                insertMigrationLog(intToLong(id), MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(), param);
            }
        }
        return new CommonResult("合作方保单数据同步至保联后台成功");
    }

    @Override
    public CommonResult getError(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> policys = migrationLogMapper.getErrorSync(intToLong(param.getChannelId()));
        Long partnerId=PartnerEnum.getPartnerIdByChannelId(param.getChannelId());
        for (Integer id : policys) {
            try{
                syncPartnerLifePolicy(id,partnerId,param);
            }catch (Exception e){
                insertMigrationLog(intToLong(id),MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(),param);
            }
        }
        return new CommonResult("失败的保单数据重新同步至保联后台成功");
    }

    public void syncPartnerLifePolicy(Integer id,Long partnerId,Param param){
        /**
         * partner_life_policy表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        LifePolicy lifePolicy = lifePolicyMapper.getByIds(intToLong(id));
        if(ReflectUtil.isNull(lifePolicy)){
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
            Integer num = partnerLifePolicyMapper.getCount(lifePolicy.getId());
            if(num==0){
                PartnerLifePolicy partnerLifePolicy = new PartnerLifePolicy();
                BeanUtils.copyProperties(lifePolicy,partnerLifePolicy);
                partnerLifePolicy.setPartnerId(partnerId);
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                partnerLifePolicyMapper.insert(partnerLifePolicy);
                log.info("保单号id{}partner_life_policy表同步至保联后台成功",lifePolicy.getId());
            }else {
                log.info("保单号id{}partner_life_policy表已同步至保联后台，忽略",lifePolicy.getId());
            }

        }
        /**
         * partner_life_policy_holder表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyHolder> lifePolicyHolderList = lifePolicyHolderMapper.selectBypolicyId(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyHolderList)){
            for (LifePolicyHolder lifePolicyHolder : lifePolicyHolderList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyHolderMapper.getCount(lifePolicyHolder.getLifePolicyId());
                if(num==0){
                    PartnerLifePolicyHolder partnerLifePolicyHolder = new PartnerLifePolicyHolder();
                    BeanUtils.copyProperties(lifePolicyHolder,partnerLifePolicyHolder);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyHolderMapper.insert(partnerLifePolicyHolder);
                    log.info("保单号life_policy_id{}partner_life_policy_holder表同步至保联后台成功",lifePolicyHolder.getLifePolicyId());
                }else {
                    log.info("保单号life_policy_id{}partner_life_policy_holder表已同步至保联后台，忽略",lifePolicyHolder.getLifePolicyId());
                }
            }
        }
        /**
         * partner_life_policy_beneficiary表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyBeneficiary> lifePolicyBeneficiaryList = lifePolicyBeneficiaryMapper.selectByPolicyId(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyBeneficiaryList)){
            for (LifePolicyBeneficiary lifePolicyBeneficiary : lifePolicyBeneficiaryList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyBeneficiaryMapper.getCount(lifePolicyBeneficiary.getId());
                if(num==0){
                    PartnerLifePolicyBeneficiary partnerLifePolicyBeneficiary = new PartnerLifePolicyBeneficiary();
                    BeanUtils.copyProperties(lifePolicyBeneficiary,partnerLifePolicyBeneficiary);
                    partnerLifePolicyBeneficiary.setSourceId(partnerId);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyBeneficiaryMapper.insert(partnerLifePolicyBeneficiary);
                    log.info("保单号id{}partner_life_policy_holder表同步至保联后台成功",lifePolicyBeneficiary.getId());
                }else {
                    log.info("保单号id{}partner_life_policy_beneficiary表已同步至保联后台，忽略",lifePolicyBeneficiary.getId());
                }
            }
        }
        /**
         * partner_life_policy_insured表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyInsured> lifePolicyInsuredList = lifePolicyInsuredMapper.selectByPolicyId(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyInsuredList)){
            for (LifePolicyInsured lifePolicyInsured : lifePolicyInsuredList) {
                if(ReflectUtil.isNotNull(lifePolicyInsured)){
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    Integer num = partnerLifePolicyInsuredMapper.getCount(lifePolicyInsured.getId());
                    if(num==0){
                        PartnerLifePolicyInsured partnerLifePolicyInsured = new PartnerLifePolicyInsured();
                        BeanUtils.copyProperties(lifePolicyInsured,partnerLifePolicyInsured);
                        partnerLifePolicyInsured.setSourceId(partnerId);
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                        partnerLifePolicyInsuredMapper.insert(partnerLifePolicyInsured);
                        log.info("保单号id{}partner_life_policy_insured表同步至保联后台成功",lifePolicyInsured.getId());
                    }else {
                        log.info("保单号id{}partner_life_policy_insured表已同步至保联后台，忽略",lifePolicyInsured.getId());
                    }
                }
            }
        }


        /**
         * partner_life_policy_product表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyProduct> lifePolicyProductList = lifePolicyProductMapper.selectByPolicyId(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyProductList)){
            for (LifePolicyProduct lifePolicyProduct : lifePolicyProductList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyProductMapper.getCount(lifePolicyProduct.getId());
                if(num==0){
                    PartnerLifePolicyProduct partnerLifePolicyProduct = new PartnerLifePolicyProduct();
                    BeanUtils.copyProperties(lifePolicyProduct,partnerLifePolicyProduct);
                    partnerLifePolicyProduct.setSourceId(partnerId);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyProductMapper.insert(partnerLifePolicyProduct);
                    log.info("保单号id{}partner_life_policy_product表同步至保联后台成功",lifePolicyProduct.getId());
                }else {
                    log.info("保单号id{}partner_life_policy_product表已同步至保联后台，忽略",lifePolicyProduct.getId());
                }
            }
        }

        /**
         * partner_life_policy_progress表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyProgress> lifePolicyProgressList = lifePolicyProgressMapper.selectByPolicyId(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyProgressList)){
            for (LifePolicyProgress lifePolicyProgress : lifePolicyProgressList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyProgressMapper.getCount(lifePolicyProgress.getId());
                if(num==0){
                    PartnerLifePolicyProgress partnerLifePolicyProduct = new PartnerLifePolicyProgress();
                    BeanUtils.copyProperties(lifePolicyProgress,partnerLifePolicyProduct);
                    partnerLifePolicyProduct.setSourceId(partnerId);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyProgressMapper.insert(partnerLifePolicyProduct);
                    log.info("保单号id{}partner_life_policy_progress表同步至保联后台成功",lifePolicyProgress.getId());
                }else {
                    log.info("保单号id{}partner_life_policy_progress表已同步至保联后台，忽略",lifePolicyProgress.getId());
                }
            }
        }
        /**
         * partner_life_policy_status表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyStatus> lifePolicyStatusList = lifePolicyStatusMapper.selectByPolicy(intToLong(id));
        if(!CollectionUtils.isEmpty(lifePolicyStatusList)){
            for (LifePolicyStatus lifePolicyStatus : lifePolicyStatusList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyProgressMapper.getCount(lifePolicyStatus.getId());
                if(num==0){
                    PartnerLifePolicyStatus partnerLifePolicyStatus = new PartnerLifePolicyStatus();
                    BeanUtils.copyProperties(lifePolicyStatus,partnerLifePolicyStatus);
                    partnerLifePolicyStatus.setSourceId(partnerId);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyStatusMapper.insert(partnerLifePolicyStatus);
                    log.info("保单号id{}partner_life_policy_status表同步至保联后台成功",lifePolicyStatus.getId());
                }else {
                    log.info("保单号id{}partner_life_policy_status表已同步至保联后台，忽略",lifePolicyStatus.getId());
                }
            }
        }
        /**
         *partner_life_policy_document表同步
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<LifePolicyDocument> lifePolicyDocumentList = lifePolicyDocumentMapper.selectByPolicyId(intToLong(id));
        if(CollectionUtils.isEmpty(lifePolicyDocumentList)){
            for (LifePolicyDocument lifePolicyDocument : lifePolicyDocumentList) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                Integer num = partnerLifePolicyDocumentMapper.getCount(lifePolicyDocument.getId());
                if(num==0){
                    PartnerLifePolicyDocument partnerLifePolicyDocument = new PartnerLifePolicyDocument();
                    BeanUtils.copyProperties(lifePolicyDocument,partnerLifePolicyDocument);
                    partnerLifePolicyDocument.setSourceId(partnerId);
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                    partnerLifePolicyDocumentMapper.insert(partnerLifePolicyDocument);
                    log.info("保单号id{}partner_life_policy_document表同步至保联后台成功",lifePolicyDocument.getId());
                }else {
                    log.info("保单号id{}partner_life_policy_document表已同步至保联后台，忽略",lifePolicyDocument.getId());
                }
            }
        }
        insertMigrationLog(intToLong(id),MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(),param);
    }

    public void insertMigrationLog(Long id,Integer status,Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldIdAndType(id);
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_POLICY_TO_ADMIN.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id);
        }
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
}
