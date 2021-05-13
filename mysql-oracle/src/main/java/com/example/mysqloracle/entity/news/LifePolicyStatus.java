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
import java.time.LocalDateTime;


/**
 * @author blueSky
 * @Description:寿险保单-保单状态变更表
 * @since 2021-05-11
 */
@ApiModel(value = "寿险保单-保单状态变更表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_policy_status")
public class LifePolicyStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "保单表ID")
    @TableField("life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "1待承保2承保3有效4撤单5犹豫期退保6退保7失效8终止")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "状态备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "状态日期")
    @TableField("date")
    private LocalDate date;
    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "修改者")
    @TableField("update_user")
    private String updateUser;
    @TableField("update_user_id")
    private Long updateUserId;


    public static final String ID = "id";

    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";

    public static final String DATE = "date";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_USER_ID = "update_user_id";

}