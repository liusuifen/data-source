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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:内容管理-课程表
 * @since 2021-05-28
 */
@ApiModel(value = "内容管理-课程表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_course")
public class ContentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "课程类型ID")
    @TableField("content_course_type_id")
    private Long contentCourseTypeId;
    @ApiModelProperty(value = "封面")
    @TableField("cover")
    private String cover;
    @ApiModelProperty(value = "课程标题")
    @TableField("title")
    private String title;
    @ApiModelProperty(value = "课程作者")
    @TableField("author")
    private String author;
    @ApiModelProperty(value = "课程简介")
    @TableField("introduction")
    private String introduction;
    @ApiModelProperty(value = "接收机构-子级")
    @TableField("receive_org")
    private String receiveOrg;
    @ApiModelProperty(value = "接收机构-父级")
    @TableField("parent_org")
    private String parentOrg;
    @ApiModelProperty(value = "接收机构-全部")
    @TableField("total_org")
    private String totalOrg;
    @ApiModelProperty(value = "是否允许分享：1、是 0、否")
    @TableField("is_share")
    private Integer isShare;
    @ApiModelProperty(value = "接收职级")
    @TableField("receive_rank")
    private String receiveRank;
    @ApiModelProperty(value = "渠道是否可学：1、是 0、否")
    @TableField("is_learn")
    private Integer isLearn;
    @ApiModelProperty(value = "可学渠道")
    @TableField("receive_channel")
    private String receiveChannel;
    @ApiModelProperty(value = "游客是否可见：1、是 0、否")
    @TableField("is_visible")
    private Integer isVisible;
    @ApiModelProperty(value = "状态:  1=上架,  2=下架.")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "考勤日期")
    @TableField("attendance_time")
    private LocalDate attendanceTime;
    @ApiModelProperty(value = "打卡时间-时")
    @TableField("clock_in_hour")
    private Integer clockInHour;
    @ApiModelProperty(value = "打卡时间-分")
    @TableField("clock_in_minute")
    private Integer clockInMinute;
    @ApiModelProperty(value = "打卡时间-秒")
    @TableField("clock_in_second")
    private Integer clockInSecond;
    @ApiModelProperty(value = "面向平台，1、藏保图APP，2、藏保图平板")
    @TableField("platform_oriented")
    private String platformOriented;
    @ApiModelProperty(value = "上载类型：1、视频2、音频3、编写文案4、文件5、媒体链接")
    @TableField("upload_type")
    private Integer uploadType;
    @ApiModelProperty(value = "发布者")
    @TableField("created_user_id")
    private Long createdUserId;
    @ApiModelProperty(value = "发布时间")
    @TableField("publish_time")
    private LocalDateTime publishTime;
    @ApiModelProperty(value = "写入操作者id")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "浏览次数")
    @TableField("view")
    private Long view;
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

    public static final String CONTENT_COURSE_TYPE_ID = "content_course_type_id";

    public static final String COVER = "cover";

    public static final String TITLE = "title";

    public static final String AUTHOR = "author";

    public static final String INTRODUCTION = "introduction";

    public static final String RECEIVE_ORG = "receive_org";

    public static final String PARENT_ORG = "parent_org";

    public static final String TOTAL_ORG = "total_org";

    public static final String IS_SHARE = "is_share";

    public static final String RECEIVE_RANK = "receive_rank";

    public static final String IS_LEARN = "is_learn";

    public static final String RECEIVE_CHANNEL = "receive_channel";

    public static final String IS_VISIBLE = "is_visible";

    public static final String STATUS = "status";

    public static final String ATTENDANCE_TIME = "attendance_time";

    public static final String CLOCK_IN_HOUR = "clock_in_hour";

    public static final String CLOCK_IN_MINUTE = "clock_in_minute";

    public static final String CLOCK_IN_SECOND = "clock_in_second";

    public static final String PLATFORM_ORIENTED = "platform_oriented";

    public static final String UPLOAD_TYPE = "upload_type";

    public static final String CREATED_USER_ID = "created_user_id";

    public static final String PUBLISH_TIME = "publish_time";

    public static final String USER_ID = "user_id";

    public static final String VIEW = "view";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}