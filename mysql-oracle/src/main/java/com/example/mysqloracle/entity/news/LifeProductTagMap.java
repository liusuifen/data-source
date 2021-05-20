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

/**
 * @author blueSky
 * @Description:标签库表
 * @since 2021-05-18
 */
@ApiModel(value = "标签库表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product_tag_map")
public class LifeProductTagMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "产品id")
    @TableField("life_product_id")
    private Long lifeProductId;
    @ApiModelProperty(value = "标签id")
    @TableField("life_product_tag_id")
    private Integer lifeProductTagId;
    @ApiModelProperty(value = "1产品类型2保障范围3需求标签")
    @TableField("type")
    private Integer type;
    @TableField("is_deleted")
    private Integer isDeleted;


    public static final String ID = "id";

    public static final String LIFE_PRODUCT_ID = "life_product_id";

    public static final String LIFE_PRODUCT_TAG_ID = "life_product_tag_id";

    public static final String TYPE = "type";

    public static final String IS_DELETED = "is_deleted";

}