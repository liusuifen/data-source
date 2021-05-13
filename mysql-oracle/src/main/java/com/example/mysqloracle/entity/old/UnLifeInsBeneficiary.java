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
import java.math.BigDecimal;

/**
 * @author blueSky
 * @Description:寿险保单-受益人信息-子表
 * @since 2021-05-10
 */
@ApiModel(value = "寿险保单-受益人信息-子表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_ins_beneficiary")
public class UnLifeInsBeneficiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险保单受益人编号,后续对应字段名为bene_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "保单编号（对应hmhome.un_life_policy_main.policy_id）")
    @TableField("policy_id")
    private Integer policyId;
    @ApiModelProperty(value = "受益人姓名")
    @TableField("fullname")
    private String fullname;
    @ApiModelProperty(value = "受益人性别")
    @TableField("gender")
    private String gender;
    @ApiModelProperty(value = "受益人出生日期：格式：1999-01-01")
    @TableField("birthday")
    private String birthday;
    @ApiModelProperty(value = "受益人证件类型")
    @TableField("ID_type")
    private String idType;
    @ApiModelProperty(value = "受益人证件号码")
    @TableField("ID_no")
    private String idNo;
    @ApiModelProperty(value = "受益人有效截止日期：默认长期有效：9999-12-31")
    @TableField("ID_expire_end")
    private String idExpireEnd;
    @ApiModelProperty(value = "国籍")
    @TableField("nation")
    private String nation;
    @ApiModelProperty(value = "电话")
    @TableField("tel")
    private String tel;
    @ApiModelProperty(value = "受益人与被保险人关系：本人 / 配偶 / 父母 / 子女 / 其他(详细)")
    @TableField("beneficiary_is_insured")
    private String beneficiaryIsInsured;
    @ApiModelProperty(value = "受益顺序：默认为1最高级，数字递增->受益排位降低。")
    @TableField("sort_order")
    private Integer sortOrder;
    @ApiModelProperty(value = "受益比例：记录百分比值。")
    @TableField("rate")
    private BigDecimal rate;
    @ApiModelProperty(value = "受益人地址类型：0=否,1=同投保人,2=同被保险人.")
    @TableField("addr_type")
    private Integer addrType;
    @ApiModelProperty(value = "职业")
    @TableField("job_code")
    private String jobCode;
    @ApiModelProperty(value = "税收类型")
    @TableField("tax_type")
    private String taxType;
    @ApiModelProperty(value = "地区代码:省")
    @TableField("province")
    private String province;
    @ApiModelProperty(value = "地区代码:市")
    @TableField("city")
    private String city;
    @ApiModelProperty(value = "地区代码:区")
    @TableField("district")
    private String district;
    @ApiModelProperty(value = "受益人详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "邮编")
    @TableField("zip")
    private String zip;
    @ApiModelProperty(value = "受益人类别：0=不作区分,1=生存金受益人,2=身故受益人.")
    @TableField("type")
    private String type;
    @ApiModelProperty(value = "身故金领取方式")
    @TableField("death_get_type")
    private String deathGetType;
    @ApiModelProperty(value = "身故金领取频率")
    @TableField("death_get_rate")
    private String deathGetRate;
    @ApiModelProperty(value = "身故金领取比例")
    @TableField("death_get_ratio")
    private String deathGetRatio;
    @ApiModelProperty(value = "身故金起领年龄")
    @TableField("death_get_start")
    private String deathGetStart;
    @ApiModelProperty(value = "身故金领至年龄")
    @TableField("death_get_end")
    private String deathGetEnd;
    @ApiModelProperty(value = "身故金计息方式")
    @TableField("death_rate")
    private String deathRate;
    @ApiModelProperty(value = "受益人状态：0=作废,1=正常.")
    @TableField("state")
    private Boolean state;
    @ApiModelProperty(value = "受益人列表创建人编号")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "受益人列表创建时间戳")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "受益人列表修改人编号")
    @TableField("modify_by")
    private Integer modifyBy;
    @ApiModelProperty(value = "受益人列表修改时间戳")
    @TableField("modify_time")
    private Integer modifyTime;
    @TableField("bene_id")
    private Integer beneId;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String POLICY_ID = "policy_id";

    public static final String FULLNAME = "fullname";

    public static final String GENDER = "gender";

    public static final String BIRTHDAY = "birthday";

    public static final String ID_TYPE = "ID_type";

    public static final String ID_NO = "ID_no";

    public static final String ID_EXPIRE_END = "ID_expire_end";

    public static final String NATION = "nation";

    public static final String TEL = "tel";

    public static final String BENEFICIARY_IS_INSURED = "beneficiary_is_insured";

    public static final String SORT_ORDER = "sort_order";

    public static final String RATE = "rate";

    public static final String ADDR_TYPE = "addr_type";

    public static final String JOB_CODE = "job_code";

    public static final String TAX_TYPE = "tax_type";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    public static final String ADDRESS = "address";

    public static final String ZIP = "zip";

    public static final String TYPE = "type";

    public static final String DEATH_GET_TYPE = "death_get_type";

    public static final String DEATH_GET_RATE = "death_get_rate";

    public static final String DEATH_GET_RATIO = "death_get_ratio";

    public static final String DEATH_GET_START = "death_get_start";

    public static final String DEATH_GET_END = "death_get_end";

    public static final String DEATH_RATE = "death_rate";

    public static final String STATE = "state";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String BENE_ID = "bene_id";

    public static final String DELETE_TIME = "delete_time";

}