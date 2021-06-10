package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.ContentAd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 内容管理-广告位管理 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Mapper
public interface ContentAdMapper extends BaseMapper<ContentAd> {

    @Select("select id, content_ad_type_id as contentAdTypeId, pic, description, url, status, sort, " +
            "publish_start as publishStart, publish_end as publishEnd, user_id as userId, is_deleted as isDeleted, " +
            "created_at as createdAt, updated_at as updatedAt from content_ad " +
            "where id=#{id}")
    ContentAd getById(@Param("id") Long id);

}
