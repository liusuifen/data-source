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
 * @Description:标签库表
 * @since 2021-05-18
 */
@ApiModel(value = "标签库表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product_tag")
public class LifeProductTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "1产品类型2保障范围3需求标签")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "标签名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "0无效1有效")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @TableField("system_user_id")
    private Long systemUserId;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @TableField("is_deleted")
    private Integer isDeleted;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String SORT = "sort";

    public static final String REMARK = "remark";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String IS_DELETED = "is_deleted";

}