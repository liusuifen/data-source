package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnVideoCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 培训_分类表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Mapper
public interface UnVideoCategoryMapper extends BaseMapper<UnVideoCategory> {


    @Select("select  id, channel_id as channelId, pid, superior, name, code, pass_score as passScore, question_num as questionNum, " +
            "exam_time as examTime, style, sort, state, create_time as createTime, create_by as createBy, modify_time as modifyTime, " +
            "modify_by as modifyBy, delete_time as deleteTime from  un_video_category where channel_id=#{channelId} and  delete_time is null")
    List<UnVideoCategory> getAllCategory(@Param("channelId") Integer channelId);



}
