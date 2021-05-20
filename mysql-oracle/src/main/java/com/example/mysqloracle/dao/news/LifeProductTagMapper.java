package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProductTag;
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
public interface LifeProductTagMapper extends BaseMapper<LifeProductTag> {

    @Select("select id from life_product_tag where type=1 and is_deleted=0 and name=#{name}")
    Integer getIdByName(@Param("name") String name);

}
