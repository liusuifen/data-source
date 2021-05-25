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
 * @Description:价值系数模板表
 * @since 2021-05-24
 */
@ApiModel(value = "价值系数模板表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product_ratio_template")
public class LifeProductRatioTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "模板名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "状态 1有效2无效")
    @TableField("status")
    private Integer status;
    @TableField("system_user_id")
    private Long systemUserId;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "产品id")
    @TableField("life_product_id")
    private Long lifeProductId;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String IS_DELETED = "is_deleted";

    public static final String LIFE_PRODUCT_ID = "life_product_id";

}