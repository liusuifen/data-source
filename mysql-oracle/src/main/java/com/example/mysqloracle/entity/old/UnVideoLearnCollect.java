package com.example.mysqloracle.entity.old;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author blueSky
 * @Description:培训 - 用户收藏课程
 * @since 2021-06-01
 */
@ApiModel(value = "培训 - 用户收藏课程")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_video_learn_collect")
public class UnVideoLearnCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    @TableField("video_id")
    private Integer videoId;
    @TableField("create_time")
    private Integer createTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String VIDEO_ID = "video_id";

    public static final String CREATE_TIME = "create_time";

}