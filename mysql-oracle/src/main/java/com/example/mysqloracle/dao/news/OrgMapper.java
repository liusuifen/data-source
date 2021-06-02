package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 组织架构 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

    @Select("select id, parent_id as parentId, parent_id_json as parentIdJson, type, level, source_partner_id as sourcePartnerId, " +
            "source_org_id as sourceOrgId, source_parent_id as sourceParentId, org_channel_id as orgChannelId, name, code, logo, " +
            "master_user_id as masterUserId, master, contacts, tel, status, belong_provice_code as belongProviceCode, belong_city_code as belongCityCode, " +
            "license_type as licenseType, license_number as licenseNumber, channel_type as channelType, is_channel as isChannel, is_online_license as isOnlineLicense, " +
            "online_number as onlineNumber, is_partner as isPartner, is_sync as isSync, certificate_type as certificateType, certificate_number as certificateNumber, " +
            "level_order as levelOrder, pay_day as payDay,  system_user_id as systemUserId, is_deleted as isDeleted, created_at as createdAt, " +
            "updated_at as updatedAt, config_agent_contract as configAgentContract, api_date as apiDate, app_key as appKey, key from org " +
            "where id=#{id}")
    Org getById(@Param("id") Long id);


    @Select("SELECT id from org  where `level` in (1,2,3,4)  and `status`=1 and is_deleted=0 order by level_order asc")
    List<Long> getOrg();

}
