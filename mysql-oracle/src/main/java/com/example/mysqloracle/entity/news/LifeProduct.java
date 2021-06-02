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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险产品库
 * @since 2021-05-06
 */
@ApiModel(value = "寿险产品库")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product")
public class LifeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "险种分类1主险2双主险3附加险")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "保险公司id")
    @TableField("company_id")
    private Long companyId;
    @ApiModelProperty(value = "险种代码")
    @TableField("code")
    private String code;
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
    @ApiModelProperty(value = "产品大类")
    @TableField("category_id")
    private Integer categoryId;
    @ApiModelProperty(value = "是否对接api，对接了api的才可以线上投保")
    @TableField("is_api")
    private Integer isApi;
    @ApiModelProperty(value = "是否绿通服务")
    @TableField("is_green_service")
    private Integer isGreenService;
    @ApiModelProperty(value = "是否养老社区")
    @TableField("is_community")
    private Integer isCommunity;
    @ApiModelProperty(value = "是否网销产品")
    @TableField("is_sale_online")
    private Integer isSaleOnline;
    @ApiModelProperty(value = "状态：1草稿2可售3停售4下架")
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
    @ApiModelProperty(value = "销售方式 1：h5跳转 2:二维码 3：api对接")
    @TableField("sale_way")
    private Integer saleWay;
    @ApiModelProperty(value = "二维码图片地址")
    @TableField("qrcode_url")
    private String qrcodeUrl;
    @ApiModelProperty(value = "计划书链接地址")
    @TableField("proposal_url")
    private String proposalUrl;
    @ApiModelProperty(value = "在线投保链接地址")
    @TableField("sale_online_url")
    private String saleOnlineUrl;
    @ApiModelProperty(value = "算佣方式 1折标系数2佣金比例3收益佣金结算")
    @TableField("commission_way")
    private Integer commissionWay;
    @ApiModelProperty(value = "续期算佣方式2佣金比例3收益佣金结算")
    @TableField("commission_way_renewal")
    private Integer commissionWayRenewal;
    @ApiModelProperty(value = "投保年龄")
    @TableField("allow_age")
    private String allowAge;
    @ApiModelProperty(value = "是否上线计划书")
    @TableField("is_proposal")
    private Integer isProposal;
    @ApiModelProperty(value = "首次收取保单期数")
    @TableField("first_pay_count")
    private Integer firstPayCount;
    @ApiModelProperty(value = "是否在线投保")
    @TableField("is_order_online")
    private Integer isOrderOnline;
    @ApiModelProperty(value = "是否长期险")
    @TableField("is_long_term")
    private Integer isLongTerm;
    @ApiModelProperty(value = "是否配置了产品佣金")
    @TableField("is_set_commission_ratios")
    private Integer isSetCommissionRatios;
    @ApiModelProperty(value = "产品列表图")
    @TableField("list_image_url")
    private String listImageUrl;
    @ApiModelProperty(value = "是否提交过api申请")
    @TableField("is_add_api")
    private Integer isAddApi;
    @ApiModelProperty(value = "是否提交过计划书申请")
    @TableField("is_add_proposal")
    private Integer isAddProposal;
    @ApiModelProperty(value = "1自己新增2产品库导入新增")
    @TableField("add_from")
    private Integer addFrom;
    @ApiModelProperty(value = "1小雨伞100其他")
    @TableField("source")
    private Integer source;
    @ApiModelProperty(value = "是否自动同步产品数据")
    @TableField("is_auto_sync")
    private Integer isAutoSync;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String COMPANY_ID = "company_id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String NAME_SHORT = "name_short";

    public static final String CYCLE = "cycle";

    public static final String PRICE = "price";

    public static final String IMAGE_URL = "image_url";

    public static final String RANGE = "range";

    public static final String CATEGORY_ID = "category_id";

    public static final String IS_API = "is_api";

    public static final String IS_GREEN_SERVICE = "is_green_service";

    public static final String IS_COMMUNITY = "is_community";

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

    public static final String SALE_WAY = "sale_way";

    public static final String QRCODE_URL = "qrcode_url";

    public static final String PROPOSAL_URL = "proposal_url";

    public static final String SALE_ONLINE_URL = "sale_online_url";

    public static final String COMMISSION_WAY = "commission_way";

    public static final String COMMISSION_WAY_RENEWAL = "commission_way_renewal";

    public static final String ALLOW_AGE = "allow_age";

    public static final String IS_PROPOSAL = "is_proposal";

    public static final String FIRST_PAY_COUNT = "first_pay_count";

    public static final String IS_ORDER_ONLINE = "is_order_online";

    public static final String IS_LONG_TERM = "is_long_term";

    public static final String IS_SET_COMMISSION_RATIOS = "is_set_commission_ratios";

    public static final String LIST_IMAGE_URL = "list_image_url";

    public static final String IS_ADD_API = "is_add_api";

    public static final String IS_ADD_PROPOSAL = "is_add_proposal";

    public static final String ADD_FROM = "add_from";

    public static final String SOURCE = "source";

    public static final String IS_AUTO_SYNC = "is_auto_sync";

}