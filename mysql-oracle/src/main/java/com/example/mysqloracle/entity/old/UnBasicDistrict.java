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
 * @Description:省市区
 * @since 2021-05-10
 */
@ApiModel(value = "省市区")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_basic_district")
public class UnBasicDistrict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("parent_id")
    private Integer parentId;
    @TableField("initial")
    private String initial;
    @TableField("initials")
    private String initials;
    @TableField("pinyin")
    private String pinyin;
    @TableField("code")
    private String code;
    @TableField("area_code")
    private String areaCode;
    @ApiModelProperty(value = "邮政编码")
    @TableField("zip_code")
    private String zipCode;
    @ApiModelProperty(value = "等级：1省；2市；3区/县")
    @TableField("level")
    private Integer level;
    @TableField("order")
    private Integer order;
    @ApiModelProperty(value = "是否显示,0禁用,1正常,2恒大不显示")
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    private Integer createTime;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";

    public static final String INITIAL = "initial";

    public static final String INITIALS = "initials";

    public static final String PINYIN = "pinyin";

    public static final String CODE = "code";

    public static final String AREA_CODE = "area_code";

    public static final String ZIP_CODE = "zip_code";

    public static final String LEVEL = "level";

    public static final String ORDER = "order";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DELETE_TIME = "delete_time";

}