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
 * @Description:组织架构
 * @since 2021-05-07
 */
@ApiModel(value = "组织架构")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("org")
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "上级id，0表示总公司")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty(value = "所有祖先列表")
    @TableField("parent_id_json")
    private String parentIdJson;
    @ApiModelProperty(value = "类型：1、内勤2、外勤3、渠道0表示公司")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "机构层级: 1：T1(总公司级)、2：T2(省级)、 3：T3(市级)、 4：T4(县级)、5:渠道、6:部门、7:团队")
    @TableField("level")
    private Integer level;
    @ApiModelProperty(value = "来源合作方id 0表示本身")
    @TableField("source_partner_id")
    private Long sourcePartnerId;
    @ApiModelProperty(value = "来源机构id 0表示本身")
    @TableField("source_org_id")
    private Long sourceOrgId;
    @ApiModelProperty(value = "来源上级id，0表示总公司")
    @TableField("source_parent_id")
    private Long sourceParentId;
    @ApiModelProperty(value = "机构渠道id")
    @TableField("org_channel_id")
    private Long orgChannelId;
    @ApiModelProperty(value = "组织名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "组织编码")
    @TableField("code")
    private String code;
    @ApiModelProperty(value = "公司logo")
    @TableField("logo")
    private String logo;
    @ApiModelProperty(value = "管理岗用户id")
    @TableField("master_user_id")
    private Long masterUserId;
    @ApiModelProperty(value = "组织负责人")
    @TableField("master")
    private String master;
    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;
    @ApiModelProperty(value = "联系电话")
    @TableField("tel")
    private String tel;
    @ApiModelProperty(value = "合作状态：1、正常合作2、停止合作")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "行政区域归属省code")
    @TableField("belong_provice_code")
    private Integer belongProviceCode;
    @ApiModelProperty(value = "行政区域归属市code")
    @TableField("belong_city_code")
    private Integer belongCityCode;
    @ApiModelProperty(value = "牌照类型：1、全国2、区域3、双牌照")
    @TableField("license_type")
    private Integer licenseType;
    @ApiModelProperty(value = "牌照编码")
    @TableField("license_number")
    private String licenseNumber;
    @ApiModelProperty(value = "渠道类型：1、总销2、分销")
    @TableField("channel_type")
    private Integer channelType;
    @ApiModelProperty(value = "冗余字段，是否属于渠道1、是0不是")
    @TableField("is_channel")
    private Integer isChannel;
    @ApiModelProperty(value = "是否网销牌照：1、是0、否")
    @TableField("is_online_license")
    private Integer isOnlineLicense;
    @ApiModelProperty(value = "网销牌照编码")
    @TableField("online_number")
    private String onlineNumber;
    @ApiModelProperty(value = "是否保联合作方：1、是0、否")
    @TableField("is_partner")
    private Integer isPartner;
    @ApiModelProperty(value = "是否报送组织人员信息")
    @TableField("is_sync")
    private Integer isSync;
    @ApiModelProperty(value = "证件类型：1、组织机构代码2、多证合一")
    @TableField("certificate_type")
    private Integer certificateType;
    @ApiModelProperty(value = "证件号码")
    @TableField("certificate_number")
    private String certificateNumber;
    @ApiModelProperty(value = "机构层级排序值，值越小越靠前")
    @TableField("level_order")
    private Integer levelOrder;
    @ApiModelProperty(value = "发薪日")
    @TableField("pay_day")
    private Integer payDay;
    @ApiModelProperty(value = "系统用户id")
    @TableField("system_user_id")
    private Integer systemUserId;
    @ApiModelProperty(value = "是否删除：1、删除 0、未删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @ApiModelProperty(value = "（旧）入职合同PDF填充配置")
    @TableField("config_agent_contract")
    private String configAgentContract;
    @ApiModelProperty(value = "（旧）API接口截至日期")
    @TableField("api_date")
    private Integer apiDate;
    @ApiModelProperty(value = "(旧) APPKEY")
    @TableField("app_key")
    private String appKey;
    @ApiModelProperty(value = "(旧) 渠道方密钥")
    @TableField("key")
    private String key;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_ID_JSON = "parent_id_json";

    public static final String TYPE = "type";

    public static final String LEVEL = "level";

    public static final String SOURCE_PARTNER_ID = "source_partner_id";

    public static final String SOURCE_ORG_ID = "source_org_id";

    public static final String SOURCE_PARENT_ID = "source_parent_id";

    public static final String ORG_CHANNEL_ID = "org_channel_id";

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String LOGO = "logo";

    public static final String MASTER_USER_ID = "master_user_id";

    public static final String MASTER = "master";

    public static final String CONTACTS = "contacts";

    public static final String TEL = "tel";

    public static final String STATUS = "status";

    public static final String BELONG_PROVICE_CODE = "belong_provice_code";

    public static final String BELONG_CITY_CODE = "belong_city_code";

    public static final String LICENSE_TYPE = "license_type";

    public static final String LICENSE_NUMBER = "license_number";

    public static final String CHANNEL_TYPE = "channel_type";

    public static final String IS_CHANNEL = "is_channel";

    public static final String IS_ONLINE_LICENSE = "is_online_license";

    public static final String ONLINE_NUMBER = "online_number";

    public static final String IS_PARTNER = "is_partner";

    public static final String IS_SYNC = "is_sync";

    public static final String CERTIFICATE_TYPE = "certificate_type";

    public static final String CERTIFICATE_NUMBER = "certificate_number";

    public static final String LEVEL_ORDER = "level_order";

    public static final String PAY_DAY = "pay_day";

    public static final String SYSTEM_USER_ID = "system_user_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String CONFIG_AGENT_CONTRACT = "config_agent_contract";

    public static final String API_DATE = "api_date";

    public static final String APP_KEY = "app_key";

    public static final String KEY = "key";
}