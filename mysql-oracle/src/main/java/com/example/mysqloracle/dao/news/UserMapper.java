package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select id, source_user_id as sourceUserId, real_name as realName, org_id as orgId, org_channel_id as orgChannelId, team_id as teamId, " +
            "sex, role_type as roleType, type, is_channel as isChannel, account, mobile, job_number as jobNumber, parent_id as parentId, " +
            "teach_id as teachId, recommend_id as recommendId, status, account_status as accountStatus, join_time as joinTime, service_time as serviceTime, " +
            "job_rank as jobRank, certificate_type as certificateType, certificate_number as certificateNumber, birthday, nation, " +
            "native_place_province_code as nativePlaceProvinceCode, native_place_city_code as nativePlaceCityCode, political, " +
            "salary_type as salaryType, is_manage as isManage, is_admin as isAdmin, train_status as trainStatus, marry_status as marryStatus, education, remark, " +
            "punch_type as punchType, promote_province_code as promoteProvinceCode, promote_city_code as promoteCityCode, professional_certificate_number as professionalCertificateNumber, " +
            "is_send as isSend, import_way as importWay, is_deleted as isDeleted, created_at as createdAt, updated_at as updatedAt from user " +
            "where id=#{id}")
    User getById(@Param("id") Long id);



    @Select("select team_id from  user where id=#{id}")
    Long getTeamById(@Param("id") Long id);

}
