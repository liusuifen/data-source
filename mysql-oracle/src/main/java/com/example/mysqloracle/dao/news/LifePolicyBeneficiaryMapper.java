package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyBeneficiary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单受益人表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Mapper
public interface LifePolicyBeneficiaryMapper extends BaseMapper<LifePolicyBeneficiary> {


    @Select("select count(*) from life_policy_beneficiary where life_policy_id=#{lifePolicyId}")
    Integer getCountBypolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_beneficiary where id=#{id}")
    Integer getCountById(@Param("id") Long id);


    @Select("select id, life_policy_id as lifePolicyId, name, gender, birthday, id_type as idType, id_no as idNo, " +
            "id_concat as idConcat, id_expired_date as idExpiredDate, email, mobile, relation, relation_name as relationName, " +
            "`rank`, ratio, province, city, district, address, type, is_deleted as isDeleted, height, weight, nationality, zip " +
            "from life_policy_beneficiary where life_policy_id=#{lifePolicyId}")
    List<LifePolicyBeneficiary> selectByPolicyId(@Param("lifePolicyId") Long lifePolicyId);

    @Select("select count(*) from life_policy_beneficiary where  LENGTH(life_policy_id)<=5; ")
    Integer selectByChannelId();


}
