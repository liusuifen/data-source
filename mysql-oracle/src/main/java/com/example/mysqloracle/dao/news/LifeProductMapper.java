package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险产品库 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface LifeProductMapper extends BaseMapper<LifeProduct> {


    @Select("select  id, type, company_id as companyId, code, name, name_short as nameShort, cycle, price, " +
            "image_url as imageUrl,`range`, category_id as categoryId, is_api as isApi, is_green_service as isGreenService, is_community as isCommunity, " +
            "is_sale_online as isSaleOnline, status, start_time as startTime, end_time as endTime, hesitate_time as hesitateTime, " +
            "hesitate_unit as hesitateUnit, system_user_id as systemUserId, created_at as createdAt, updated_at as updatedAt, is_deleted as isDeleted, " +
            "sale_way as saleWay, qrcode_url as qrcodeUrl, proposal_url as proposalUrl, sale_online_url as saleOnlineUrl, commission_way as commissionWay, " +
            "commission_way_renewal as commissionWayRenewal, allow_age as allowAge, is_proposal as isProposal, first_pay_count as firstPayCount, " +
            "is_order_online as isOrderOnline, is_long_term as isLongTerm, is_set_commission_ratios as isSetCommissionRatios, " +
            "list_image_url as listImageUrl, is_add_api as isAddApi, is_add_proposal as isAddProposal, add_from as addFrom, source, is_auto_sync as isAutoSync from life_product " +
            "where id=#{id}")
    LifeProduct  getById(@Param("id") Long id);


    @Select("select id from life_product where `code` = #{code} and is_deleted=0;")
    Long getIdByCode(@Param("code") String Code);



}
