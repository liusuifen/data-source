package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyDocument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单相关文档表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-11
 */
@Mapper
public interface LifePolicyDocumentMapper extends BaseMapper<LifePolicyDocument> {

    @Select("select count(*) from life_policy_document where life_policy_id=#{lifePolicyId} and type='106' and is_deleted=0")
    Integer getByPolicyIdAndType(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select id, life_policy_id as lifePolicyId, name, url, created_at as createdAt, type, " +
            "is_deleted as isDeleted, upload_user as uploadUser, upload_user_id as uploadUserId from life_policy_document " +
            "where life_policy_id=#{lifePolicyId} " +
            "and type='106' " +
            "and is_deleted=0")
    List<LifePolicyDocument> selectByPolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_document where  LENGTH(life_policy_id)=5; ")
    Integer selectByChannelId();

}
