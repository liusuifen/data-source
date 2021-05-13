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
 * @Description:数据迁移日志表
 * @since 2021-05-12
 */
@ApiModel(value = "数据迁移日志表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("migration_log")
public class MigrationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "迁移老系统数据id")
    @TableField("old_id")
    private Long oldId;
    @ApiModelProperty(value = "迁移老系统数据id")
    @TableField("source_partner_id")
    private Long sourcePartnerId;
    @ApiModelProperty(value = "迁移状态1：迁移成功 2：迁移失败")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "迁移类型 1：保单")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String OLD_ID = "old_id";

    public static final String STATUS = "status";

    public static final String TYPE = "type";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}