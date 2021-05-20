package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-投保单主表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Mapper
public interface LifePolicyMapper extends BaseMapper<LifePolicy> {

    @Select("select * from life_policy where id=#{id}")
    LifePolicy getById(@Param("id") Long id);



    @Select("select id, source_partner_id as sourcePartnerId , order_no as orderNo, policy_no as policyNo, policy_sn as policySn, company , " +
            "company_type as companyType, progress, period, status, is_legal_beneficiary as isLegalBeneficiary, remark, date_start as dateStart, " +
            "date_end as dateEnd, date_deduct as dateDeduct, date_accept as dateAccept, date_advance as dateAdvance, is_sale_self as isSaleSelf, " +
            "created_at as createdAt, updated_at as updatedAt, service_user_json as serviceUserJson, import_way as importWay, source, pay_way as payWay, " +
            "pay_frequency as payFrequency, pay_year as payYear, pay_year_unit as payYearUnit, safe_year as safeYear, safe_year_unit as safeYearUnit, " +
            "notification_status as notificationStatus, update_user_json as updateUserJson, import_user_json as importUserJson, holder_json as holderJson, " +
            "insured_json as insuredJson, product_json as productJson, fee, std_fee as stdFee, progress_result as progressResult, update_user_id as updateUserId, " +
            "product_id as productId, product_category_id as productCategoryId, import_user_id as importUserId, sales_user_json as salesUserJson, sales_user_id as salesUserId, " +
            "sales_user_org_id as salesUserOrgId, sales_user_rank_id as salesUserRankId, is_partner as isPartner, change_type as changeType, partner_param as partnerParam, " +
            "service_agent_type as serviceAgentType, service_user_id as serviceUserId, is_channel as isChannel, company_json as companyJson,  is_after_hesitate as isAfterHesitate, " +
            "client, approval_status as approvalStatus, approval_remark as approvalRemark, draft_step as draftStep, contract_type as contractType, contract_id as contractId, " +
            "amount, is_deleted as isDeleted, extension, deduct_company_commission as deductCompanyCommission, deduct_commission as deductCommission, deduct_score as deductScore, " +
            "is_deduct as isDeduct, sales_team_id as salesTeamId, sales_channel_id as salesChannelId, commission_status as commissionStatus, " +
            "sales_channel_org_id as salesChannelOrgId, pay_status as payStatus " +
            "from life_policy where id=#{id}")
    LifePolicy getByIds(@Param("id") Long id);


}
