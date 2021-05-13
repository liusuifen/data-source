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
import java.math.BigDecimal;

/**
 * @author blueSky
 * @Description:寿险总档
 * @since 2021-05-10
 */
@ApiModel(value = "寿险总档")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_product_all")
public class UnLifeProductAll implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "原id(后续迁移数据完删除)")
    @TableField("safe_id")
    private Integer safeId;
    @ApiModelProperty(value = "主险，父类ID，对应本表id,可多个,','隔开")
    @TableField("pid")
    private String pid;
    @ApiModelProperty(value = "险种分类：0附加险，1主险，2双主险")
    @TableField("is_primary")
    private Boolean isPrimary;
    @ApiModelProperty(value = "关联un_basic_company表的id")
    @TableField("company_id")
    private Integer companyId;
    @ApiModelProperty(value = "险种代码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "险种编码")
    @TableField("product_code")
    private String productCode;
    @ApiModelProperty(value = "险种名称")
    @TableField("product_name")
    private String productName;
    @ApiModelProperty(value = "缴费周期，1：年交；2：月交")
    @TableField("payment_cycle")
    private String paymentCycle;
    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;
    @ApiModelProperty(value = "图片")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty(value = "计划书详情， 页面头部banner 图")
    @TableField("proposal_image_url")
    private String proposalImageUrl;
    @ApiModelProperty(value = "亮点")
    @TableField("shining_point")
    private String shiningPoint;
    @ApiModelProperty(value = "保障范围，多个用逗号隔开")
    @TableField("ensure_range")
    private String ensureRange;
    @ApiModelProperty(value = "需求标签，对应basic_enum中的id，多个用逗号隔开")
    @TableField("require_sign")
    private String requireSign;
    @ApiModelProperty(value = "关键字")
    @TableField("keywords")
    private String keywords;
    @ApiModelProperty(value = "简称")
    @TableField("short_name")
    private String shortName;
    @ApiModelProperty(value = "保险类别")
    @TableField("ins_type")
    private String insType;
    @ApiModelProperty(value = "是否可以对比，1可以，0不可以")
    @TableField("compare")
    private Boolean compare;
    @ApiModelProperty(value = "条款文件")
    @TableField("statement")
    private String statement;
    @ApiModelProperty(value = "投保规则")
    @TableField("insured_rule")
    private String insuredRule;
    @ApiModelProperty(value = "是否在线投保：1是，0否")
    @TableField("online_buy")
    private Boolean onlineBuy;
    @ApiModelProperty(value = "计划书状态：0禁用 1启用")
    @TableField("plan_state")
    private Boolean planState;
    @ApiModelProperty(value = " 平板计划书上线状态 0 否  1 是")
    @TableField("plan_state_pad")
    private Boolean planStatePad;
    @ApiModelProperty(value = "是否具备绿通服务,0否 1是")
    @TableField("is_green_server")
    private Boolean isGreenServer;
    @ApiModelProperty(value = "是否养老社区：0否，1是")
    @TableField("is_community")
    private Boolean isCommunity;
    @ApiModelProperty(value = "险种状态：0禁用 1启用")
    @TableField("status")
    private Boolean status;
    @ApiModelProperty(value = "产品组合代码")
    @TableField("package_code")
    private String packageCode;
    @ApiModelProperty(value = "标记详情")
    @TableField("flag_des")
    private String flagDes;
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


    public static final String ID = "id";

    public static final String SAFE_ID = "safe_id";

    public static final String PID = "pid";

    public static final String IS_PRIMARY = "is_primary";

    public static final String COMPANY_ID = "company_id";

    public static final String CODE = "code";

    public static final String PRODUCT_CODE = "product_code";

    public static final String PRODUCT_NAME = "product_name";

    public static final String PAYMENT_CYCLE = "payment_cycle";

    public static final String PRICE = "price";

    public static final String IMAGE_URL = "image_url";

    public static final String PROPOSAL_IMAGE_URL = "proposal_image_url";

    public static final String SHINING_POINT = "shining_point";

    public static final String ENSURE_RANGE = "ensure_range";

    public static final String REQUIRE_SIGN = "require_sign";

    public static final String KEYWORDS = "keywords";

    public static final String SHORT_NAME = "short_name";

    public static final String INS_TYPE = "ins_type";

    public static final String COMPARE = "compare";

    public static final String STATEMENT = "statement";

    public static final String INSURED_RULE = "insured_rule";

    public static final String ONLINE_BUY = "online_buy";

    public static final String PLAN_STATE = "plan_state";

    public static final String PLAN_STATE_PAD = "plan_state_pad";

    public static final String IS_GREEN_SERVER = "is_green_server";

    public static final String IS_COMMUNITY = "is_community";

    public static final String STATUS = "status";

    public static final String PACKAGE_CODE = "package_code";

    public static final String FLAG_DES = "flag_des";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

}