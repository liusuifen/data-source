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
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险保单相关文档表 1.投保人身份证正面
 * 2.投保人身份证反面
 * 3.被保人身份证正面
 * 4.被保人身份证反面
 * 5.被保人户口本
 * 6.被保人出生证
 * 7.银行卡
 * 8.指定受益人身份证明文件正面
 * 9.指定受益人身份证明文件反面
 * 10.银行自动转账授权书
 * 11.投保人人脸识别或拍照
 * 12.被保险人拍照
 * <p>
 * 101.投保人签名
 * 102.投保人拍照
 * 103.被保人签名
 * 104.被保人拍照
 * 105.代理人签名
 * @since 2021-05-17
 */
@ApiModel(value = "寿险保单相关文档表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("partner_life_policy_document")
public class PartnerLifePolicyDocument implements Serializable {

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
    @ApiModelProperty(value = "1.投保人证件正面")
    @TableField("type")
    private String type;
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "上传人")
    @TableField("upload_user")
    private String uploadUser;
    @ApiModelProperty(value = "来源Id(更新分销数据时使用)")
    @TableField("source_id")
    private Long sourceId;
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

    public static final String SOURCE_ID = "source_id";

    public static final String UPLOAD_USER_ID = "upload_user_id";

}