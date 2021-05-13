package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeProductAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险总档 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Mapper
public interface UnLifeProductAllMapper extends BaseMapper<UnLifeProductAll> {


    @Select("SELECT code FROM un_life_product_all  where id  in (select product_id from un_life_product where id =#{id})")
    String getCOdeById(@Param("id") Integer id);

}
