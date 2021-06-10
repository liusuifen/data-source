package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ContentPoster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 内容管理-海报管理 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Mapper
public interface ContentPosterMapper extends BaseMapper<ContentPoster> {

    @Select("select id, name, status, sort, item, is_festival as isFestival, user_id as userId, is_deleted as isDeleted, " +
            "created_at as createdAt, updated_at as updatedAt from content_poster where name=#{name}")
    ContentPoster getByName(@Param("name") String name);



}
