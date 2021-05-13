package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyHolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-投保人 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface LifePolicyHolderMapper extends BaseMapper<LifePolicyHolder> {


    @Select("select * from life_policy_holder where life_policy_id =#{lifePolicyId}")
    LifePolicyHolder getById(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_holder where life_policy_id=#{lifePolicyId}")
    Integer getCountBypolicyId(@Param("lifePolicyId") Long lifePolicyId);
}
