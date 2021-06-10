package com.example.mysqloracle.dao.news;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.SystemRank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统管理-职级维护表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Mapper
public interface SystemRankMapper extends BaseMapper<SystemRank> {

    @Select("select id, org_id as orgId, number, type, name, status, is_manage as isManage, level, is_deleted as isDeleted, created_at as createdAt, updated_at as updatedAt " +
            "from system_rank " +
            "where id=#{id}")
    SystemRank getById(@Param("id")Long id);


    @Select("select id from system_rank where `status`=2 and is_deleted=0 and type=2 ")
    List<Long> getAllID();

}
