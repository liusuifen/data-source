package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyInsured;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-被保人 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface LifePolicyInsuredMapper extends BaseMapper<LifePolicyInsured> {


    @Select("select count(*) from life_policy_insured where life_policy_id=#{lifePolicyId}")
    Integer getCountBypolicyId(@Param("lifePolicyId") Long lifePolicyId);

    @Select("select count(*) from life_policy_insured where id=#{id}")
    Integer getCountById(@Param("id") Long id);

}
