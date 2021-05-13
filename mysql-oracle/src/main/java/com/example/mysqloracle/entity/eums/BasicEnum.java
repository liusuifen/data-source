package com.example.mysqloracle.entity.eums;

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
 * @Description:枚举管理
 * @since 2021-05-08
 */
@ApiModel(value = "枚举管理")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("basic_enum")
public class BasicEnum implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("pid")
    private String pid;
    @TableField("module")
    private String module;
    @ApiModelProperty(value = "类型")
    @TableField("category")
    private String category;
    @ApiModelProperty(value = "保险公司ID|0为通用")
    @TableField("company")
    private Integer company;
    @ApiModelProperty(value = "所属产品，为空表示公共")
    @TableField("product")
    private String product;
    @ApiModelProperty(value = "枚举代号")
    @TableField("enum_code")
    private String enumCode;
    @ApiModelProperty(value = "枚举名称")
    @TableField("enum_name")
    private String enumName;
    @ApiModelProperty(value = "新系统值")
    @TableField("new_value")
    private String newValue;
    @ApiModelProperty(value = "枚举值")
    @TableField("enum_value")
    private String enumValue;
    @ApiModelProperty(value = "扩展字段json格式")
    @TableField("extend")
    private String extend;
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty(value = "是否试算基础数据")
    @TableField("is_calc")
    private Integer isCalc;
    @ApiModelProperty(value = "基础数据类型,0：不区分类型；1：适用后台；2：适用在线投保；3：适用于手机录单；4：仅作APP展示")
    @TableField("type")
    private String type;
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_time")
    private Integer createTime;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "原表if_id")
    @TableField("if_id")
    private String ifId;
    @ApiModelProperty(value = "原表(hm_safegoods_category的scat_id)")
    @TableField("scat_id")
    private String scatId;
    @TableField("sa_id")
    private String saId;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String MODULE = "module";

    public static final String CATEGORY = "category";

    public static final String COMPANY = "company";

    public static final String PRODUCT = "product";

    public static final String ENUM_CODE = "enum_code";

    public static final String ENUM_NAME = "enum_name";

    public static final String NEW_VALUE = "new_value";

    public static final String ENUM_VALUE = "enum_value";

    public static final String EXTEND = "extend";

    public static final String SORT = "sort";

    public static final String IS_CALC = "is_calc";

    public static final String TYPE = "type";

    public static final String STATE = "state";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String IF_ID = "if_id";

    public static final String SCAT_ID = "scat_id";

    public static final String SA_ID = "sa_id";

}