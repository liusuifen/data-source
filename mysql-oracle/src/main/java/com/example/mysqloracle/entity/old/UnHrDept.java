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
 * @Description:部门档
 * @since 2021-05-27
 */
@ApiModel(value = "部门档")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_hr_dept")
public class UnHrDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门编号,后续对应字段名为dept_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "上级部门")
    @TableField("pid")
    private Integer pid;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "部门名称")
    @TableField("dept_name")
    private String deptName;
    @ApiModelProperty(value = "状态：0禁用，1启用")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "是否统计，计算业绩：0否，1是")
    @TableField("count_sale")
    private Integer countSale;
    @ApiModelProperty(value = "是否计算佣金")
    @TableField("execute_commission")
    private Integer executeCommission;
    @ApiModelProperty(value = "0  地区区域（如：广东）  1 外勤部门  2 内勤部门")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "行政区域编码-省级")
    @TableField("code_province")
    private String codeProvince;
    @ApiModelProperty(value = "行政区域编码-市级")
    @TableField("code_city")
    private String codeCity;
    @ApiModelProperty(value = "联系方式")
    @TableField("tel")
    private String tel;
    @ApiModelProperty(value = "联系地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "注释改为：   1：T1（总公司）；2：T2（省/市级）；3：T3（支公司级）；4：T4（部门级）")
    @TableField("level")
    private Integer level;
    @ApiModelProperty(value = "是否配置基本法")
    @TableField("is_law")
    private Integer isLaw;
    @TableField("create_by")
    private Integer createBy;
    @TableField("create_time")
    private Integer createTime;
    @TableField("modify_by")
    private Integer modifyBy;
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "旧部门id")
    @TableField("did")
    private String did;
    @TableField("delete_time")
    private Integer deleteTime;
    @ApiModelProperty(value = "车险悦保行政区域编码-省级")
    @TableField("code_province_yuebao")
    private String codeProvinceYuebao;
    @ApiModelProperty(value = "车险悦保行政区域编码-市级")
    @TableField("code_city_yuebao")
    private String codeCityYuebao;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String CHANNEL_ID = "channel_id";

    public static final String DEPT_NAME = "dept_name";

    public static final String STATE = "state";

    public static final String COUNT_SALE = "count_sale";

    public static final String EXECUTE_COMMISSION = "execute_commission";

    public static final String TYPE = "type";

    public static final String CODE_PROVINCE = "code_province";

    public static final String CODE_CITY = "code_city";

    public static final String TEL = "tel";

    public static final String ADDRESS = "address";

    public static final String LEVEL = "level";

    public static final String IS_LAW = "is_law";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String DID = "did";

    public static final String DELETE_TIME = "delete_time";

    public static final String CODE_PROVINCE_YUEBAO = "code_province_yuebao";

    public static final String CODE_CITY_YUEBAO = "code_city_yuebao";

}