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

/**
 * @author blueSky
 * @Description:寿险保单-险种信息表
 * @since 2021-05-06
 */
@ApiModel(value = "寿险保单-险种信息表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_policy_product")
public class LifePolicyProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "保单表id")
    @TableField("life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "保险产品表id")
    @TableField("life_product_id")
    private Long lifeProductId;
    @ApiModelProperty(value = "险种名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "险种编码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "产品大类")
    @TableField("category_id")
    private Integer categoryId;
    @ApiModelProperty(value = "大类名称")
    @TableField("category_name")
    private String categoryName;
    @ApiModelProperty(value = "保险金额")
    @TableField("amount")
    private BigDecimal amount;
    @ApiModelProperty(value = "保障区间")
    @TableField("safe_year")
    private Integer safeYear;
    @ApiModelProperty(value = "保障区间单位 1年2岁3月4周5天")
    @TableField("safe_year_unit")
    private Integer safeYearUnit;
    @ApiModelProperty(value = "缴费期间 1-100")
    @TableField("pay_year")
    private Integer payYear;
    @ApiModelProperty(value = "缴费期间单位  1年2岁")
    @TableField("pay_year_unit")
    private Integer payYearUnit;
    @ApiModelProperty(value = "缴费方式1趸交2期交")
    @TableField("pay_way")
    private Integer payWay;
    @ApiModelProperty(value = "缴费频率1年交2半年交3季缴4月缴5周缴6天缴7一次性缴清")
    @TableField("pay_frequency")
    private Integer payFrequency;
    @ApiModelProperty(value = "保险费(首期 / 期交)")
    @TableField("fee")
    private BigDecimal fee;
    @ApiModelProperty(value = "标准保费")
    @TableField("std_fee")
    private BigDecimal stdFee;
    @ApiModelProperty(value = "折标")
    @TableField("ratio")
    private BigDecimal ratio;
    @ApiModelProperty(value = "是否主险")
    @TableField("is_primary")
    private Integer isPrimary;
    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "扩展属性")
    @TableField("extension")
    private String extension;
    @ApiModelProperty(value = "犹豫期时间")
    @TableField("hesitate_time")
    private Integer hesitateTime;
    @ApiModelProperty(value = "犹豫期单位  1:工作日2:自然日")
    @TableField("hesitate_unit")
    private Integer hesitateUnit;
    @ApiModelProperty(value = "算佣方式 1折标系数2佣金比例3收益佣金结算")
    @TableField("commission_way")
    private Integer commissionWay;
    @ApiModelProperty(value = "续期算佣方式2佣金比例3收益佣金结算")
    @TableField("commission_way_renewal")
    private Integer commissionWayRenewal;


    public static final String ID = "id";

    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String LIFE_PRODUCT_ID = "life_product_id";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String CATEGORY_ID = "category_id";

    public static final String CATEGORY_NAME = "category_name";

    public static final String AMOUNT = "amount";

    public static final String SAFE_YEAR = "safe_year";

    public static final String SAFE_YEAR_UNIT = "safe_year_unit";

    public static final String PAY_YEAR = "pay_year";

    public static final String PAY_YEAR_UNIT = "pay_year_unit";

    public static final String PAY_WAY = "pay_way";

    public static final String PAY_FREQUENCY = "pay_frequency";

    public static final String FEE = "fee";

    public static final String STD_FEE = "std_fee";

    public static final String RATIO = "ratio";

    public static final String IS_PRIMARY = "is_primary";

    public static final String IS_DELETED = "is_deleted";

    public static final String EXTENSION = "extension";

    public static final String HESITATE_TIME = "hesitate_time";

    public static final String HESITATE_UNIT = "hesitate_unit";

    public static final String COMMISSION_WAY = "commission_way";

    public static final String COMMISSION_WAY_RENEWAL = "commission_way_renewal";

}