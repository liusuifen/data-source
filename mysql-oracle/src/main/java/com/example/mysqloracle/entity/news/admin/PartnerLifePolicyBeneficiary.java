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
 * @Description:寿险保单受益人表
 * @since 2021-05-17
 */
@ApiModel(value = "寿险保单受益人表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("partner_life_policy_beneficiary")
public class PartnerLifePolicyBeneficiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "保单表id")
    @TableField("life_policy_id")
    private Long lifePolicyId;
    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "1男2女")
    @TableField("gender")
    private Integer gender;
    @ApiModelProperty(value = "出生日期")
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
    @ApiModelProperty(value = "证件过期日期")
    @TableField("id_expired_date")
    private LocalDate idExpiredDate;
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;
    @ApiModelProperty(value = "电话")
    @TableField("mobile")
    private String mobile;
    @ApiModelProperty(value = "与被保险人关系1本人2 配偶3子女4父母5其他")
    @TableField("relation")
    private Integer relation;
    @ApiModelProperty(value = "与被保险人关系文字")
    @TableField("relation_name")
    private String relationName;
    @ApiModelProperty(value = "受益顺序：默认为1最高级，数字递增->受益排位降低。")
    @TableField("rank")
    private Integer rank;
    @ApiModelProperty(value = "受益比例：记录百分比值。")
    @TableField("ratio")
    private BigDecimal ratio;
    @ApiModelProperty(value = "地区代码:省")
    @TableField("province")
    private Integer province;
    @ApiModelProperty(value = "地区代码:市")
    @TableField("city")
    private Integer city;
    @ApiModelProperty(value = "地区代码:区")
    @TableField("district")
    private Integer district;
    @ApiModelProperty(value = "详细地址")
    @TableField("address")
    private String address;
    @ApiModelProperty(value = "1生存金受益人2身故受益人")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "身高")
    @TableField("height")
    private Integer height;
    @ApiModelProperty(value = "体重")
    @TableField("weight")
    private Float weight;
    @ApiModelProperty(value = "国籍")
    @TableField("nationality")
    private Integer nationality;
    @ApiModelProperty(value = "扩展字段")
    @TableField("extension")
    private String extension;
    @ApiModelProperty(value = "税收类型")
    @TableField("tax_type")
    private String taxType;
    @ApiModelProperty(value = "邮编")
    @TableField("zip")
    private String zip;
    @ApiModelProperty(value = "来源Id(更新分销数据时使用)")
    @TableField("source_id")
    private Long sourceId;


    public static final String ID = "id";

    public static final String LIFE_POLICY_ID = "life_policy_id";

    public static final String NAME = "name";

    public static final String GENDER = "gender";

    public static final String BIRTHDAY = "birthday";

    public static final String ID_TYPE = "id_type";

    public static final String ID_NO = "id_no";

    public static final String ID_CONCAT = "id_concat";

    public static final String ID_EXPIRED_DATE = "id_expired_date";

    public static final String EMAIL = "email";

    public static final String MOBILE = "mobile";

    public static final String RELATION = "relation";

    public static final String RELATION_NAME = "relation_name";

    public static final String RANK = "rank";

    public static final String RATIO = "ratio";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String DISTRICT = "district";

    public static final String ADDRESS = "address";

    public static final String TYPE = "type";

    public static final String IS_DELETED = "is_deleted";

    public static final String HEIGHT = "height";

    public static final String WEIGHT = "weight";

    public static final String NATIONALITY = "nationality";

    public static final String EXTENSION = "extension";

    public static final String TAX_TYPE = "tax_type";

    public static final String ZIP = "zip";

    public static final String SOURCE_ID = "source_id";

}