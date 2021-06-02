package com.example.mysqloracle.entity.news;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:用户收藏表
 * @since 2021-06-01
 */
@ApiModel(value = "用户收藏表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_favorite")
public class UserFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;
    @ApiModelProperty(value = "1=保险产品，2=课程")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "对应id")
    @TableField("content_id")
    private Long contentId;
    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;
    @ApiModelProperty(value = "签到时间")
    @TableField("created_at")
    private LocalDateTime createdAt;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String USER_ID = "user_id";

    public static final String CONTENT_ID = "content_id";

    public static final String TITLE = "title";

    public static final String CREATED_AT = "created_at";

}