package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-保单状态变更表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-11
 */
@Mapper
public interface LifePolicyStatusMapper extends BaseMapper<LifePolicyStatus> {

    @Select("select id, life_policy_id as lifePolicyId, status, remark, date, created_at as createdAt, update_user as updateUser, " +
            "update_user_id from life_policy_status where life_policy_id=#{lifePolicyId} ")
    LifePolicyStatus getByPolicyId(@Param("lifePolicyId") Long lifePolicyId);

    int insertLifePolicyStatus(LifePolicyStatus lifePolicyStatus);

}