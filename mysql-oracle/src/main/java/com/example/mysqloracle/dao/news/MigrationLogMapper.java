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

    /**
     * 根据id查询该数据是否存在迁移记录
     * @param oldId
     * @return
     */
    @Select("select id, old_id as oldId, status, type, created_at as createdAt, updated_at as updatedAt from migration_log where old_id=#{oldId} and type=#{type} limit 1")
    MigrationLog getByOldId(@Param("oldId") Long oldId,@Param("type") Integer type);


    /**
     * 根据id查询该数据是否存在迁移记录
     * @param oldId
     * @return
     */
    @Select("select id, old_id as oldId, status, type, created_at as createdAt, updated_at as updatedAt from migration_log where old_id=#{oldId} and type=3 limit 1")
    MigrationLog getByOldIdAndType(@Param("oldId") Long oldId);

    /**
     * 获取迁移失败的保单id
     * @param sourcePartnerId
     * @return
     */
    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=2 and type=1")
    List<Integer> getErrorPolicy(@Param("sourcePartnerId") Long sourcePartnerId);

    /**
     * 获取迁移失败的保单id
     * @param sourcePartnerId
     * @return
     */
    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=2 and type=5")
    List<Integer> getErrorCourse(@Param("sourcePartnerId") Long sourcePartnerId);

    /**
     * 获取全部迁移成功的保单id
     */
    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=1 and type=1")
    List<Integer> getSuccessPolicy(@Param("sourcePartnerId") Long sourcePartnerId);


    /**
     * 获取全部迁移成功的保单id
     */
    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=2 and type=3")
    List<Integer> getErrorSync(@Param("sourcePartnerId") Long sourcePartnerId);

    /**
     * 获取迁移失败的产品id
     * @param sourcePartnerId
     * @return
     */
    @Select("select old_id from migration_log where  source_partner_id=#{sourcePartnerId} and status=2 and type=2")
    List<Long> getErrorProduct(@Param("sourcePartnerId") Long sourcePartnerId);

    /**
     * 更新迁移状态
     */
    @Update("update migration_log set updated_at =#{updatedAt},status=#{status} where old_id=#{oldId} and type=#{type}")
    Integer updateAt(@Param("updatedAt")LocalDateTime updatedAt,@Param("status")Integer status,@Param("oldId") Long oldId,@Param("type") Integer type);

}
