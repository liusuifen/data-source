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
 * @Description:渠道商-广告图展示图
 * @since 2021-06-08
 */
@ApiModel(value = "渠道商-广告图展示图")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_basic_banner")
public class UnBasicBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "图位置，关联 basic_enum 表")
    @TableField("location")
    private String location;
    @ApiModelProperty(value = "图片名")
    @TableField("img_name")
    private String imgName;
    @ApiModelProperty(value = "图片地址")
    @TableField("image")
    private String image;
    @ApiModelProperty(value = "超链接")
    @TableField("link")
    private String link;
    @ApiModelProperty(value = "有效起始时间")
    @TableField("start_time")
    private String startTime;
    @ApiModelProperty(value = "终止时间")
    @TableField("end_time")
    private String endTime;
    @TableField("create_time")
    private Integer createTime;
    @TableField("create_by")
    private Integer createBy;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String LOCATION = "location";

    public static final String IMG_NAME = "img_name";

    public static final String IMAGE = "image";

    public static final String LINK = "link";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_BY = "create_by";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

}