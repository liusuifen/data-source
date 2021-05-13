package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.MigrationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 数据迁移日志表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-12
 */
@Mapper
public interface MigrationLogMapper extends BaseMapper<MigrationLog> {


    @Select("select id, old_id as oldId, status, type, created_at as createdAt, updated_at as updatedAt from migration_log where old_id=#{oldId}")
    MigrationLog getByOldId(@Param("oldId") Long oldId);

    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=2")
    List<Integer> getErrorPolicy(@Param("sourcePartnerId") Long sourcePartnerId);

    @Update("update migration_log set updated_at =#{updatedAt},status=#{status} where old_id=#{oldId}")
    Integer updateAt(@Param("updatedAt")LocalDateTime updatedAt,@Param("status")Integer status,@Param("oldId") Long oldId);

}
