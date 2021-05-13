package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyProgress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-保单进度变更表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface LifePolicyProgressMapper extends BaseMapper<LifePolicyProgress> {

    @Select("select count(*) from life_policy_progress where life_policy_id=#{lifePolicyId}")
    Integer getCountByPolicyId(@Param("lifePolicyId") Long lifePolicyId);

    @Select("select count(*) from life_policy_progress where id=#{id}")
    Integer getCountById(@Param("id") Long id);

}
