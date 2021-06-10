package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnBasicBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 渠道商-广告图展示图 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Mapper
public interface UnBasicBannerMapper extends BaseMapper<UnBasicBanner> {

    @Select("select id, channel_id as channelId, location, img_name as imgName, image, link, start_time as startTime, end_time as endTime, " +
            "create_time as createTime, create_by as createBy, modify_by as modifyBy, modify_time as modifyTime, delete_time as deleteTime " +
            "from un_basic_banner where channel_id=#{channelId} and delete_time is null ")
    List<UnBasicBanner>  selectByChannelId(@Param("channelId") Integer channelId);
}
