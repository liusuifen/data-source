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
 * @Description:内容管理-课程类型
 * @since 2021-05-28
 */
@ApiModel(value = "内容管理-课程类型")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_course_type")
public class ContentCourseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "课程类型名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "顺序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "状态，1=有效，2=无效.")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "是否每日一课：1、是 0、否")
    @TableField("is_lesson")
    private Integer isLesson;
    @ApiModelProperty(value = "写入操作者id")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "是否删除：1、删除 0、未删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String SORT = "sort";

    public static final String STATUS = "status";

    public static final String IS_LESSON = "is_lesson";

    public static final String USER_ID = "user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}