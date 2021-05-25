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
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:价值系数模板明细表
 * @since 2021-05-24
 */
@ApiModel(value = "价值系数模板明细表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product_ratio_template_detail")
public class LifeProductRatioTemplateDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;
    @ApiModelProperty(value = "模板id")
    @TableField("template_id")
    private Integer templateId;
    @ApiModelProperty(value = "保险期间（年）")
    @TableField("year")
    private Integer year;
    @ApiModelProperty(value = "保险期间单位：1年2岁")
    @TableField("year_unit")
    private Integer yearUnit;
    @ApiModelProperty(value = "缴费年期（年）")
    @TableField("pay_year")
    private Integer payYear;
    @ApiModelProperty(value = "缴费年期单位：1年2月3周4天")
    @TableField("pay_year_unit")
    private Integer payYearUnit;
    @ApiModelProperty(value = "折标率")
    @TableField("ratio")
    private BigDecimal ratio;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "来源方式：0:源系统，1：迁移")
    @TableField("source_way")
    private Integer sourceWay;
    @TableField("system_user_id")
    private Integer systemUserId;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @TableField("is_deleted")
    private Integer isDeleted;


    public static final String ID = "id";

    public static final String TEMPLATE_ID = "template_id";

    public static final String YEAR = "year";

    public static final String YEAR_UNIT = "year_unit";

    public static final String PAY_YEAR = "pay_year";

    public static final String PAY_YEAR_UNIT = "pay_year_unit";

    public static final String RATIO = "ratio";

    public static final String REMARK = "remark";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String IS_DELETED = "is_deleted";

}