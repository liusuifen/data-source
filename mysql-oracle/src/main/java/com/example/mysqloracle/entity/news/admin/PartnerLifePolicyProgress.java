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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险保单-保单进度变更表
 * @since 2021-05-17
 */
@ApiModel(value = "寿险保单-保单进度变更表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("partner_life_policy_progress")
public class PartnerLifePolicyProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "保单表id")
    @TableField("life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "10草稿11录单12预收13人工核保14拒保15承保16回执17复核18回访19对账20发佣21积分22核保")
    @TableField("progress")
    private Integer progress;
    @ApiModelProperty(value = "进度结果1成功2失败 无结果0")
    @TableField("result")
    private Integer result;
    @ApiModelProperty(value = "进度时间")
    @TableField("date")
    private LocalDate date;
    @ApiModelProperty(value = "影像资料上传地址")
    @TableField("file")
    private String file;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "修改者")
    @TableField("update_user")
    private String updateUser;
    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "来源Id(更新分销数据时使用)")
    @TableField("source_id")
    private Long sourceId;
    @TableField("update_user_id")
    private Long updateUserId;


    public static final String ID = "id";

    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String PROGRESS = "progress";

    public static final String RESULT = "result";

    public static final String DATE = "date";

    public static final String FILE = "file";

    public static final String REMARK = "remark";

    public static final String UPDATE_USER = "update_user";

    public static final String CREATED_AT = "created_at";

    public static final String SOURCE_ID = "source_id";

    public static final String UPDATE_USER_ID = "update_user_id";

}