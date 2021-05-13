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
 * @Description:保险公司 - 基本信息
 * @since 2021-05-06
 */
@ApiModel(value = "保险公司 - 基本信息")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键索引")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "对应保联后台保险公司信息主键id")
    @TableField("admin_id")
    private Long adminId;
    @ApiModelProperty(value = "关联总公司id,若该公司性质为总公司，则为0")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty(value = "保险公司编码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "保险公司全称")
    @TableField("full_name")
    private String fullName;
    @ApiModelProperty(value = "保险公司简称")
    @TableField("short_name")
    private String shortName;
    @ApiModelProperty(value = "公司性质，1 - 总公司，2 - 分公司")
    @TableField("category")
    private Integer category;
    @ApiModelProperty(value = "牌照类型，1 - 全国，2 - 区域，3 - 按机构")
    @TableField("license_type")
    private Integer licenseType;
    @ApiModelProperty(value = "是否有网销牌照，1 - 有，0 - 无")
    @TableField("has_net_license")
    private Integer hasNetLicense;
    @ApiModelProperty(value = "总部所属省")
    @TableField("province")
    private Integer province;
    @ApiModelProperty(value = "总部所属市")
    @TableField("city")
    private Integer city;
    @ApiModelProperty(value = "总部所属区")
    @TableField("district")
    private Integer district;
    @ApiModelProperty(value = "总部详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "运营地点，省")
    @TableField("location_province")
    private Integer locationProvince;
    @ApiModelProperty(value = "运营地点，市")
    @TableField("location_city")
    private Integer locationCity;
    @ApiModelProperty(value = "保险公司商标图片url")
    @TableField("logo")
    private String logo;
    @ApiModelProperty(value = "保险公司类型，1 - 寿险，2 - 产险")
    @TableField("company_type")
    private Integer companyType;
    @ApiModelProperty(value = "渠道对于该保司的合作状态， 1- 开启合作，2 - 终止合作")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "证件类型，1 - 工商营业执照，2 - 经营许可证")
    @TableField("id_type")
    private Integer idType;
    @ApiModelProperty(value = "证件号码")
    @TableField("id_no")
    private String idNo;
    @ApiModelProperty(value = "法人代表名称")
    @TableField("boss_name")
    private String bossName;
    @ApiModelProperty(value = "纳税人名称")
    @TableField("taxpayer")
    private String taxpayer;
    @ApiModelProperty(value = "纳税人识别号")
    @TableField("taxpayer_id_no")
    private String taxpayerIdNo;
    @ApiModelProperty(value = "开户行名称")
    @TableField("bank_name")
    private String bankName;
    @ApiModelProperty(value = "开户用户名")
    @TableField("account_name")
    private String accountName;
    @ApiModelProperty(value = "开户账号")
    @TableField("account_no")
    private String accountNo;
    @ApiModelProperty(value = "纳税人地址")
    @TableField("taxpayer_address")
    private String taxpayerAddress;
    @ApiModelProperty(value = "纳税人联系电话")
    @TableField("taxpayer_phone_number")
    private String taxpayerPhoneNumber;
    @ApiModelProperty(value = "最后选择的折标类型： 1 - 行业折标，2 - 自定义折标")
    @TableField("last_ratio_type")
    private Integer lastRatioType;
    @ApiModelProperty(value = "写入操作者id")
    @TableField("system_user_id")
    private Long systemUserId;
    @ApiModelProperty(value = "删除标记，1 - 删除， 0 - 未删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "记录创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "记录修改时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String ADMIN_ID = "admin_id";

    public static final String PARENT_ID = "parent_id";

    public static final String CODE = "code";

    public static final String FULL_NAME = "full_name";

    public static final String SHORT_NAME = "short_name";

    public static final String CATEGORY = "category";

    public static final String LICENSE_TYPE = "license_type";

    public static final String HAS_NET_LICENSE = "has_net_license";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    public static final String ADDRESS = "address";

    public static final String LOCATION_PROVINCE = "location_province";

    public static final String LOCATION_CITY = "location_city";

    public static final String LOGO = "logo";

    public static final String COMPANY_TYPE = "company_type";

    public static final String STATUS = "status";

    public static final String ID_TYPE = "id_type";

    public static final String ID_NO = "id_no";

    public static final String BOSS_NAME = "boss_name";

    public static final String TAXPAYER = "taxpayer";

    public static final String TAXPAYER_ID_NO = "taxpayer_id_no";

    public static final String BANK_NAME = "bank_name";

    public static final String ACCOUNT_NAME = "account_name";

    public static final String ACCOUNT_NO = "account_no";

    public static final String TAXPAYER_ADDRESS = "taxpayer_address";

    public static final String TAXPAYER_PHONE_NUMBER = "taxpayer_phone_number";

    public static final String LAST_RATIO_TYPE = "last_ratio_type";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

}