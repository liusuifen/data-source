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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:客户中心-客户表
 * @since 2021-05-19
 */
@ApiModel(value = "客户中心-客户表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "客户类型，投保人还是被保人,1=投保，2=被保人")
    @TableField("customer_type")
    private String customerType;
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "证件类型：1、身份证2、护照3、出生证4、户口本")
    @TableField("certificate_type")
    private Integer certificateType;
    @ApiModelProperty(value = "证件号码")
    @TableField("certificate_number")
    private String certificateNumber;
    @ApiModelProperty(value = "证件类型与证件号码的合并，例如 1440305xxxxxxxxxx")
    @TableField("certificate_concat")
    private String certificateConcat;
    @ApiModelProperty(value = "性别：1、男2、女")
    @TableField("sex")
    private Integer sex;
    @ApiModelProperty(value = "出生日期")
    @TableField("birthday")
    private LocalDate birthday;
    @ApiModelProperty(value = "联系方式")
    @TableField("mobile")
    private String mobile;
    @ApiModelProperty(value = "联系地址省code")
    @TableField("province_code")
    private Integer provinceCode;
    @ApiModelProperty(value = "联系地址市code")
    @TableField("city_code")
    private Integer cityCode;
    @ApiModelProperty(value = "联系地址区code")
    @TableField("district_code")
    private Integer districtCode;
    @ApiModelProperty(value = "联系详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "类型：1、保单客户 2、普通客户")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "出单代理人id")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty(value = "是否删除：1、删除 0、未删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String CUSTOMER_TYPE = "customer_type";

    public static final String NAME = "name";

    public static final String CERTIFICATE_TYPE = "certificate_type";

    public static final String CERTIFICATE_NUMBER = "certificate_number";

    public static final String CERTIFICATE_CONCAT = "certificate_concat";

    public static final String SEX = "sex";

    public static final String BIRTHDAY = "birthday";

    public static final String MOBILE = "mobile";

    public static final String PROVINCE_CODE = "province_code";

    public static final String CITY_CODE = "city_code";

    public static final String DISTRICT_CODE = "district_code";

    public static final String ADDRESS = "address";

    public static final String EMAIL = "email";

    public static final String REMARK = "remark";

    public static final String TYPE = "type";

    public static final String USER_ID = "user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}