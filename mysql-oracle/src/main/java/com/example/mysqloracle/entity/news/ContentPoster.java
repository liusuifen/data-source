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
 * @Description:内容管理-海报管理
 * @since 2021-06-08
 */
@ApiModel(value = "内容管理-海报管理")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_poster")
public class ContentPoster implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键关联编号")
    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "海报类型名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "状态，1=有效，2=无效")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "海报内容，存url")
    @TableField("item")
    private String item;
    @ApiModelProperty(value = "是否节日-生日海报-产品：1、是 0、否")
    @TableField("is_festival")
    private Integer isFestival;
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

    public static final String STATUS = "status";

    public static final String SORT = "sort";

    public static final String ITEM = "item";

    public static final String IS_FESTIVAL = "is_festival";

    public static final String USER_ID = "user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}