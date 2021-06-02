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
 * @Description:寿险产品系数
 * @since 2021-05-24
 */
@ApiModel(value = "寿险产品系数")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_product_ratio")
public class UnLifeProductRatio implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险产品折标系数编号,后续对应字段名为ratio_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "产品ID，对应life_product中的id")
    @TableField("product_id")
    private Integer productId;
    @ApiModelProperty(value = "城市代号")
    @TableField("area")
    private String area;
    @ApiModelProperty(value = "保险期间（年）")
    @TableField("year")
    private Integer year;
    @ApiModelProperty(value = "保险期间单位：1年，2岁")
    @TableField("year_unit")
    private Integer yearUnit;
    @ApiModelProperty(value = "缴费年期（年）")
    @TableField("pay_year")
    private Integer payYear;
    @ApiModelProperty(value = "缴费年期单位：1年，2岁")
    @TableField("pay_year_unit")
    private Integer payYearUnit;
    @ApiModelProperty(value = "手续费")
    @TableField("pro_fee")
    private BigDecimal proFee;
    @ApiModelProperty(value = "折标率")
    @TableField("ratio")
    private BigDecimal ratio;
    @ApiModelProperty(value = "续期比例")
    @TableField("renewal")
    private BigDecimal renewal;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "safegoods_value 表sv_id")
    @TableField("sv_id")
    private Integer svId;
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
    @ApiModelProperty(value = "状态，开启1/禁用0")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "首佣率，逗号间隔（针对盛世安康产品首佣率分档）")
    @TableField("fyp_rate")
    private String fypRate;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String AREA = "area";

    public static final String YEAR = "year";

    public static final String YEAR_UNIT = "year_unit";

    public static final String PAY_YEAR = "pay_year";

    public static final String PAY_YEAR_UNIT = "pay_year_unit";

    public static final String PRO_FEE = "pro_fee";

    public static final String RATIO = "ratio";

    public static final String RENEWAL = "renewal";

    public static final String REMARK = "remark";

    public static final String SV_ID = "sv_id";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

    public static final String STATE = "state";

    public static final String FYP_RATE = "fyp_rate";

}