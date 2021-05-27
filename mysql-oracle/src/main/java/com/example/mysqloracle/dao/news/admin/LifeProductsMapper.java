package com.example.mysqloracle.dao.news.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.admin.LifeProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 寿险产品库 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-27
 */
@Mapper
public interface LifeProductsMapper extends BaseMapper<LifeProduct> {


    @Select("select code from life_product where  code!=''")
    List<String> selectProductCode();

}
