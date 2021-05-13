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
 * @Description:系统管理-职级维护表
 * @since 2021-05-07
 */
@ApiModel(value = "系统管理-职级维护表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_rank")
public class SystemRank implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键关联编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "组织机构ID")
    @TableField("org_id")
    private Long orgId;
    @ApiModelProperty(value = "职级编号")
    @TableField("number")
    private Integer number;
    @ApiModelProperty(value = "类型：1=内勤，2=外勤.")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "职级名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "状态:  1=无效,  2=有效.")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "是否管理岗")
    @TableField("is_manage")
    private Integer isManage;
    @ApiModelProperty(value = "职级排序（越大职级越大）")
    @TableField("level")
    private Integer level;
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

    public static final String ORG_ID = "org_id";

    public static final String NUMBER = "number";

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String IS_MANAGE = "is_manage";

    public static final String LEVEL = "level";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}