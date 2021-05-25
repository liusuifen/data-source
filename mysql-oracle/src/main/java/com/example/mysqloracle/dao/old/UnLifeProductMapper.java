package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险渠道产品档 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-18
 */
@Mapper
public interface UnLifeProductMapper extends BaseMapper<UnLifeProduct> {

    @Select("select product_id from un_life_product where id=#{id}")
    Integer getCodeById(@Param("id") Integer id);



}
