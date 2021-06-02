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
 * @Description:培训_视频主表
 * @since 2021-05-28
 */
@ApiModel(value = "培训_视频主表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_video_learn")
public class UnVideoLearn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "渠道id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "缩略图")
    @TableField("img")
    private String img;
    @ApiModelProperty(value = "0:无媒体；1:视频;2:音频")
    @TableField("media_type")
    private Integer mediaType;
    @ApiModelProperty(value = "部门id集合")
    @TableField("dept")
    private String dept;
    @ApiModelProperty(value = "有权限的所有子部门")
    @TableField("dept_child")
    private String deptChild;
    @ApiModelProperty(value = "分类")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;
    @ApiModelProperty(value = "视频简介")
    @TableField("introduction")
    private String introduction;
    @ApiModelProperty(value = "视频地址")
    @TableField("media_url")
    private String mediaUrl;
    @ApiModelProperty(value = "有效观看时间")
    @TableField("effective_time")
    private Integer effectiveTime;
    @ApiModelProperty(value = "编辑器")
    @TableField("desc")
    private String desc;
    @ApiModelProperty(value = "是否启用")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "0000-00-00  考勤")
    @TableField("date")
    private String date;
    @ApiModelProperty(value = "搜索关键字")
    @TableField("keyword")
    private String keyword;
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
    @TableField("is_public")
    private Integer isPublic;
    @ApiModelProperty(value = "打卡后图片")
    @TableField("sign_img")
    private String signImg;
    @ApiModelProperty(value = "打卡后标语")
    @TableField("sign_text")
    private String signText;
    @ApiModelProperty(value = "打卡二维码内容")
    @TableField("qr_code_text")
    private String qrCodeText;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String IMG = "img";

    public static final String MEDIA_TYPE = "media_type";

    public static final String DEPT = "dept";

    public static final String DEPT_CHILD = "dept_child";

    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String INTRODUCTION = "introduction";

    public static final String MEDIA_URL = "media_url";

    public static final String EFFECTIVE_TIME = "effective_time";

    public static final String DESC = "desc";

    public static final String STATE = "state";

    public static final String DATE = "date";

    public static final String KEYWORD = "keyword";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_BY = "create_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String DELETE_TIME = "delete_time";

    public static final String IS_PUBLIC = "is_public";

    public static final String SIGN_IMG = "sign_img";

    public static final String SIGN_TEXT = "sign_text";

    public static final String QR_CODE_TEXT = "qr_code_text";

}