package com.example.mysqloracle.entity.old;


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
 * @Description:寿险渠道产品档
 * @since 2021-05-18
 */
@ApiModel(value = "寿险渠道产品档")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_product")
public class UnLifeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "原safe_id")
    @TableField("safe_id")
    private Integer safeId;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "产品ID，对应life_product_all中的id")
    @TableField("product_id")
    private Integer productId;
    @ApiModelProperty(value = "产品标签")
    @TableField("product_sign")
    private String productSign;
    @ApiModelProperty(value = "在线投保地址")
    @TableField("online_insure")
    private String onlineInsure;
    @ApiModelProperty(value = "计划书链接")
    @TableField("plan_book")
    private String planBook;
    @ApiModelProperty(value = "图片")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty(value = "犹豫期")
    @TableField("hesitation")
    private Integer hesitation;
    @ApiModelProperty(value = "售卖城市，多个用逗号隔开")
    @TableField("sale_city")
    private String saleCity;
    @ApiModelProperty(value = "是否可以对比，1可以，0不可以")
    @TableField("compare")
    private Integer compare;
    @ApiModelProperty(value = "是否上线")
    @TableField("state")
    private Integer state;
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_time")
    private Integer createTime;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @TableField("delete_time")
    private Integer deleteTime;
    @ApiModelProperty(value = "在线投保类型（1.api对接投保；2.投保链接；3.扫码投保）")
    @TableField("online_insure_type")
    private Integer onlineInsureType;


    public static final String ID = "id";

    public static final String SAFE_ID = "safe_id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_SIGN = "product_sign";

    public static final String ONLINE_INSURE = "online_insure";

    public static final String PLAN_BOOK = "plan_book";

    public static final String IMAGE_URL = "image_url";

    public static final String HESITATION = "hesitation";

    public static final String SALE_CITY = "sale_city";

    public static final String COMPARE = "compare";

    public static final String STATE = "state";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

    public static final String ONLINE_INSURE_TYPE = "online_insure_type";

}