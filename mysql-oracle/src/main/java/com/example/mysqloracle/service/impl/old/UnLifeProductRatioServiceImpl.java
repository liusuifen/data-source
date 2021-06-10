package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.old.UnLifeProductAllMapper;
import com.example.mysqloracle.dao.old.UnLifeProductMapper;
import com.example.mysqloracle.dao.old.UnLifeProductRatioMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.entity.old.UnLifeProduct;
import com.example.mysqloracle.entity.old.UnLifeProductRatio;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.PartnerEnum;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnLifeProductRatioService;
import com.example.mysqloracle.util.DateUtil;
import com.example.mysqloracle.util.IdUtil;
import com.example.mysqloracle.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 寿险产品系数 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@Service
@Slf4j
public class UnLifeProductRatioServiceImpl extends ServiceImpl<UnLifeProductRatioMapper, UnLifeProductRatio> implements UnLifeProductRatioService {

    @Autowired
    private UnLifeProductRatioMapper unLifeProductRatioMapper;

    @Autowired
    private LifeProductRatioTemplateDetailMapper lifeProductRatioTemplateDetailMapper;


    @Autowired
    private UnLifeProductAllMapper unLifeProductAllMapper;

    @Autowired
    private LifeProductMapper lifeProductMapper;

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private LifeProductRatioTemplateMapper lifeProductRatioTemplateMapper;

    @Autowired
    private LifeProductRatioTemplateMapMapper lifeProductRatioTemplateMapMapper;


