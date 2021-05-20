package com.example.mysqloracle.dao.news.admin;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.admin.PartnerLifePolicyHolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-投保人 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
@Mapper
public interface PartnerLifePolicyHolderMapper extends BaseMapper<PartnerLifePolicyHolder> {

    @Select("select count(*) from partner_life_policy_holder where life_policy_id=#{lifePolicyId}")
    Integer getCount(@Param("lifePolicyId") Long lifePolicyId);

}
