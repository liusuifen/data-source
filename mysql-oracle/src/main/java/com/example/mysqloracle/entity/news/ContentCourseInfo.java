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
 * @Description:内容管理-课程信息表
 * @since 2021-05-28
 */
@ApiModel(value = "内容管理-课程信息表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_course_info")
public class ContentCourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "content_course_id")
    private Long contentCourseId;
    @ApiModelProperty(value = "视频/音频/文件url")
    @TableField("url")
    private String url;
    @ApiModelProperty(value = "转载链接url")
    @TableField("link_url")
    private String linkUrl;
    @ApiModelProperty(value = "上载文件名")
    @TableField("file_name")
    private String fileName;
    @ApiModelProperty(value = "文案/备注")
    @TableField("remark")
    private String remark;
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


    public static final String CONTENT_COURSE_ID = "content_course_id";

    public static final String URL = "url";

    public static final String LINK_URL = "link_url";

    public static final String FILE_NAME = "file_name";

    public static final String REMARK = "remark";

    public static final String USER_ID = "user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}