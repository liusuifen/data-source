package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnVideoLearnCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 培训 - 用户收藏课程 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-06-01
 */
@Mapper
public interface UnVideoLearnCollectMapper extends BaseMapper<UnVideoLearnCollect> {

    @Select("select id,user_id as userId,video_id as videoId,create_time as createTime from un_video_learn_collect where video_id in " +
            " (select id from un_video_learn where channel_id=#{channelId} and delete_time is null)")
    List<UnVideoLearnCollect> selectByChannelId(@Param("channelId") Integer channelId);

}
