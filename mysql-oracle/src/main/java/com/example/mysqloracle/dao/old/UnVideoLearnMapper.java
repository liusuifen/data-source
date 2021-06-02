package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.Org;
import com.example.mysqloracle.entity.old.UnVideoLearn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 培训_视频主表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Mapper
public interface UnVideoLearnMapper extends BaseMapper<UnVideoLearn> {


    @Select("select id, channel_id as channelId, img, media_type as mediaType, dept, dept_child as deptChild, type, title, " +
            "introduction, media_url as mediaUrl, effective_time as effectiveTime, `desc`, state, date, keyword, create_time as createTime, " +
            "create_by as createBy, modify_time as modifyTime, modify_by as modifyBy, delete_time as deleteTime, is_public as isPublic, " +
            "sign_img as signImg, sign_text as signText, qr_code_text as qrCodeText from un_video_learn where channel_id=#{channelId} " +
            "and delete_time is null ")
    List<UnVideoLearn> getByChannleId(@Param("channelId") Integer channelId);

    @Select("select id, channel_id as channelId, img, media_type as mediaType, dept, dept_child as deptChild, type, title, " +
            "introduction, media_url as mediaUrl, effective_time as effectiveTime, `desc`, state, date, keyword, create_time as createTime, " +
            "create_by as createBy, modify_time as modifyTime, modify_by as modifyBy, delete_time as deleteTime, is_public as isPublic, " +
            "sign_img as signImg, sign_text as signText, qr_code_text as qrCodeText from un_video_learn where id=#{id} " +
            "and delete_time is null ")
    UnVideoLearn getById(@Param("id") Integer id);
}
