package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 客户中心-客户表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-19
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("select count(*) from customer where certificate_type=#{certificateType} and certificate_number=#{certificateNumber} and user_id=#{userId} and is_deleted=0" )
    Integer selectIdTypeNo(@Param("certificateType") Integer certificateType,@Param("certificateNumber") String certificateNumber,@Param("userId") Long userId);


}
