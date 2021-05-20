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
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险保单-投保单主表
 * @since 2021-05-17
 */
@ApiModel(value = "寿险保单-投保单主表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("partner_life_policy")
public class PartnerLifePolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @ApiModelProperty(value = "合作方id")
    @TableField("partner_id")
    private Long partnerId;
    @ApiModelProperty(value = "来源合作方id")
    @TableField("source_partner_id")
    private Long sourcePartnerId;
    @ApiModelProperty(value = "预投保编号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty(value = "投保单号/投保书号")
    @TableField("policy_no")
    private String policyNo;
    @ApiModelProperty(value = "保单正式编号")
    @TableField("policy_sn")
    private String policySn;
    @ApiModelProperty(value = "保险公司id")
    @TableField("company")
    private Long company;
    @ApiModelProperty(value = "保险公司类型1寿险2产险")
    @TableField("company_type")
    private Integer companyType;
    @ApiModelProperty(value = "承保进度10草稿11录单12预收13人工核保14拒保15承保16回执17复核18回访19对账20发佣21积分22核保")
    @TableField("progress")
    private Integer progress;
    @ApiModelProperty(value = "第几期保单")
    @TableField("period")
    private Integer period;
    @ApiModelProperty(value = "1待承保2承保3有效4撤单5犹豫期退保6退保7失效8终止")
    @TableField("status")
    private Integer status;
    @ApiModelProperty(value = "是否法定受益人，1是0否")
    @TableField("is_legal_beneficiary")
    private Integer isLegalBeneficiary;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "生效日期")
    @TableField("date_start")
    private LocalDate dateStart;
    @ApiModelProperty(value = "失效日期")
    @TableField("date_end")
    private LocalDate dateEnd;
    @ApiModelProperty(value = "扣款日期")
    @TableField("date_deduct")
    private LocalDate dateDeduct;
    @ApiModelProperty(value = "承保日期")
    @TableField("date_accept")
    private LocalDate dateAccept;
    @ApiModelProperty(value = "预收日期")
    @TableField("date_advance")
    private LocalDate dateAdvance;
    @ApiModelProperty(value = "是否自保件")
    @TableField("is_sale_self")
    private Integer isSaleSelf;
    @ApiModelProperty(value = "创建时间戳")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "修改时间戳")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
    @ApiModelProperty(value = "服务代理人")
    @TableField("service_user_json")
    private String serviceUserJson;
    @ApiModelProperty(value = "录单方式 1 - api对接，2 - 手工录单，3 - H5投保")
    @TableField("import_way")
    private Integer importWay;
    @ApiModelProperty(value = "来源1藏保图2保险公司平台")
    @TableField("source")
    private Integer source;
    @ApiModelProperty(value = "缴费方式1趸交2期缴")
    @TableField("pay_way")
    private Integer payWay;
    @ApiModelProperty(value = "缴费频率1年交2半年交3季缴4月缴5周缴")
    @TableField("pay_frequency")
    private Integer payFrequency;
    @ApiModelProperty(value = "缴费期间")
    @TableField("pay_year")
    private Integer payYear;
    @ApiModelProperty(value = "缴费区间单位")
    @TableField("pay_year_unit")
    private Integer payYearUnit;
    @ApiModelProperty(value = "保障区间")
    @TableField("safe_year")
    private Integer safeYear;
    @ApiModelProperty(value = "保障区间单位 1年2岁3月4周5天")
    @TableField("safe_year_unit")
    private Integer safeYearUnit;
    @ApiModelProperty(value = "告知书状态  1未签收2已签收")
    @TableField("notification_status")
    private Integer notificationStatus;
    @ApiModelProperty(value = "更新人")
    @TableField("update_user_json")
    private String updateUserJson;
    @ApiModelProperty(value = "录单人")
    @TableField("import_user_json")
    private String importUserJson;
    @ApiModelProperty(value = "保险公司工号")
    @TableField("sales_user_json")
    private String salesUserJson;
    @ApiModelProperty(value = "投保人")
    @TableField("holder_json")
    private String holderJson;
    @ApiModelProperty(value = "被保人")
    @TableField("insured_json")
    private String insuredJson;
    @ApiModelProperty(value = "主险信息")
    @TableField("product_json")
    private String productJson;
    @ApiModelProperty(value = "保费")
    @TableField("fee")
    private BigDecimal fee;
    @ApiModelProperty(value = "标准保费")
    @TableField("std_fee")
    private BigDecimal stdFee;
    @ApiModelProperty(value = "承保进度结果   ")
    @TableField("progress_result")
    private Integer progressResult;
    @ApiModelProperty(value = "修改人id")
    @TableField("update_user_id")
    private Long updateUserId;
    @ApiModelProperty(value = "险种id")
    @TableField("product_id")
    private Long productId;
    @ApiModelProperty(value = "险种类别id")
    @TableField("product_category_id")
    private Integer productCategoryId;
    @ApiModelProperty(value = "录单人id")
    @TableField("import_user_id")
    private Long importUserId;
    @ApiModelProperty(value = "出单人id")
    @TableField("sales_user_id")
    private Long salesUserId;
    @ApiModelProperty(value = "出单机构id")
    @TableField("sales_user_org_id")
    private Long salesUserOrgId;
    @ApiModelProperty(value = "出单人职级id")
    @TableField("sales_user_rank_id")
    private Long salesUserRankId;
    @ApiModelProperty(value = "是否保联合作方")
    @TableField("is_partner")
    private Integer isPartner;
    @ApiModelProperty(value = "服务代理人id")
    @TableField("service_user_id")
    private Long serviceUserId;
    @ApiModelProperty(value = "修改类型：1：正常保单  2：孤儿单修改")
    @TableField("change_type")
    private Integer changeType;
    @ApiModelProperty(value = "服务代理人类别：1代理人2.机构")
    @TableField("service_agent_type")
    private Integer serviceAgentType;
    @ApiModelProperty(value = "是否渠道保单")
    @TableField("is_channel")
    private Integer isChannel;
    @ApiModelProperty(value = "保险公司信息")
    @TableField("company_json")
    private String companyJson;
    @ApiModelProperty(value = "是否已过犹豫期")
    @TableField("is_after_hesitate")
    private Integer isAfterHesitate;
    @ApiModelProperty(value = "1app录单2后台录单")
    @TableField("client")
    private Integer client;
    @ApiModelProperty(value = "审批状态")
    @TableField("approval_status")
    private Integer approvalStatus;
    @ApiModelProperty(value = "审批备注")
    @TableField("approval_remark")
    private String approvalRemark;
    @ApiModelProperty(value = "是否管理型中介收费")
    @TableField("is_maintenance_fee")
    private Integer isMaintenanceFee;
    @ApiModelProperty(value = "来源保单id")
    @TableField("source_policy_id")
    private Long sourcePolicyId;
    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;
    @ApiModelProperty(value = "扩展字段")
    @TableField("extension")
    private String extension;
    @ApiModelProperty(value = "草稿步骤 步骤为100时草稿完成")
    @TableField("draft_step")
    private Integer draftStep;
    @ApiModelProperty(value = "总保额")
    @TableField("amount")
    private BigDecimal amount;
    @ApiModelProperty(value = "扣除公司佣金")
    @TableField("deduct_company_commission")
    private BigDecimal deductCompanyCommission;
    @ApiModelProperty(value = "扣除代理人佣金或者渠道佣金")
    @TableField("deduct_commission")
    private BigDecimal deductCommission;
    @ApiModelProperty(value = "扣除积分")
    @TableField("deduct_score")
    private BigDecimal deductScore;
    @ApiModelProperty(value = "是否已扣除佣金")
    @TableField("is_deduct")
    private Integer isDeduct;
    @ApiModelProperty(value = "出单组织id")
    @TableField("sales_team_id")
    private Long salesTeamId;
    @ApiModelProperty(value = "出单渠道id")
    @TableField("sales_channel_id")
    private Long salesChannelId;
    @ApiModelProperty(value = "渠道机构id")
    @TableField("sales_channel_org_id")
    private Long salesChannelOrgId;
    @ApiModelProperty(value = "1未支付2支付成功3支付失败")
    @TableField("pay_status")
    private Long payStatus;
    @ApiModelProperty(value = "第三方参数")
    @TableField("partner_param")
    private String partnerParam;


    public static final String ID = "id";

    public static final String PARTNER_ID = "partner_id";

    public static final String SOURCE_PARTNER_ID = "source_partner_id";

    public static final String ORDER_NO = "order_no";

    public static final String POLICY_NO = "policy_no";

    public static final String POLICY_SN = "policy_sn";

    public static final String COMPANY = "company";

    public static final String COMPANY_TYPE = "company_type";

    public static final String PROGRESS = "progress";

    public static final String PERIOD = "period";

    public static final String STATUS = "status";

    public static final String IS_LEGAL_BENEFICIARY = "is_legal_beneficiary";

    public static final String REMARK = "remark";

    public static final String DATE_START = "date_start";

    public static final String DATE_END = "date_end";

    public static final String DATE_DEDUCT = "date_deduct";

    public static final String DATE_ACCEPT = "date_accept";

    public static final String DATE_ADVANCE = "date_advance";

    public static final String IS_SALE_SELF = "is_sale_self";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String SERVICE_USER_JSON = "service_user_json";

    public static final String IMPORT_WAY = "import_way";

    public static final String SOURCE = "source";

    public static final String PAY_WAY = "pay_way";

    public static final String PAY_FREQUENCY = "pay_frequency";

    public static final String PAY_YEAR = "pay_year";

    public static final String PAY_YEAR_UNIT = "pay_year_unit";

    public static final String SAFE_YEAR = "safe_year";

    public static final String SAFE_YEAR_UNIT = "safe_year_unit";

    public static final String NOTIFICATION_STATUS = "notification_status";

    public static final String UPDATE_USER_JSON = "update_user_json";

    public static final String IMPORT_USER_JSON = "import_user_json";

    public static final String SALES_USER_JSON = "sales_user_json";

    public static final String HOLDER_JSON = "holder_json";

    public static final String INSURED_JSON = "insured_json";

    public static final String PRODUCT_JSON = "product_json";

    public static final String FEE = "fee";

    public static final String STD_FEE = "std_fee";

    public static final String PROGRESS_RESULT = "progress_result";

    public static final String UPDATE_USER_ID = "update_user_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_CATEGORY_ID = "product_category_id";

    public static final String IMPORT_USER_ID = "import_user_id";

    public static final String SALES_USER_ID = "sales_user_id";

    public static final String SALES_USER_ORG_ID = "sales_user_org_id";

    public static final String SALES_USER_RANK_ID = "sales_user_rank_id";

    public static final String IS_PARTNER = "is_partner";

    public static final String SERVICE_USER_ID = "service_user_id";

    public static final String CHANGE_TYPE = "change_type";

    public static final String SERVICE_AGENT_TYPE = "service_agent_type";

    public static final String IS_CHANNEL = "is_channel";

    public static final String COMPANY_JSON = "company_json";

    public static final String IS_AFTER_HESITATE = "is_after_hesitate";

    public static final String CLIENT = "client";

    public static final String APPROVAL_STATUS = "approval_status";

    public static final String APPROVAL_REMARK = "approval_remark";

    public static final String IS_MAINTENANCE_FEE = "is_maintenance_fee";

    public static final String SOURCE_POLICY_ID = "source_policy_id";

    public static final String IS_DELETED = "is_deleted";

    public static final String EXTENSION = "extension";

    public static final String DRAFT_STEP = "draft_step";

    public static final String AMOUNT = "amount";

    public static final String DEDUCT_COMPANY_COMMISSION = "deduct_company_commission";

    public static final String DEDUCT_COMMISSION = "deduct_commission";

    public static final String DEDUCT_SCORE = "deduct_score";

    public static final String IS_DEDUCT = "is_deduct";

    public static final String SALES_TEAM_ID = "sales_team_id";

    public static final String SALES_CHANNEL_ID = "sales_channel_id";

    public static final String SALES_CHANNEL_ORG_ID = "sales_channel_org_id";

    public static final String PAY_STATUS = "pay_status";

    public static final String PARTNER_PARAM = "partner_param";

}