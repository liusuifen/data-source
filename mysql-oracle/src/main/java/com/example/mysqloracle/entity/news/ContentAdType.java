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
 * @Description:内容管理-广告位分类
 * @since 2021-06-08
 */
@ApiModel(value = "内容管理-广告位分类")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("content_ad_type")
public class ContentAdType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "广告位名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "状态，1=有效，2=无效")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}