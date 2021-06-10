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
 * @Description:内容管理-广告位管理
 * @since 2021-06-08
 */
@ApiModel(value = "内容管理-广告位管理")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_ad")
public class ContentAd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键关联编号")
    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "广告位分类")
    @TableField("content_ad_type_id")
    private Long contentAdTypeId;
    @ApiModelProperty(value = "广告图片")
    @TableField("pic")
    private String pic;
    @ApiModelProperty(value = "广告位信息")
    @TableField("description")
    private String description;
    @ApiModelProperty(value = "图片url链接")
    @TableField("url")
    private String url;
    @ApiModelProperty(value = "状态，1=有效，2=无效")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "上架开始时间")
    @TableField("publish_start")
    private LocalDate publishStart;
    @ApiModelProperty(value = "上架结束时间")
    @TableField("publish_end")
    private LocalDate publishEnd;
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

    public static final String CONTENT_AD_TYPE_ID = "content_ad_type_id";

    public static final String PIC = "pic";

    public static final String DESCRIPTION = "description";

    public static final String URL = "url";

    public static final String STATUS = "status";

    public static final String SORT = "sort";

    public static final String PUBLISH_START = "publish_start";

    public static final String PUBLISH_END = "publish_end";

    public static final String USER_ID = "user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}