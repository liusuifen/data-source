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

/**
 * @author blueSky
 * @Description:代理人职级表
 * @since 2021-04-29
 */
@ApiModel(value = "代理人职级表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_hr_rank")
public class UnHrRank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "渠道号")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "职级名称")
    @TableField("rank_name")
    private String rankName;
    @ApiModelProperty(value = "1启用,2禁用")
    @TableField("state")
    private Boolean state;
    @ApiModelProperty(value = "是否代理人职级（用于控制移动端是否显示），0否，1是")
    @TableField("view")
    private Boolean view;
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_time")
    private Integer createTime;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "旧rank_id")
    @TableField("rank_id")
    private Integer rankId;
    @TableField("delete_time")
    private Integer deleteTime;
    @ApiModelProperty(value = "注册默认职级，1是，0否")
    @TableField("default_rank")
    private Boolean defaultRank;
    @ApiModelProperty(value = "代理人职级排序（越大职级越大）")
    @TableField("agent_sort")
    private Integer agentSort;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String RANK_NAME = "rank_name";

    public static final String STATE = "state";

    public static final String VIEW = "view";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String RANK_ID = "rank_id";

    public static final String DELETE_TIME = "delete_time";

    public static final String DEFAULT_RANK = "default_rank";

    public static final String AGENT_SORT = "agent_sort";

}