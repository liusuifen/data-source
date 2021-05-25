package com.example.mysqloracle.dao.news;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProductRatioTemplateMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * <p>
 * 产品价值系数表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@Mapper
public interface LifeProductRatioTemplateMapMapper extends BaseMapper<LifeProductRatioTemplateMap> {

    @Select("select count(*) from life_product_ratio_template_map where template_id=#{templateId}")
    Integer selectByTemplateId(@Param("templateId") Integer templateId);


    @Select("select count(*) from life_product_ratio_template_map where life_product_id=#{lifeProductId} and org_id=#{orgId}")
    Integer selectByLifeProductIdAndOrgId(@Param("lifeProductId") Long lifeProductId,@Param("orgId") Long orgId);


}
