package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单-险种信息表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface LifePolicyProductMapper extends BaseMapper<LifePolicyProduct> {

    @Select("select count(*) from life_policy_product where life_policy_id=#{lifePolicyId}")
    Integer getCountByPolicyId(@Param("lifePolicyId") Long lifePolicyId);

    @Select("select count(*) from life_policy_product where id=#{id}")
    Integer getCountById(@Param("id") Long id);


    @Select("select id, life_policy_id as lifePolicyId, life_product_id as lifeProductId, name, code, category_id as categoryId, category_name as categoryName, " +
            "amount, safe_year as safeYear, safe_year_unit as safeYearUnit, pay_year as payYear, pay_year_unit as payYearUnit, pay_way as payWay, pay_frequency as payFrequency, " +
            "fee, std_fee as stdFee, ratio, is_primary as isPrimary, is_deleted as isDeleted, extension, hesitate_time as hesitateTime , hesitate_unit as hesitateUnit, " +
            "commission_way as commissionWay, commission_way_renewal as commissionWayRenewal " +
            "from life_policy_product where life_policy_id=#{lifePolicyId}")
    List<LifePolicyProduct> selectByPolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_product where  LENGTH(life_policy_id)<=5; ")
    Integer selectByChannelId();
}
