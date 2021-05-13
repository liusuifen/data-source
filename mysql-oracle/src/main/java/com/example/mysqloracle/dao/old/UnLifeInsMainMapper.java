package com.example.mysqloracle.dao.old;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeInsMain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Mapper
public interface UnLifeInsMainMapper extends BaseMapper<UnLifeInsMain> {

    @Select("select id, channel_id as channelId, order_no as channelId, policy_no as policyNo, policy_sn as policySn, " +
            "uw_medical_id as uwMedicalId, supplier_id as supplierId, sales_channel as salesChannel, sales_dept as salesDept, " +
            "ins_area as insArea, user_id as userId, rank_id as rankId, holder_name as holderName, holder_gender as holderGender, " +
            "holder_birthday as holderBirthday, holder_nation as holderNation, holder_ID_type as holderIdType, holder_ID_no as holderIdNo, " +
            "holder_ID_expire_end as holderIdExpireEnd, holder_marriage as holderMarriage, holder_height as holderHeight, holder_weight  as holderWeight, " +
            "holder_salary as holderSalary, holder_job_code as holderJobCode, holder_company as holderCompany, holder_position as holderPosition, " +
            "holder_company_address as holderCompanyAddress, holder_salary_avg as holderSalaryAvg, holder_salary_from as holderSalaryFrom," +
            " holder_home_province as holderHomeProvince, holder_home_city as holderHomeCity, holder_home_district as holderHomeDistrict, " +
            "holder_home_address as holderHomeAddress, holder_home_zip as holderHomeZip, holder_contact_province as holderContactProvince, " +
            "holder_contact_city as holderContactCity, holder_contact_district  as holderContactDistrict, holder_contact_address as holderContactAddress," +
            " holder_contact_zip as holderContactZip, holder_phone as holderPhone , holder_mobile as holderMobile, holder_office_phone as holderOfficePhone, " +
            "holder_is_sms as holderIsSms, holder_email as holderEmail, holder_has_SSID as holderHasSsid, holder_education as holderEducation, " +
            "bank_area as bankArea, bank_name  as bankName, bank_type as bankType, bank_no as bankNo, bank_holder as bankHolder, val_date as valDate, " +
            "grace_end_date as graceEndDate, total as total, progress as progress, progress_time as progressTime, is_first as isFirst, " +
            "is_legal_benefic as isLegalBenefic, extend_items as extendItems, img_json as imgJson,  state as state, pay_status as payStatus, is_temp as isTemp, " +
            "remark, is_property as isProperty, create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime , " +
            "war_id as warId , delete_time as deleteTime " +
            "from un_life_ins_main where 1=1 " +
            "and channel_id=#{channelId} " +
            "and is_first=1 " +
            "and delete_time is null")
    List<UnLifeInsMain> getAll(@Param("channelId") Integer channelId);




    @Select("select id, channel_id as channelId, order_no as channelId, policy_no as policyNo, policy_sn as policySn, " +
            "uw_medical_id as uwMedicalId, supplier_id as supplierId, sales_channel as salesChannel, sales_dept as salesDept, " +
            "ins_area as insArea, user_id as userId, rank_id as rankId, holder_name as holderName, holder_gender as holderGender, " +
            "holder_birthday as holderBirthday, holder_nation as holderNation, holder_ID_type as holderIdType, holder_ID_no as holderIdNo, " +
            "holder_ID_expire_end as holderIdExpireEnd, holder_marriage as holderMarriage, holder_height as holderHeight, holder_weight  as holderWeight, " +
            "holder_salary as holderSalary, holder_job_code as holderJobCode, holder_company as holderCompany, holder_position as holderPosition, " +
            "holder_company_address as holderCompanyAddress, holder_salary_avg as holderSalaryAvg, holder_salary_from as holderSalaryFrom," +
            " holder_home_province as holderHomeProvince, holder_home_city as holderHomeCity, holder_home_district as holderHomeDistrict, " +
            "holder_home_address as holderHomeAddress, holder_home_zip as holderHomeZip, holder_contact_province as holderContactProvince, " +
            "holder_contact_city as holderContactCity, holder_contact_district  as holderContactDistrict, holder_contact_address as holderContactAddress," +
            " holder_contact_zip as holderContactZip, holder_phone as holderPhone , holder_mobile as holderMobile, holder_office_phone as holderOfficePhone, " +
            "holder_is_sms as holderIsSms, holder_email as holderEmail, holder_has_SSID as holderHasSsid, holder_education as holderEducation, " +
            "bank_area as bankArea, bank_name  as bankName, bank_type as bankType, bank_no as bankNo, bank_holder as bankHolder, val_date as valDate, " +
            "grace_end_date as graceEndDate, total as total, progress as progress, progress_time as progressTime, is_first as isFirst, " +
            "is_legal_benefic as isLegalBenefic, extend_items as extendItems, img_json as imgJson,  state as state, pay_status as payStatus, is_temp as isTemp, " +
            "remark, is_property as isProperty, create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime , " +
            "war_id as warId , delete_time as deleteTime " +
            "from un_life_ins_main where 1=1 " +
            "and id=#{id}")
    UnLifeInsMain getByPolicyId(@Param("id") Integer id);


    @Select("SELECT extend_items FROM `un_life_ins_main` where extend_items like '%isTaxResidents%' and is_first=1 and delete_time is null")
    List<String> getExtendItems();

}