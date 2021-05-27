package com.example.mysqloracle.entity.news.admin;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险产品库
 * @since 2021-05-27
 */
@ApiModel(value = "寿险产品库")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product")
public class LifeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "险种分类：1主险2双主险3附加险")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "保险公司id")
    @TableField("company_id")
    private Long companyId;
    @ApiModelProperty(value = "险种代码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "险种编码")
    @TableField("code_api")
    private String codeApi;
    @ApiModelProperty(value = "险种名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "简称")
    @TableField("name_short")
    private String nameShort;
    @ApiModelProperty(value = "缴费周期，1：年交；2：月交 多种缴费周期用,分割")
    @TableField("cycle")
    private String cycle;
    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;
    @ApiModelProperty(value = "图片")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty(value = "保障区间")
    @TableField("range")
    private String range;
    @ApiModelProperty(value = "保险类别")
    @TableField("category_id")
    private Integer categoryId;
    @ApiModelProperty(value = "是否对接api，对接了api的才可以线上投保")
    @TableField("is_api")
    private Integer isApi;
    @ApiModelProperty(value = "是否可以对比，0否1是")
    @TableField("is_compare")
    private Integer isCompare;
    @ApiModelProperty(value = "是否网销产品")
    @TableField("is_sale_online")
    private Integer isSaleOnline;
    @ApiModelProperty(value = "状态：1草稿2可售3停售")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "售卖开始时间")
    @TableField("start_time")
    private LocalDate startTime;
    @ApiModelProperty(value = "售卖结束时间")
    @TableField("end_time")
    private LocalDate endTime;
    @ApiModelProperty(value = "犹豫时间")
    @TableField("hesitate_time")
    private Integer hesitateTime;
    @ApiModelProperty(value = "犹豫时间单位 1:工作日2:自然日")
    @TableField("hesitate_unit")
    private Integer hesitateUnit;
    @TableField("system_user_id")
    private Long systemUserId;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "首期收取保单期数")
    @TableField("first_pay_count")
    private Integer firstPayCount;
    @ApiModelProperty(value = "产品列表图")
    @TableField("list_image_url")
    private String listImageUrl;
    @ApiModelProperty(value = "是否长期险")
    @TableField("is_long_term")
    private Integer isLongTerm;
    @ApiModelProperty(value = "购买链接")
    @TableField("buy_link")
    private String buyLink;
    @ApiModelProperty(value = "产品来源")
    @TableField("source")
    private String source;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String COMPANY_ID = "company_id";

    public static final String CODE = "code";

    public static final String CODE_API = "code_api";

    public static final String NAME = "name";

    public static final String NAME_SHORT = "name_short";

    public static final String CYCLE = "cycle";

    public static final String PRICE = "price";

    public static final String IMAGE_URL = "image_url";

    public static final String RANGE = "range";

    public static final String CATEGORY_ID = "category_id";

    public static final String IS_API = "is_api";

    public static final String IS_COMPARE = "is_compare";

    public static final String IS_SALE_ONLINE = "is_sale_online";

    public static final String STATUS = "status";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String HESITATE_TIME = "hesitate_time";

    public static final String HESITATE_UNIT = "hesitate_unit";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String IS_DELETED = "is_deleted";

    public static final String FIRST_PAY_COUNT = "first_pay_count";

    public static final String LIST_IMAGE_URL = "list_image_url";

    public static final String IS_LONG_TERM = "is_long_term";

    public static final String BUY_LINK = "buy_link";

    public static final String SOURCE = "source";

}