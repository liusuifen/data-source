package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ContentCourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 内容管理-课程信息表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Mapper
public interface ContentCourseInfoMapper extends BaseMapper<ContentCourseInfo> {

    @Select("select count(*) from content_course_info where content_course_id=#{contentCourseId}")
    Integer getCountById(@Param("contentCourseId") Long contentCourseId);

}
