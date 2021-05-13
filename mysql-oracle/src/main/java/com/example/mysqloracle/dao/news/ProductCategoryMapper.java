package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 产品大类表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    @Select("select id, name, status from product_category where id=#{id}")
    ProductCategory getById(@Param("id") Long id);

}
