package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ContentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 内容管理-课程表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Mapper
public interface ContentCourseMapper extends BaseMapper<ContentCourse> {

    @Select("select count(*) from content_course where id =#{id}")
    Integer getCountById(@Param("id") Long id);


    @Select("select id, content_course_type_id as contentCourseTypeId, cover, title, author, introduction, " +
            "receive_org as receiveOrg, parent_org as parentOrg, total_org as totalOrg, is_share as isShare, receive_rank as receiveRank, " +
            "is_learn as isLearn, receive_channel as receiveChannel, is_visible as isVisible, status, attendance_time as attendanceTime, " +
            "clock_in_hour as clockInHour, clock_in_minute as clockInMinute, clock_in_second as clockInSecond,  " +
            "platform_oriented as platformOriented, upload_type as uploadType, created_user_id as createdUserId, publish_time as publishTime, " +
            "user_id as userId, view, is_deleted as isDeleted, created_at as createdAt, updated_at as updatedAt " +
            "from content_course where id=#{id}")
    ContentCourse getById(@Param("id") Long id);

}
