package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 保险公司 - 基本信息 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {


    @Select("select id, admin_id as adminId, parent_id as parentId, code, full_name as fullName, " +
            "short_name as shortName, category, license_type as licenseType, has_net_license as hasNetLicense, " +
            "province, city, district, address, location_province as locationProvince, location_city as locationCity, logo, " +
            "company_type as companyType, status, id_type as idType, id_no as idNo, boss_name as bossName, taxpayer, " +
            "taxpayer_id_no as taxpayerIdNo, bank_name as bankName, account_name as accountName, account_no as accountNo, " +
            "taxpayer_address as taxpayerAddress, taxpayer_phone_number as taxpayerPhoneNumber, last_ratio_type as lastRatioType, " +
            "system_user_id as systemUserId, is_deleted as isDeleted, created_at as createdAt, updated_at as updatedAt from company " +
            "where id=#{id}")
    Company getById(@Param("id") Long id);
}
