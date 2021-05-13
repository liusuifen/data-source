package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeInsBeneficiary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单-受益人信息-子表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Mapper
public interface UnLifeInsBeneficiaryMapper extends BaseMapper<UnLifeInsBeneficiary> {


    @Select("select id, channel_id as channelId, policy_id as policyId, fullname, gender, birthday, ID_type as idType, " +
            "ID_no as idNo, ID_expire_end as idExpireEnd, nation, tel, beneficiary_is_insured as beneficiaryIsInsured, sort_order as sortOrder, " +
            "rate, addr_type as addrType, job_code as jobCode, tax_type as taxType, province, city, district, address, zip, type, " +
            "death_get_type as deathGetType, death_get_rate as deathGetRate, death_get_ratio as deathGetRatio, death_get_start as deathGetStart, " +
            "death_get_end as deathGetEnd, death_rate as deathRate, state, create_by as createBy, create_time as createTime, modify_by as modifyBy, " +
            "modify_time as modifyTime, bene_id as beneId, delete_time as deleteTime " +
            " from un_life_ins_beneficiary where policy_id=#{policyId}")
    List<UnLifeInsBeneficiary> getByPolicyId(@Param("policyId") Integer policyId);



}
