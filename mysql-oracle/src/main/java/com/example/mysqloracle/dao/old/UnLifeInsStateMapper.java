package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeInsState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单-状态跟踪-影像记录列表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface UnLifeInsStateMapper extends BaseMapper<UnLifeInsState> {


    @Select("select id, channel_id as channelId, policy_id as policyId, progress, picture, " +
            "file_time as fileTime, create_by as createBy, create_time as createTime, " +
            "modify_by as modifyBy, modify_time as modifyTime, wp_id as wpId, delete_time as deleteTime from un_life_ins_state " +
            "where policy_id =#{policyId} " +
            "and delete_time is null ")
    List<UnLifeInsState> getByPolicyId(@Param("policyId") Integer policyId);


    @Select("select count(*) from un_life_ins_state where policy_id in (select id from un_life_ins_main where channel_id=#{channelId} and is_first=1 and delete_time is null) and delete_time is null;")
    Integer selectByChannelId(@Param("channelId") Integer channelId);
}
