package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyDocument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select count(*) from life_policy_document where life_policy_id=#{lifePolicyId} and type='102' and is_deleted=0")
    Integer getByPolicyIdAndType(@Param("lifePolicyId") Long lifePolicyId);

}
