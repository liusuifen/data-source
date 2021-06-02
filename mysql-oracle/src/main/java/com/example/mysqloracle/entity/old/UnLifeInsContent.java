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
 * @Description:寿险保单-投保内容信息-子表
 * @since 2021-05-06
 */
@ApiModel(value = "寿险保单-投保内容信息-子表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_ins_content")
public class UnLifeInsContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险保单内容编号,后续对应字段名为content_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "保单编号（对应hmhome.un_life_policy_main.policy_id）")
    @TableField("policy_id")
    private Integer policyId;
    @ApiModelProperty(value = "险种")
    @TableField("product_id")
    private Integer productId;
    @ApiModelProperty(value = "保险金额")
    @TableField("amount")
    private BigDecimal amount;
    @ApiModelProperty(value = "保险期间")
    @TableField("period_guanantee")
    private Integer periodGuanantee;
    @ApiModelProperty(value = "交费期间")
    @TableField("period_payment")
    private Integer periodPayment;
    @ApiModelProperty(value = "保险费(首期 / 期交)")
    @TableField("fee")
    private BigDecimal fee;
    @ApiModelProperty(value = "折标")
    @TableField("ratio")
    private BigDecimal ratio;
    @ApiModelProperty(value = "标保")
    @TableField("std_premium")
    private BigDecimal stdPremium;
    @ApiModelProperty(value = "追加保费(首期)")
    @TableField("additional_fee")
    private BigDecimal additionalFee;
    @ApiModelProperty(value = "投保份数：默认一份。")
    @TableField("quantity")
    private Integer quantity;
    @ApiModelProperty(value = "是否主险(主合同)")
    @TableField("is_main")
    private Integer isMain;
    @ApiModelProperty(value = "责任选项")
    @TableField("duty_option")
    private String dutyOption;
    @ApiModelProperty(value = "免赔额 / 免赔天数")
    @TableField("free_option")
    private BigDecimal freeOption;
    @ApiModelProperty(value = "赔付比例")
    @TableField("loss_rate")
    private BigDecimal lossRate;
    @ApiModelProperty(value = "扩展字段信息（JSON格式）")
    @TableField("extend_items")
    private String extendItems;
    @ApiModelProperty(value = "投保内容状态：0=作废,1=正常.")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "投保内容创建人编号")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "投保内容创建时间戳")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "投保内容修改人编号")
    @TableField("modify_by")
    private Integer modifyBy;
    @ApiModelProperty(value = "投保内容修改时间戳")
    @TableField("modify_time")
    private Integer modifyTime;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String POLICY_ID = "policy_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String AMOUNT = "amount";

    public static final String PERIOD_GUANANTEE = "period_guanantee";

    public static final String PERIOD_PAYMENT = "period_payment";

    public static final String FEE = "fee";

    public static final String RATIO = "ratio";

    public static final String STD_PREMIUM = "std_premium";

    public static final String ADDITIONAL_FEE = "additional_fee";

    public static final String QUANTITY = "quantity";

    public static final String IS_MAIN = "is_main";

    public static final String DUTY_OPTION = "duty_option";

    public static final String FREE_OPTION = "free_option";

    public static final String LOSS_RATE = "loss_rate";

    public static final String EXTEND_ITEMS = "extend_items";

    public static final String STATE = "state";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

}