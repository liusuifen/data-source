package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ContentCourseType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 内容管理-课程类型 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Mapper
public interface ContentCourseTypeMapper extends BaseMapper<ContentCourseType> {

    @Select("select count(*) from content_course_type where id=#{id}")
    Integer getById(@Param("id") Long id);

}
