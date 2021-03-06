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

    @Select("select count(*) from content_course_type where name=#{name}")
    Integer getByName(@Param("name") String name);


    @Select("select id from content_course_type where name=#{name}")
    Integer getByCategoryName(@Param("name") String name);



    @Select("select sort from content_course_type where is_deleted=0 order by sort desc limit 1")
    Integer getSort();

}
