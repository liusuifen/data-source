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
 * @Description:寿险保单-状态跟踪-影像记录列表
 * @since 2021-05-06
 */
@ApiModel(value = "寿险保单-状态跟踪-影像记录列表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_ins_state")
public class UnLifeInsState implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险保单扫描件编号,后续对应字段名为state_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "保单ID")
    @TableField("policy_id")
    private Integer policyId;
    @ApiModelProperty(value = "前处理进度：basecode CJ 0投保,1录单,2承保,3回执,4复核,5回访,6对账,7发首佣（直提部分）,8发积分")
    @TableField("progress")
    private String progress;
    @ApiModelProperty(value = "影像资料上传地址")
    @TableField("picture")
    private String picture;
    @ApiModelProperty(value = "文件时间")
    @TableField("file_time")
    private Integer fileTime;
    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "修改者")
    @TableField("modify_by")
    private Integer modifyBy;
    @ApiModelProperty(value = "修改时间")
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "warranty_picture 表 wp_id")
    @TableField("wp_id")
    private Integer wpId;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String POLICY_ID = "policy_id";

    public static final String PROGRESS = "progress";

    public static final String PICTURE = "picture";

    public static final String FILE_TIME = "file_time";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String WP_ID = "wp_id";

    public static final String DELETE_TIME = "delete_time";

}