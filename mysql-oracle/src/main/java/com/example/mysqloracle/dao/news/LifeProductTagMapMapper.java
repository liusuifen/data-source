package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProductTagMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 标签库表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-18
 */
@Mapper
public interface LifeProductTagMapMapper extends BaseMapper<LifeProductTagMap> {

    @Select("select count(*) from life_product_tag_map where life_product_id=#{lifeProductId} and is_deleted = 0")
    Integer getCount(@Param("lifeProductId") Long lifeProductId);

}
