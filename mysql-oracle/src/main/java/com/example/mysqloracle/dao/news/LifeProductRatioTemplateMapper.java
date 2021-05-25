package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProductRatioTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * <p>
 * 价值系数模板表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@Mapper
public interface LifeProductRatioTemplateMapper extends BaseMapper<LifeProductRatioTemplate> {



    @Select("select id, name, status, system_user_id as systemUserId, created_at as createdAt, updated_at as updatedAt, " +
            "is_deleted as isDeleted , life_product_id as lifeProductId from life_product_ratio_template where life_product_id=#{lifeProductId}")
    LifeProductRatioTemplate selectByProductId(@Param("lifeProductId") Long lifeProductId);

}
