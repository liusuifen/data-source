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
 * @Description:培训_分类表
 * @since 2021-05-28
 */
@ApiModel(value = "培训_分类表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_video_category")
public class UnVideoCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "渠道")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "父id")
    @TableField("pid")
    private Integer pid;
    @ApiModelProperty(value = "上级")
    @TableField("superior")
    private Integer superior;
    @ApiModelProperty(value = "分类名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "模块代号")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "及格分数")
    @TableField("pass_score")
    private BigDecimal passScore;
    @ApiModelProperty(value = "考试题目数量")
    @TableField("question_num")
    private Integer questionNum;
    @ApiModelProperty(value = "考试时间 （单位： 分钟）")
    @TableField("exam_time")
    private Integer examTime;
    @ApiModelProperty(value = "1:每日一课样式;2:云课堂样式;3:进阶样式")
    @TableField("style")
    private Integer style;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "是否禁用")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "创建人")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "修改人")
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String PID = "pid";

    public static final String SUPERIOR = "superior";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String PASS_SCORE = "pass_score";

    public static final String QUESTION_NUM = "question_num";

    public static final String EXAM_TIME = "exam_time";

    public static final String STYLE = "style";

    public static final String SORT = "sort";

    public static final String STATE = "state";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_BY = "create_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String DELETE_TIME = "delete_time";

}