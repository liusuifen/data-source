package com.example.mysqloracle.entity;

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
 * @Description:寿险保单-投保单主表
 * @since 2021-04-29
 */
@ApiModel(value = "寿险保单-投保单主表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("un_life_ins_main")
public class UnLifeInsMain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寿险保单编号,后续对应字段名为policy_id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "销售主体对应表un_basic_channel中的id")
    @TableField("channel_id")
    private Integer channelId;
    @ApiModelProperty(value = "预投保订单号")
    @TableField("order_no")
    private String orderNo;
    @ApiModelProperty(value = "投保单号/投保书号")
    @TableField("policy_no")
    private String policyNo;
    @ApiModelProperty(value = "保单正式编号")
    @TableField("policy_sn")
    private String policySn;
    @ApiModelProperty(value = "平安智能核保核保号")
    @TableField("uw_medical_id")
    private String uwMedicalId;
    @ApiModelProperty(value = "保单所属保险公司ID（与un_supplier.id关联）")
    @TableField("supplier_id")
    private Integer supplierId;
    @ApiModelProperty(value = "销售渠道")
    @TableField("sales_channel")
    private Integer salesChannel;
    @ApiModelProperty(value = "营业单位 / 分公司 / 经代公司")
    @TableField("sales_dept")
    private Integer salesDept;
    @ApiModelProperty(value = "投保地区")
    @TableField("ins_area")
    private String insArea;
    @ApiModelProperty(value = "保险代理人ID")
    @TableField("user_id")
    private Integer userId;
    @ApiModelProperty(value = "出单职级")
    @TableField("rank_id")
    private String rankId;
    @ApiModelProperty(value = "投保人姓名")
    @TableField("holder_name")
    private String holderName;
    @ApiModelProperty(value = "投保人性别：女,男.")
    @TableField("holder_gender")
    private String holderGender;
    @ApiModelProperty(value = "投保人出生日期：格式：1999-01-01")
    @TableField("holder_birthday")
    private String holderBirthday;
    @ApiModelProperty(value = "投保人国籍")
    @TableField("holder_nation")
    private String holderNation;
    @ApiModelProperty(value = "投保人证件类型")
    @TableField("holder_ID_type")
    private String holderIdType;
    @ApiModelProperty(value = "投保人证件号码")
    @TableField("holder_ID_no")
    private String holderIdNo;
    @ApiModelProperty(value = "投保人证件有效截止日期：默认长期有效：9999-12-31")
    @TableField("holder_ID_expire_end")
    private String holderIdExpireEnd;
    @ApiModelProperty(value = "投保人婚姻状态")
    @TableField("holder_marriage")
    private String holderMarriage;
    @ApiModelProperty(value = "投保人身高(CM)")
    @TableField("holder_height")
    private BigDecimal holderHeight;
    @ApiModelProperty(value = "投保人体重(KG)")
    @TableField("holder_weight")
    private BigDecimal holderWeight;
    @ApiModelProperty(value = "投保人家庭年收入：单位万元。")
    @TableField("holder_salary")
    private BigDecimal holderSalary;
    @ApiModelProperty(value = "投保人职业代码")
    @TableField("holder_job_code")
    private String holderJobCode;
    @ApiModelProperty(value = "投保人工作单位")
    @TableField("holder_company")
    private String holderCompany;
    @ApiModelProperty(value = "投保人公司职务")
    @TableField("holder_position")
    private String holderPosition;
    @ApiModelProperty(value = "投保人工作单位地址")
    @TableField("holder_company_address")
    private String holderCompanyAddress;
    @ApiModelProperty(value = "投保人平均年收入：单位万元。")
    @TableField("holder_salary_avg")
    private BigDecimal holderSalaryAvg;
    @ApiModelProperty(value = "投保人收入来源：工资,奖金 / 分红,遗产,房租,股票 / 债券 / 工薪 / 私营 / 证券投资 / 银行利息 / ,其它(详细).")
    @TableField("holder_salary_from")
    private String holderSalaryFrom;
    @ApiModelProperty(value = "投保人身份证或居住所在地:省")
    @TableField("holder_home_province")
    private String holderHomeProvince;
    @ApiModelProperty(value = "投保人身份证或居住所在地:市")
    @TableField("holder_home_city")
    private String holderHomeCity;
    @ApiModelProperty(value = "投保人身份证或居住所在地:区")
    @TableField("holder_home_district")
    private String holderHomeDistrict;
    @ApiModelProperty(value = "投保人身份证或居住所在地详细住址")
    @TableField("holder_home_address")
    private String holderHomeAddress;
    @ApiModelProperty(value = "投保人身份证或居住所在地邮编")
    @TableField("holder_home_zip")
    private String holderHomeZip;
    @ApiModelProperty(value = "投保人通讯地址:省")
    @TableField("holder_contact_province")
    private String holderContactProvince;
    @ApiModelProperty(value = "投保人通讯地址:市")
    @TableField("holder_contact_city")
    private String holderContactCity;
    @ApiModelProperty(value = "投保人通讯地址:区")
    @TableField("holder_contact_district")
    private String holderContactDistrict;
    @ApiModelProperty(value = "投保人通讯地详细地址")
    @TableField("holder_contact_address")
    private String holderContactAddress;
    @ApiModelProperty(value = "投保人通讯地邮编")
    @TableField("holder_contact_zip")
    private String holderContactZip;
    @ApiModelProperty(value = "投保人固话")
    @TableField("holder_phone")
    private String holderPhone;
    @ApiModelProperty(value = "投保人本人手机")
    @TableField("holder_mobile")
    private String holderMobile;
    @ApiModelProperty(value = "投保人单位固定电话")
    @TableField("holder_office_phone")
    private String holderOfficePhone;
    @ApiModelProperty(value = "投保人是否接受手机短信服务：否,是。")
    @TableField("holder_is_sms")
    private String holderIsSms;
    @ApiModelProperty(value = "投保人电子邮箱")
    @TableField("holder_email")
    private String holderEmail;
    @ApiModelProperty(value = "是否有社保")
    @TableField("holder_has_SSID")
    private String holderHasSsid;
    @ApiModelProperty(value = "学历")
    @TableField("holder_education")
    private String holderEducation;
    @ApiModelProperty(value = "开户地")
    @TableField("bank_area")
    private String bankArea;
    @ApiModelProperty(value = "开户行")
    @TableField("bank_name")
    private String bankName;
    @ApiModelProperty(value = "卡折类型")
    @TableField("bank_type")
    private String bankType;
    @ApiModelProperty(value = "银行卡号")
    @TableField("bank_no")
    private String bankNo;
    @ApiModelProperty(value = "持卡人")
    @TableField("bank_holder")
    private String bankHolder;
    @ApiModelProperty(value = "生效日")
    @TableField("val_date")
    private String valDate;
    @ApiModelProperty(value = "宽限期截止日")
    @TableField("grace_end_date")
    private String graceEndDate;
    @ApiModelProperty(value = "保费合计")
    @TableField("total")
    private BigDecimal total;
    @ApiModelProperty(value = "最新进度：basecode  CJ 类")
    @TableField("progress")
    private Integer progress;
    @ApiModelProperty(value = "进度确认时间")
    @TableField("progress_time")
    private Integer progressTime;
    @ApiModelProperty(value = "第几期保单")
    @TableField("is_first")
    private Integer isFirst;
    @ApiModelProperty(value = "是否法定受益人，1是0否")
    @TableField("is_legal_benefic")
    private Integer isLegalBenefic;
    @ApiModelProperty(value = "扩展字段信息（JSON格式）")
    @TableField("extend_items")
    private String extendItems;
    @ApiModelProperty(value = "json格式图片路径")
    @TableField("img_json")
    private String imgJson;
    @ApiModelProperty(value = "保单状态：0'承保中',1'审核',2 '撤单',3'退保',4 '拒保',5 '人工核保',6'犹退', 7 '有效',8'失效',9'终止',10'承保'")
    @TableField("state")
    private Integer state;
    @ApiModelProperty(value = "0未支付，1支付完成，2支付中，3支付失败")
    @TableField("pay_status")
    private Integer payStatus;
    @ApiModelProperty(value = "1:临时保单,0:非临时保单")
    @TableField("is_temp")
    private Integer isTemp;
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty(value = "是否为做到寿险中的财险：  0 否  1 是")
    @TableField("is_property")
    private Integer isProperty;
    @ApiModelProperty(value = "创建人")
    @TableField("create_by")
    private Integer createBy;
    @ApiModelProperty(value = "创建时间戳")
    @TableField("create_time")
    private Integer createTime;
    @ApiModelProperty(value = "修改人")
    @TableField("modify_by")
    private Integer modifyBy;
    @ApiModelProperty(value = "修改时间戳")
    @TableField("modify_time")
    private Integer modifyTime;
    @ApiModelProperty(value = "老ID")
    @TableField("war_id")
    private Integer warId;
    @TableField("delete_time")
    private Integer deleteTime;


    public static final String ID = "id";

    public static final String CHANNEL_ID = "channel_id";

    public static final String ORDER_NO = "order_no";

    public static final String POLICY_NO = "policy_no";

    public static final String POLICY_SN = "policy_sn";

    public static final String UW_MEDICAL_ID = "uw_medical_id";

    public static final String SUPPLIER_ID = "supplier_id";

    public static final String SALES_CHANNEL = "sales_channel";

    public static final String SALES_DEPT = "sales_dept";

    public static final String INS_AREA = "ins_area";

    public static final String USER_ID = "user_id";

    public static final String RANK_ID = "rank_id";

    public static final String HOLDER_NAME = "holder_name";

    public static final String HOLDER_GENDER = "holder_gender";

    public static final String HOLDER_BIRTHDAY = "holder_birthday";

    public static final String HOLDER_NATION = "holder_nation";

    public static final String HOLDER_ID_TYPE = "holder_ID_type";

    public static final String HOLDER_ID_NO = "holder_ID_no";

    public static final String HOLDER_ID_EXPIRE_END = "holder_ID_expire_end";

    public static final String HOLDER_MARRIAGE = "holder_marriage";

    public static final String HOLDER_HEIGHT = "holder_height";

    public static final String HOLDER_WEIGHT = "holder_weight";

    public static final String HOLDER_SALARY = "holder_salary";

    public static final String HOLDER_JOB_CODE = "holder_job_code";

    public static final String HOLDER_COMPANY = "holder_company";

    public static final String HOLDER_POSITION = "holder_position";

    public static final String HOLDER_COMPANY_ADDRESS = "holder_company_address";

    public static final String HOLDER_SALARY_AVG = "holder_salary_avg";

    public static final String HOLDER_SALARY_FROM = "holder_salary_from";

    public static final String HOLDER_HOME_PROVINCE = "holder_home_province";

    public static final String HOLDER_HOME_CITY = "holder_home_city";

    public static final String HOLDER_HOME_DISTRICT = "holder_home_district";

    public static final String HOLDER_HOME_ADDRESS = "holder_home_address";

    public static final String HOLDER_HOME_ZIP = "holder_home_zip";

    public static final String HOLDER_CONTACT_PROVINCE = "holder_contact_province";

    public static final String HOLDER_CONTACT_CITY = "holder_contact_city";

    public static final String HOLDER_CONTACT_DISTRICT = "holder_contact_district";

    public static final String HOLDER_CONTACT_ADDRESS = "holder_contact_address";

    public static final String HOLDER_CONTACT_ZIP = "holder_contact_zip";

    public static final String HOLDER_PHONE = "holder_phone";

    public static final String HOLDER_MOBILE = "holder_mobile";

    public static final String HOLDER_OFFICE_PHONE = "holder_office_phone";

    public static final String HOLDER_IS_SMS = "holder_is_sms";

    public static final String HOLDER_EMAIL = "holder_email";

    public static final String HOLDER_HAS_SSID = "holder_has_SSID";

    public static final String HOLDER_EDUCATION = "holder_education";

    public static final String BANK_AREA = "bank_area";

    public static final String BANK_NAME = "bank_name";

    public static final String BANK_TYPE = "bank_type";

    public static final String BANK_NO = "bank_no";

    public static final String BANK_HOLDER = "bank_holder";

    public static final String VAL_DATE = "val_date";

    public static final String GRACE_END_DATE = "grace_end_date";

    public static final String TOTAL = "total";

    public static final String PROGRESS = "progress";

    public static final String PROGRESS_TIME = "progress_time";

    public static final String IS_FIRST = "is_first";

    public static final String IS_LEGAL_BENEFIC = "is_legal_benefic";

    public static final String EXTEND_ITEMS = "extend_items";

    public static final String IMG_JSON = "img_json";

    public static final String STATE = "state";

    public static final String PAY_STATUS = "pay_status";

    public static final String IS_TEMP = "is_temp";

    public static final String REMARK = "remark";

    public static final String IS_PROPERTY = "is_property";

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_BY = "modify_by";

    public static final String MODIFY_TIME = "modify_time";

    public static final String WAR_ID = "war_id";

    public static final String DELETE_TIME = "delete_time";

}