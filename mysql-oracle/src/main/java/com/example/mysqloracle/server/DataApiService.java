package com.example.mysqloracle.server;

import com.alibaba.fastjson.JSONObject;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.Const;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.old.UnLifeProductAllMapper;
import com.example.mysqloracle.dao.old.UnLifeProductMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.enums.BooleanEnum;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.ResultEnum;
import com.example.mysqloracle.exception.BusinessException;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.util.AuthorizationUtil;
import com.example.mysqloracle.util.IdUtil;
import com.example.mysqloracle.util.JsonUtils;
import com.example.mysqloracle.util.ReflectUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataApiService {

    @Autowired
    private HttpService httpService;


    @Autowired
    private LifeProductMapper lifeProductMapper;

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private LifeProductTagMapper lifeProductTagMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private LifeProductTagMapMapper lifeProductTagMapMapper;

    @Autowired
    private UnLifeProductMapper unLifeProductMapper;








    /**
     * 从保联获取产品
     */
    public CommonResult productMove(Param param) {
        List<String> productCode=null;

//        if(param.getChannelId()==22){
//             productCode = Const.JIAZAOYE_CODE;
//        }else if (param.getChannelId()==27){
//             productCode = Const.ZONGKANG_CODE;
//        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        productCode =unLifeProductMapper.getProductCode(param.getChannelId());
        for (String code : productCode) {
            Long id = null;
            try {
                /**
                 * 查询保联的产品库获取code
                 */
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                id = lifeProductMapper.getIdByCode(code);
                if (id != null) {
                    saveNewProduct(param, id, code);
                }else {
                    log.info("保联后台产品code：{}不存在",code);
                }
            } catch (Exception e) {
                insertMigrationLog(id,MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(), param);
            }
        }
        return new CommonResult("迁移产品数据迁移成功");
    }


    /**
     * 从保联获取产品
     */
    public CommonResult createTags(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<String> productCode =unLifeProductMapper.getProductCode(param.getChannelId());
        for (String code : productCode) {
            Long id = null;
//            try {
                /**
                 * 查询保联的产品库获取code
                 */
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                id = lifeProductMapper.getIdByCode(code);
                Integer categoryId = lifeProductMapper.getCategoryByCode(code);
                if (id != null) {
                    createTag(id, categoryId);
                }
//            } catch (Exception e) {
//                /**
//                 * 只记录生成失败的日志
//                 */
//                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
//                MigrationLog log = migrationLogMapper.getByOldId(id);
//                if (ReflectUtil.isNull(log)) {
//                    log = new MigrationLog();
//                    log.setId(IdUtil.generateId());
//                    log.setOldId(id);
//                    log.setType(migration_type_tag);
//                    log.setSourcePartnerId(intToLong(param.getChannelId()));
//                    log.setStatus(migration_status_error);
//                    log.setCreatedAt(LocalDateTime.now());
//                    log.setUpdatedAt(LocalDateTime.now());
//                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
//                    migrationLogMapper.insert(log);
//                } else {
//                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
//                    migrationLogMapper.updateAt(LocalDateTime.now(), migration_status_error, id);
//                }
//            }
        }
        return new CommonResult("迁移产品标签关联数据生成成功");
    }

    public void insertMigrationLog(Long id,Integer status,Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldId(id,MigrationTypeEnum.MIGRATION_TYPE_PRODUCT.getCode());
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_PRODUCT.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id,MigrationTypeEnum.MIGRATION_TYPE_PRODUCT.getCode());
        }
    }



    /**
     * 从保联获取产品
     */
    public CommonResult getFail(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Long> errorPolicys = migrationLogMapper.getErrorProduct(intToLong(param.getChannelId()));
        for (Long id : errorPolicys) {
            try {
                /**
                 * 查询保联的产品库获取code
                 */
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
                String code = lifeProductMapper.getCodeById(id);
                saveNewProduct(param, id, code);
            } catch (Exception e) {
                insertMigrationLog(id,MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(), param);
            }
        }
        return new CommonResult("迁移产品数据迁移成功");
    }


    public void saveNewProduct(Param param, Long id, String code) {
        /**
         * 查询合作方的产品库
         */
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Long num = lifeProductMapper.getIdByCode(code);
        if (num == null) {
            String requestUrl = param.getUrl()+"/open/product/getOneFromUnion?id=" + id;
            JSONObject resultJson = httpService.get(requestUrl);
            if (resultJson != null && resultJson.getInteger("code") == 200) {
                LifeProductResult data = JsonUtils.jsonToPojo(resultJson.get("data").toString(), LifeProductResult.class);
                LifeProductVo lifeProductVo = new LifeProductVo();
                BeanUtils.copyProperties(data, lifeProductVo);
                lifeProductVo.setFirst_pay_count(1);//默认为1
//            lifeProductVo.setSale_way();//默认为空
//            lifeProductVo.setProposal_url();//默认为空
//            lifeProductVo.setSale_online_url();//默认为空
//            lifeProductVo.setQrcode_url();//默认为空
                lifeProductVo.setImage_url(data.getImageUrl());
                lifeProductVo.setList_image_url(data.getListImageUrl());
                if(data.getIsLongTerm()!=null){
                    lifeProductVo.setIs_long_term(data.getIsLongTerm().toString());
                }else{
                    lifeProductVo.setIs_long_term("");
                }
                if(data.getStatus()!=null){
                    lifeProductVo.setStatus(data.getStatus().toString());
                }else {
                    lifeProductVo.setStatus("");
                }
                lifeProductVo.setCommission_type("1");
                lifeProductVo.setAllow_age("0周岁-60周岁");
                if(data.getIsSaleOnline()!=null){
                    lifeProductVo.setIs_sale_online(data.getIsSaleOnline().toString());
                }else {
                    lifeProductVo.setIs_sale_online("");
                }
                lifeProductVo.setIs_proposal("0");//默认0
                lifeProductVo.setIs_green_service("0");//默认为0
                lifeProductVo.setIs_community("0");//默认为0
                lifeProductVo.setIs_order_online("0");//默认0
                lifeProductVo.setPrice(new BigDecimal(100));//默认为100
                if(data.getCategoryId()!=null){
                    lifeProductVo.setCategory(data.getCategoryId().toString());
                    /**
                     * 生成对应的标签
                     */
                    createTag(id,data.getCategoryId());
                }else {
                    lifeProductVo.setCategory("");
                }
                if(data.getCategoryId()!=null){
                    lifeProductVo.setCategory(data.getCategoryId().toString());
                }else {
                    lifeProductVo.setCategory("");
                }
                lifeProductVo.setName_short(data.getNameShort());
                if(data.getCompanyId()!=null){
                    lifeProductVo.setCompany_id(data.getCompanyId().toString());
                }else {
                    lifeProductVo.setCompany_id("");
                }
                if(data.getHesitateTime()!=null){
                    lifeProductVo.setHesitate_time(data.getHesitateTime().toString());
                }else {
                    lifeProductVo.setHesitate_time("");
                }
                if(data.getHesitateUnit()!=null){
                    lifeProductVo.setHesitate_unit(data.getHesitateUnit().toString());
                }else {
                    lifeProductVo.setHesitate_unit("");
                }
                lifeProductVo.setStart_time("2021-01-01");//默认值
                lifeProductVo.setEnd_time("2099-12-31");//默认值
                lifeProductVo.setRange("终身");//默认
                List<LifeProductVo.ContentsDTO> contentsDTOList = new ArrayList<>();
                List<LifeProductResult.ContentsDTO> contents = data.getContents();
                if (!CollectionUtils.isEmpty(contents)) {
                    for (LifeProductResult.ContentsDTO content : contents) {
                        LifeProductVo.ContentsDTO dto = new LifeProductVo.ContentsDTO();
                        if(content.getType()!=null){
                            dto.setType(content.getType().toString());
                        }else {
                            dto.setType("");
                        }
                        dto.setContent(content.getContent());
                        contentsDTOList.add(dto);
                    }
                }
                lifeProductVo.setContents(contentsDTOList);
                List<LifeProductVo.ImagesDTO> imagesDTOList = new ArrayList<>();
                List<LifeProductResult.ImagesDTO> images = data.getImages();
                if (!CollectionUtils.isEmpty(images)) {
                    for (LifeProductResult.ImagesDTO image : images) {
                        LifeProductVo.ImagesDTO dto = new LifeProductVo.ImagesDTO();
                        dto.setUrl(image.getUrl());
                        imagesDTOList.add(dto);
                    }
                }
                lifeProductVo.setImages(imagesDTOList);
                List<LifeProductVo.AdditionsDTO> additionsDTOList = new ArrayList<>();
                List<LifeProductResult.AdditionsDTO> additions = data.getAdditions();
                for (LifeProductResult.AdditionsDTO addition : additions) {
                    if(addition.getCode()!=null){
                        LifeProductVo.AdditionsDTO dto = new LifeProductVo.AdditionsDTO();
                        if(addition.getProductId()!=null){
                            dto.setProductId(addition.getProductId().toString());
                        }else{
                            dto.setProductId("");
                        }
                        if(addition.getCode()!=null){
                            dto.setCode(addition.getCode());
                        }else {
                            dto.setCode("");
                        }
                        dto.setCode(addition.getCode());
                        if(addition.getStatus()!=null){
                            dto.setStatus(addition.getStatus().toString());
                        }else {
                            dto.setStatus("");
                        }
                        additionsDTOList.add(dto);
                    }
                }
                lifeProductVo.setAdditions(additionsDTOList);


                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
//                String oldProduct = unLifeProductAllService.getCodeById(id);


                lifeProductVo.setTags(new ArrayList<>());
                if(data.getSource()!=null){
                    lifeProductVo.setSource(data.getSource().toString());
                }else {
                    lifeProductVo.setSource("");
                }
                List<LifeProductVo.DocumentsDTO> documentsDTOList = new ArrayList<>();

                List<LifeProductResult.DocumentsDTO> documents = data.getDocuments();
                if (!CollectionUtils.isEmpty(documents)) {
                    for (LifeProductResult.DocumentsDTO document : documents) {
                        LifeProductVo.DocumentsDTO dto = new LifeProductVo.DocumentsDTO();
                        dto.setName(document.getName());
                        dto.setUrl(document.getUrl());
                        if(document.getStatus()!=null){
                            dto.setStatus(document.getStatus().toString());
                        }else {
                            dto.setStatus("");
                        }
                        if(document.getType()!=null){
                            dto.setType(document.getType().toString());
                        }else{
                            dto.setType("");
                        }
                        dto.setCreatedAt(document.getCreatedAt());
                        documentsDTOList.add(dto);
                    }
                }
                lifeProductVo.setDocuments(documentsDTOList);
                String importUrl = param.getUrl()+"/open/product/import";
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(lifeProductVo));
                log.info("产品导入请求入参：{}",jsonObject);
                JSONObject importJson = httpService.post(importUrl, jsonObject);
                if (importJson != null && importJson.getInteger("code").equals(200)) {
                    insertMigrationLog(id, MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(),param);
                    log.info("产品code:{}产品迁移成功",code);
                }else if(importJson != null && importJson.getInteger("code").equals(1)){
                    log.info("该产品code:{}产品已存在，忽略",code);
                }else {
                    insertMigrationLog(id,MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(),param);
                }
            }
        }else {
            insertMigrationLog(id,MigrationStatusEnum.MIGRATION_STATUS_ALREADLY.getCode(),param);
            log.info("该合作方渠道：{},产品{}已存在，忽略",param.getChannelId(),code);
        }
    }

    public void createTag(Long productId,Integer categoryId){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer num = lifeProductTagMapMapper.getCount(productId);
        if(num==0){
            Integer tagId=0;
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            ProductCategory category = productCategoryMapper.getById(intToLong(categoryId));
            if(ReflectUtil.isNotNull(category)){
                if(category.getName()!=null && !"".equals(category.getName())){
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    if(Arrays.asList(Const.TAG_定期终身寿).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("定期/终身寿");
                    }else if(Arrays.asList(Const.TAG_年金保险).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("年金保险");
                    }else if(Arrays.asList(Const.TAG_意外险).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("意外险");
                    }else if(Arrays.asList(Const.TAG_医疗保险).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("医疗保险");
                    }else if(Arrays.asList(Const.TAG_疾病保障).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("疾病保障");
                    }else if(Arrays.asList(Const.TAG_非车险).contains(category.getName())){
                        tagId = lifeProductTagMapper.getIdByName("非车险");
                    }else {
                        tagId = lifeProductTagMapper.getIdByName("非车险");
                    }
                }
            }
            LifeProductTagMap lifeProductTagMap = new LifeProductTagMap();
            lifeProductTagMap.setIsDeleted(0);
            lifeProductTagMap.setType(1);
            lifeProductTagMap.setLifeProductId(productId);
            lifeProductTagMap.setLifeProductTagId(tagId);
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            lifeProductTagMapMapper.insert(lifeProductTagMap);
            log.info("产品id{},产品标签生成成功",productId);
        }else {
            log.info("产品id{},产品标签已存在，忽略",productId);
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
