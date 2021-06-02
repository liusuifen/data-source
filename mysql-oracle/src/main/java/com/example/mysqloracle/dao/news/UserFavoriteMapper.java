package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.UserFavorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户收藏表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-06-01
 */
@Mapper
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

    @Select("select count(*) from user_favorite where id=#{id}")
    Integer getCountById(@Param("id") Integer id);




}