    public CommonResult saveTemplate(Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnLifeProductRatio> ratioByGroup = unLifeProductRatioMapper.getByGroup(param.getChannelId());
        for (UnLifeProductRatio unLifeProductRatio : ratioByGroup) {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Long newsLifeProductId = getNewsLifeProductId(unLifeProductRatio.getProductId());
            Long orgId=intToLong(Integer.valueOf(unLifeProductRatio.getArea()));
            LifeProductRatioTemplate lifeProductRatioTemplate = lifeProductRatioTemplateMapper.selectByProductId(newsLifeProductId,orgId);
            if(ReflectUtil.isNull(lifeProductRatioTemplate)){
                lifeProductRatioTemplate=new LifeProductRatioTemplate();
                lifeProductRatioTemplate.setLifeProductId(newsLifeProductId);
                lifeProductRatioTemplate.setSystemUserId(intToLong(unLifeProductRatio.getCreateBy()));
                lifeProductRatioTemplate.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeProductRatio.getCreateTime())));
                lifeProductRatioTemplate.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeProductRatio.getModifyTime())));
                lifeProductRatioTemplate.setOrgId(intToLong(Integer.valueOf(unLifeProductRatio.getArea())));
                lifeProductRatioTemplate.setIsDeleted(0);
                if(param.getChannelId()== PartnerEnum.channelId_佳兆业.getChannelId()){
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
                    String code = unLifeProductAllMapper.getCodeById(unLifeProductRatio.getProductId());
                    if(code!=null){
                        lifeProductRatioTemplate.setName("JZY_"+code+"_"+unLifeProductRatio.getArea());
                    }
                }else if(param.getChannelId()== PartnerEnum.channelId_众康.getChannelId()){
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
                    String code = unLifeProductAllMapper.getCodeById(unLifeProductRatio.getProductId());
                    if(code!=null){
                        lifeProductRatioTemplate.setName("ZK_"+code+"_"+unLifeProductRatio.getArea());
                    }
                }
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                lifeProductRatioTemplateMapper.insert(lifeProductRatioTemplate);
                log.info("该模板数据数据生成成功，模板id:{}",lifeProductRatioTemplate.getId());
            }else{
                log.info("该模板数据数据已迁移，寿险产品id:{}，忽略",unLifeProductRatio.getProductId());
            }
        }
        return new CommonResult("根据产品模板数据数据生成成功");
    }

    @Override
    public  CommonResult getRateData(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnLifeProductRatio> productRatioList = unLifeProductRatioMapper.getByChannelId(param.getChannelId());
        for (UnLifeProductRatio ratio : productRatioList) {
//            try{
                saveProductRatio(ratio,param);
//            }catch (Exception e){
//                insertMigrationLog(intToLong(ratio.getId()), MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(), param);
//            }
        }
        return new CommonResult("折标数据迁移成功");
    }



    void saveProductRatio(UnLifeProductRatio unLifeProductRatio,Param param){
        /**
         *价值系数模板明细表
         */
        Long newsLifeProductId = getNewsLifeProductId(unLifeProductRatio.getProductId());
        Long orgId=intToLong(Integer.valueOf(unLifeProductRatio.getArea()));
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer rationNum = lifeProductRatioTemplateDetailMapper.getByTemplateId(unLifeProductRatio.getId());
        if(rationNum==0){
            LifeProductRatioTemplateDetail lifeProductRatioTemplateDetail = new LifeProductRatioTemplateDetail();
            lifeProductRatioTemplateDetail.setId(unLifeProductRatio.getId());
            lifeProductRatioTemplateDetail.setYear(unLifeProductRatio.getYear());
            lifeProductRatioTemplateDetail.setYearUnit(unLifeProductRatio.getYearUnit());
            lifeProductRatioTemplateDetail.setPayYear(unLifeProductRatio.getPayYear());
            lifeProductRatioTemplateDetail.setPayYearUnit(unLifeProductRatio.getPayYearUnit());
            lifeProductRatioTemplateDetail.setRatio(unLifeProductRatio.getRatio());
            lifeProductRatioTemplateDetail.setRemark(unLifeProductRatio.getRemark());
            lifeProductRatioTemplateDetail.setSystemUserId(unLifeProductRatio.getCreateBy());
            lifeProductRatioTemplateDetail.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeProductRatio.getCreateTime())));
            lifeProductRatioTemplateDetail.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unLifeProductRatio.getModifyTime())));
            lifeProductRatioTemplateDetail.setIsDeleted(0);//默认值
            lifeProductRatioTemplateDetail.setSourceWay(1);
            lifeProductRatioTemplateDetail.setTemplateId(getTemplate(newsLifeProductId,orgId));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            lifeProductRatioTemplateDetailMapper.insert(lifeProductRatioTemplateDetail);
            log.info("该价值系数模板明细表数据迁移成功，价值系数模板明细表id:{}",unLifeProductRatio.getId());
        }else {
            log.info("该价值系数模板明细表模板数据已迁移，价值系数模板明细表id:{}，忽略",unLifeProductRatio.getId());
        }
        /**
         *
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer mapNum = lifeProductRatioTemplateMapMapper.selectByLifeProductIdAndOrgId(newsLifeProductId,intToLong(Integer.valueOf(unLifeProductRatio.getArea())));
        if(mapNum==0){
            LifeProductRatioTemplateMap lifeProductRatioTemplateMap = new LifeProductRatioTemplateMap();
            lifeProductRatioTemplateMap.setId(IdUtil.generateId());
            lifeProductRatioTemplateMap.setLifeProductId(newsLifeProductId);
            lifeProductRatioTemplateMap.setOrgId(intToLong(Integer.valueOf(unLifeProductRatio.getArea())));
            lifeProductRatioTemplateMap.setIsDeleted(0);
            lifeProductRatioTemplateMap.setTemplateId(getTemplate(newsLifeProductId,orgId));
            if(param.getChannelId()== PartnerEnum.channelId_佳兆业.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2019-09-27"));
            }else if(param.getChannelId()== PartnerEnum.channelId_汇盟.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2020-01-01"));
            }else if(param.getChannelId()== PartnerEnum.channelId_众康.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2019-12-01"));
            }else if(param.getChannelId()== PartnerEnum.channelId_广商.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2020-01-07"));
            }else if(param.getChannelId()== PartnerEnum.channelId_南粤.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2020-10-28"));
            }else if(param.getChannelId()== PartnerEnum.channelId_华润.getChannelId()){
                lifeProductRatioTemplateMap.setStartTime(DateUtil.strToLocalDate("2020-11-18"));
            }
            lifeProductRatioTemplateMap.setEndTime(DateUtil.strToLocalDate("2021-12-31"));
            lifeProductRatioTemplateMap.setSourceWay(1);
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            lifeProductRatioTemplateMapMapper.insert(lifeProductRatioTemplateMap);
            log.info("该产品价值系数表据数据迁移成功，寿险产品系数id:{}",unLifeProductRatio.getId());
        }else{
            log.info("该产品价值系数表数据已迁移，寿险产品系数id:{}，忽略",unLifeProductRatio.getId());
        }
        insertMigrationLog(intToLong(unLifeProductRatio.getId()), MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(), param);
    };

    /**
     * 根据老系统产品id返回对应新系统产品id
     * @param productId
     * @return
     */
    public Long getNewsLifeProductId(Integer productId){
        //默认
        Long id=0L;
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        String code = unLifeProductAllMapper.getCodeById(productId);
        if(code!=null){
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
             id = lifeProductMapper.getIdByCode(code);
        }
        return id;
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
     * 根据产品id获取模板id
     * @param
     * @return
     */
    public Integer getTemplate(Long lifeProductId,Long orgId){
        Integer templateId=0;
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        LifeProductRatioTemplate lifeProductRatioTemplate = lifeProductRatioTemplateMapper.selectByProductId(lifeProductId,orgId);
        if(ReflectUtil.isNotNull(lifeProductRatioTemplate)){
            templateId=lifeProductRatioTemplate.getId();
        }
        return templateId;
    }


    public void insertMigrationLog(Long id,Integer status,Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldId(id,MigrationTypeEnum.MIGRATION_TYPE_RATE.getCode());
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_RATE.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id,MigrationTypeEnum.MIGRATION_TYPE_RATE.getCode());
        }
    }

}
