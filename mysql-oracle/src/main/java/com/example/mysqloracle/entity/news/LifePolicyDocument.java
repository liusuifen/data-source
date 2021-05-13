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
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险保单相关文档表
 * @since 2021-05-11
 */
@ApiModel(value = "寿险保单相关文档表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("life_policy_document")
public class LifePolicyDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "保单id")
    @TableField("life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "文档名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "文档地址")
    @TableField("url")
    private String url;
    @ApiModelProperty(value = "上传时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "文档类型")
    @TableField("type")
    private String type;
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "上传人")
    @TableField("upload_user")
    private String uploadUser;
    @TableField("upload_user_id")
    private Long uploadUserId;


    public static final String ID = "id";

    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String NAME = "name";

    public static final String URL = "url";

    public static final String CREATED_AT = "created_at";

    public static final String TYPE = "type";

    public static final String IS_DELETED = "is_deleted";

    public static final String UPLOAD_USER = "upload_user";

    public static final String UPLOAD_USER_ID = "upload_user_id";

}