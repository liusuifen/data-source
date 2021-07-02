package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyHolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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


    @Select("select life_policy_id as lifePolicyId, name, gender, birthday, id_type as idType, id_no as idNo, id_concat as idConcat, " +
            "id_expired_date as idExpiredDate, marriage, height, weight, income, income_source as incomeSource, company, mobile, email, " +
            "is_ssid as isSsid, bank_name as bankName, bank_no as bankNo, bank_holder as bankHolder, is_tax as isTax, address, province, " +
            "city, district, nationality, zip, extension, job_code as jobCode " +
            "from life_policy_holder where life_policy_id=#{lifePolicyId}")
    LifePolicyHolder getById(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_holder where life_policy_id=#{lifePolicyId}")
    Integer getCountBypolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select life_policy_id as lifePolicyId, name, gender, birthday, id_type as idType, id_no as idNo, id_concat as idConcat, " +
            "id_expired_date as idExpiredDate, marriage, height, weight, income, income_source as incomeSource, company, mobile, email, " +
            "is_ssid as isSsid, bank_name as bankName, bank_no as bankNo, bank_holder as bankHolder, is_tax as isTax, address, province, " +
            "city, district, nationality, zip, extension, job_code as jobCode " +
            "from life_policy_holder where life_policy_id=#{lifePolicyId}")
    List<LifePolicyHolder> selectBypolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_holder where  LENGTH(life_policy_id)<=5; ")
    Integer selectByChannelId();



}
