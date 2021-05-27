package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyInsured;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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




    @Select("select id, life_policy_id as lifePolicyId, name, gender, birthday, relation, relation_name as relationName, id_type as idType, " +
            "id_no as idNo, id_concat as idConcat, id_expired_date as idExpiredDate, height, weight, marriage, mobile, email, is_ssid as isSsid, " +
            "address, province, city, district, company, income, income_source as incomeSource, is_deleted as isDeleted, nationality, is_tax as isTax, " +
            "zip, extension, job_code as jobCode " +
            "from life_policy_insured " +
            "where life_policy_id=#{lifePolicyId}")
    List<LifePolicyInsured> selectByPolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_insured where LENGTH(life_policy_id)=5; ")
    Integer selectByChannelId();

}
