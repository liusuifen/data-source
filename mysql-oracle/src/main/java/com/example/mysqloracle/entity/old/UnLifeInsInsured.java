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
 * @Description:寿险保单-被保险人信息-子表
 * @since 2021-05-07
 */
@ApiModel(value = "寿险保单-被保险人信息-子表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_ins_insured")
public class UnLifeInsInsured implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险保单被保险人编号,后续对应字段名为insured_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "保单编号（对应hmhome.un_life_policy_main.policy_id）")
    @TableField("policy_id")
    private Integer policyId;
    @ApiModelProperty(value = "被保险人姓名")
    @TableField("insured_name")
    private String insuredName;
    @ApiModelProperty(value = "性别")
    @TableField("insured_gender")
    private String insuredGender;
    @ApiModelProperty(value = "被保险人出生日期：格式：1999-01-01")
    @TableField("insured_birthday")
    private String insuredBirthday;
    @ApiModelProperty(value = "被保险人国籍")
    @TableField("insured_nation")
    private String insuredNation;
    @ApiModelProperty(value = "被保险人证件类型")
    @TableField("insured_ID_type")
    private String insuredIdType;
    @ApiModelProperty(value = "被保险人证件号码")
    @TableField("insured_ID_no")
    private String insuredIdNo;
    @ApiModelProperty(value = "被保险人证件有效截止日期：默认长期有效：9999-12-31")
    @TableField("insured_ID_expire_end")
    private String insuredIdExpireEnd;
    @ApiModelProperty(value = "被保险人身高(CM)")
    @TableField("insured_height")
    private BigDecimal insuredHeight;
    @ApiModelProperty(value = "被保险人体重(KG)")
    @TableField("insured_weight")
    private BigDecimal insuredWeight;
    @ApiModelProperty(value = "婚姻状态")
    @TableField("insured_marriage")
    private String insuredMarriage;
    @ApiModelProperty(value = "被保险人职业代码")
    @TableField("insured_job_code")
    private String insuredJobCode;
    @ApiModelProperty(value = "被保险人工作单位")
    @TableField("insured_company")
    private String insuredCompany;
    @ApiModelProperty(value = "被保险人公司职务")
    @TableField("insured_position")
    private String insuredPosition;
    @ApiModelProperty(value = "被保险人工作单位地址")
    @TableField("insured_company_address")
    private String insuredCompanyAddress;
    @ApiModelProperty(value = "被保险人工作单位邮编")
    @TableField("insured_company_zip")
    private String insuredCompanyZip;
    @ApiModelProperty(value = "被保险人平均年收入：单位万元。")
    @TableField("insured_salary_avg")
    private BigDecimal insuredSalaryAvg;
    @ApiModelProperty(value = "被保险人收入来源：工资,奖金 / 分红,遗产,房租,股票 / 债券 / 工薪 / 私营 / 证券投资 / 银行利息 / ,其它(详细).")
    @TableField("insured_salary_from")
    private String insuredSalaryFrom;
    @ApiModelProperty(value = "住址省")
    @TableField("insured_home_province")
    private String insuredHomeProvince;
    @ApiModelProperty(value = "住址市")
    @TableField("insured_home_city")
    private String insuredHomeCity;
    @ApiModelProperty(value = "住址区")
    @TableField("insured_home_district")
    private String insuredHomeDistrict;
    @ApiModelProperty(value = "被保险人现 / 常住址")
    @TableField("insured_home_address")
    private String insuredHomeAddress;
    @ApiModelProperty(value = "被保险人住址邮编")
    @TableField("insured_home_zip")
    private String insuredHomeZip;
    @ApiModelProperty(value = "联系地址:省")
    @TableField("insured_contact_province")
    private String insuredContactProvince;
    @ApiModelProperty(value = "通讯地址:市")
    @TableField("insured_contact_city")
    private String insuredContactCity;
    @ApiModelProperty(value = "通讯地址:区")
    @TableField("insured_contact_district")
    private String insuredContactDistrict;
    @ApiModelProperty(value = "被保险人联系地址")
    @TableField("insured_contact_address")
    private String insuredContactAddress;
    @ApiModelProperty(value = "被保险人联系地址邮编")
    @TableField("insured_contact_zip")
    private String insuredContactZip;
    @ApiModelProperty(value = "被保险人本人手机")
    @TableField("insured_mobile")
    private String insuredMobile;
    @ApiModelProperty(value = "被保险人联系电话")
    @TableField("insured_phone")
    private String insuredPhone;
    @ApiModelProperty(value = "被保险人单位固定电话")
    @TableField("insured_office_phone")
    private String insuredOfficePhone;
    @ApiModelProperty(value = "被保险人是否接受手机短信服务")
    @TableField("insured_is_sms")
    private String insuredIsSms;
    @ApiModelProperty(value = "被保险人电子邮箱")
    @TableField("insured_email")
    private String insuredEmail;
    @ApiModelProperty(value = "被保险人是否有社保")
    @TableField("has_insured_SSID")
    private String hasInsuredSsid;
    @ApiModelProperty(value = "学历")
    @TableField("insured_education")
    private String insuredEducation;
    @ApiModelProperty(value = "投保人是被保人的:本人 / 配偶 / 父母 / 子女 / 其他(详细)")
    @TableField("rel_holder_insured")
    private String relHolderInsured;
    @ApiModelProperty(value = "被保人是投保人的：本人 / 配偶 / 父母 / 子女 / 其他(详细)")
    @TableField("rel_insured_holder")
    private String relInsuredHolder;
    @ApiModelProperty(value = "json扩展字段")
    @TableField("extend_items")
    private String extendItems;
    @ApiModelProperty(value = "被保险人状态：0=作废,1=正常.")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "被保险人列表创建人编号")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "被保险人列表创建时间戳")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "被保险人列表修改人编号")
    @TableField("modify_by")
    private Integer modifyBy;
    @ApiModelProperty(value = "被保险人列表修改时间戳")
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "assured/assureds  表 assu_id")
    @TableField("assu_id")
    private Integer assuId;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String POLICY_ID = "policy_id";

    public static final String INSURED_NAME = "insured_name";

    public static final String INSURED_GENDER = "insured_gender";

    public static final String INSURED_BIRTHDAY = "insured_birthday";

    public static final String INSURED_NATION = "insured_nation";

    public static final String INSURED_ID_TYPE = "insured_ID_type";

    public static final String INSURED_ID_NO = "insured_ID_no";

    public static final String INSURED_ID_EXPIRE_END = "insured_ID_expire_end";

    public static final String INSURED_HEIGHT = "insured_height";

    public static final String INSURED_WEIGHT = "insured_weight";

    public static final String INSURED_MARRIAGE = "insured_marriage";

    public static final String INSURED_JOB_CODE = "insured_job_code";

    public static final String INSURED_COMPANY = "insured_company";

    public static final String INSURED_POSITION = "insured_position";

    public static final String INSURED_COMPANY_ADDRESS = "insured_company_address";

    public static final String INSURED_COMPANY_ZIP = "insured_company_zip";

    public static final String INSURED_SALARY_AVG = "insured_salary_avg";

    public static final String INSURED_SALARY_FROM = "insured_salary_from";

    public static final String INSURED_HOME_PROVINCE = "insured_home_province";

    public static final String INSURED_HOME_CITY = "insured_home_city";

    public static final String INSURED_HOME_DISTRICT = "insured_home_district";

    public static final String INSURED_HOME_ADDRESS = "insured_home_address";

    public static final String INSURED_HOME_ZIP = "insured_home_zip";

    public static final String INSURED_CONTACT_PROVINCE = "insured_contact_province";

    public static final String INSURED_CONTACT_CITY = "insured_contact_city";

    public static final String INSURED_CONTACT_DISTRICT = "insured_contact_district";

    public static final String INSURED_CONTACT_ADDRESS = "insured_contact_address";

    public static final String INSURED_CONTACT_ZIP = "insured_contact_zip";

    public static final String INSURED_MOBILE = "insured_mobile";

    public static final String INSURED_PHONE = "insured_phone";

    public static final String INSURED_OFFICE_PHONE = "insured_office_phone";

    public static final String INSURED_IS_SMS = "insured_is_sms";

    public static final String INSURED_EMAIL = "insured_email";

    public static final String HAS_INSURED_SSID = "has_insured_SSID";

    public static final String INSURED_EDUCATION = "insured_education";

    public static final String REL_HOLDER_INSURED = "rel_holder_insured";

    public static final String REL_INSURED_HOLDER = "rel_insured_holder";

    public static final String EXTEND_ITEMS = "extend_items";

    public static final String STATE = "state";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String ASSU_ID = "assu_id";

    public static final String DELETE_TIME = "delete_time";

}