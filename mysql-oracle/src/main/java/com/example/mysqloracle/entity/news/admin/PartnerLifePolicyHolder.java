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
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author blueSky
 * @Description:寿险保单-投保人
 * @since 2021-05-17
 */
@ApiModel(value = "寿险保单-投保人")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("partner_life_policy_holder")
public class PartnerLifePolicyHolder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "保单表id")
    @TableId(value = "life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "1男2女")
    @TableField("gender")
    private Integer gender;
    @ApiModelProperty(value = "出生日期：格式：1999-01-01")
    @TableField("birthday")
    private LocalDate birthday;
    @ApiModelProperty(value = "证件类型1身份证2护照3出生证4户口本")
    @TableField("id_type")
    private Integer idType;
    @ApiModelProperty(value = "证件号码")
    @TableField("id_no")
    private String idNo;
    @TableField("id_concat")
    private String idConcat;
    @ApiModelProperty(value = "证件有效截止日期")
    @TableField("id_expired_date")
    private LocalDate idExpiredDate;
    @ApiModelProperty(value = "1已婚2未婚3离婚4丧偶5其他")
    @TableField("marriage")
    private Integer marriage;
    @ApiModelProperty(value = "身高(CM)")
    @TableField("height")
    private Integer height;
    @ApiModelProperty(value = "体重(KG)")
    @TableField("weight")
    private BigDecimal weight;
    @ApiModelProperty(value = "年收入：单位万元")
    @TableField("income")
    private BigDecimal income;
    @ApiModelProperty(value = "收入来源")
    @TableField("income_source")
    private Integer incomeSource;
    @ApiModelProperty(value = "工作单位")
    @TableField("company")
    private String company;
    @ApiModelProperty(value = "手机")
    @TableField("mobile")
    private String mobile;
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;
    @ApiModelProperty(value = "是否有社保")
    @TableField("is_ssid")
    private Integer isSsid;
    @ApiModelProperty(value = "开户地，对应省市区表的id")
    @TableField("bank_area")
    private String bankArea;
    @ApiModelProperty(value = "开户行")
    @TableField("bank_name")
    private String bankName;
    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_no")
    private String bankNo;
    @ApiModelProperty(value = "持卡人")
    @TableField("bank_holder")
    private String bankHolder;
    @ApiModelProperty(value = "税收类型")
    @TableField("is_tax")
    private Integer isTax;
    @ApiModelProperty(value = "详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "省份id")
    @TableField("province")
    private Integer province;
    @ApiModelProperty(value = "城市id")
    @TableField("city")
    private Integer city;
    @ApiModelProperty(value = "地区id")
    @TableField("district")
    private Integer district;
    @ApiModelProperty(value = "国籍")
    @TableField("nationality")
    private Integer nationality;
    @ApiModelProperty(value = "邮编")
    @TableField("zip")
    private String zip;
    @ApiModelProperty(value = "扩展字段")
    @TableField("extension")
    private String extension;
    @ApiModelProperty(value = "职业")
    @TableField("job_code")
    private String jobCode;


    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String NAME = "name";

    public static final String GENDER = "gender";

    public static final String BIRTHDAY = "birthday";

    public static final String ID_TYPE = "id_type";

    public static final String ID_NO = "id_no";

    public static final String ID_CONCAT = "id_concat";

    public static final String ID_EXPIRED_DATE = "id_expired_date";

    public static final String MARRIAGE = "marriage";

    public static final String HEIGHT = "height";

    public static final String WEIGHT = "weight";

    public static final String INCOME = "income";

    public static final String INCOME_SOURCE = "income_source";

    public static final String COMPANY = "company";

    public static final String MOBILE = "mobile";

    public static final String EMAIL = "email";

    public static final String IS_SSID = "is_ssid";

    public static final String BANK_AREA = "bank_area";

    public static final String BANK_NAME = "bank_name";

    public static final String BANK_NO = "bank_no";

    public static final String BANK_HOLDER = "bank_holder";

    public static final String IS_TAX = "is_tax";

    public static final String ADDRESS = "address";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    public static final String NATIONALITY = "nationality";

    public static final String ZIP = "zip";

    public static final String EXTENSION = "extension";

    public static final String JOB_CODE = "job_code";

}