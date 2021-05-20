package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeInsInsured;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单-被保险人信息-子表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface UnLifeInsInsuredMapper extends BaseMapper<UnLifeInsInsured> {


    @Select("select  id, channel_id as channelId, policy_id as policyId, insured_name as insuredName, insured_gender as insuredGender, " +
            "insured_birthday as insuredBirthday, insured_nation as insuredNation, insured_ID_type as insuredIdType, insured_ID_no as insuredIdNo, " +
            "insured_ID_expire_end as insuredIdExpireEnd, insured_height as insuredHeight, insured_weight as insuredWeight, insured_marriage as insuredMarriage, " +
            "insured_job_code as insuredJobCode, insured_company as insuredCompany, insured_position as insuredPosition, insured_company_address as insuredCompanyAddress, " +
            "insured_company_zip as insuredCompanyZip, insured_salary_avg as insuredSalaryAvg, insured_salary_from as insuredSalaryFrom, insured_home_province as insuredHomeProvince , " +
            "insured_home_city as insuredHomeCity, insured_home_district as insuredHomeDistrict, insured_home_address as insuredHomeAddress, " +
            "insured_home_zip as insuredHomeZip, insured_contact_province as insuredContactProvince, insured_contact_city as insuredContactCity, " +
            "insured_contact_district as insuredContactDistrict, insured_contact_address as insuredContactAddress, insured_contact_zip as insuredContactZip, " +
            "insured_mobile as insuredMobile, insured_phone as insuredPhone, insured_office_phone as insuredOfficePhone, insured_is_sms as insuredIsSms, " +
            "insured_email as insuredEmail, has_insured_SSID as hasInsuredSsid, insured_education as insuredEducation, rel_holder_insured as relHolderInsured, " +
            "rel_insured_holder as relInsuredHolder, extend_items as extendItems, state, create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime, " +
            "assu_id as assuId, delete_time as deleteTime from un_life_ins_insured " +
            "where policy_id=#{policyId} " +
            "and delete_time is null")
    List<UnLifeInsInsured> getByPolicyId(@Param("policyId") Integer policyId);

}
