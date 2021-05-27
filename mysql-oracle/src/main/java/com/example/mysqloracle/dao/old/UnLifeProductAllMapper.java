package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeProductAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    String getCodeById(@Param("id") Integer id);


    @Select("SELECT CODE \n" +
            "FROM\n" +
            "\tun_life_product_all \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT\n" +
            "\t\tproduct_id \n" +
            "\tFROM\n" +
            "\t\tun_life_product \n" +
            "\tWHERE\n" +
            "\t\tchannel_id = #{channelId} \n" +
            "\t\tAND delete_time IS NULL \n" +
            "GROUP BY\n" +
            "\tproduct_id)")
    List<String> selectProductCode(@Param("channelId") Integer channelId);

}
