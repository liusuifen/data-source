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
import java.time.LocalDate;

/**
 * @author blueSky
 * @Description:产品价值系数表
 * @since 2021-05-24
 */
@ApiModel(value = "产品价值系数表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_product_ratio_template_map")
public class LifeProductRatioTemplateMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "产品id")
    @TableField("life_product_id")
    private Long lifeProductId;
    @ApiModelProperty(value = "分公司id")
    @TableField("org_id")
    private Long orgId;
    @ApiModelProperty(value = "价值系数模板id")
    @TableField("template_id")
    private Integer templateId;
    @ApiModelProperty(value = "来源方式：0:源系统，1：迁移")
    @TableField("source_way")
    private Integer sourceWay;
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "开始日期")
    @TableField("start_time")
    private LocalDate startTime;
    @ApiModelProperty(value = "结束日期")
    @TableField("end_time")
    private LocalDate endTime;


    public static final String ID = "id";

    public static final String LIFE_PRODUCT_ID = "life_product_id";

    public static final String ORG_ID = "org_id";

    public static final String TEMPLATE_ID = "template_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

}