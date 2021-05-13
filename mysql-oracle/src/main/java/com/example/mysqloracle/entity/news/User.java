package com.example.mysqloracle.entity.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author
 * @Description:用户表
 * @since 2021-04-01
 */
@ApiModel(value = "用户表")
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @ApiModelProperty(value = "来源用户id，本身0")
    private Long sourceUserId;
    @ApiModelProperty(value = "姓名")
    private String realName;
    @ApiModelProperty(value = "组织机构id")
    private Long orgId;
    @ApiModelProperty(value = "机构渠道id")
    private Long orgChannelId;
    @ApiModelProperty(value = "部门团队id")
    private Long teamId;
    @ApiModelProperty(value = "性别：1、男2、女")
    private Integer sex;
    @ApiModelProperty(value = "角色类型 1、超级管理员2、渠道管理员3、普通")
    private Integer roleType;
    @ApiModelProperty(value = "类型：0、总公司1、内勤2、外勤3、渠道 (0和3用于存储超级管理员)")
    private Integer type;
    @ApiModelProperty(value = "是否属于渠道1、是0不是")
    private Integer isChannel;
    @ApiModelProperty(value = "用户账号")
    private String account;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "工号")
    private String jobNumber;
    @ApiModelProperty(value = "上级领导id")
    private Long parentId;
    @ApiModelProperty(value = "辅导人id")
    private Long teachId;
    @ApiModelProperty(value = "推荐人id")
    private Long recommendId;
    @ApiModelProperty(value = "人员状态：0、游客1、在职2、离职3、停职4、草稿5、停用")
    private Integer status;
    @ApiModelProperty(value = "用户(账号)状态：0(存储草稿)1、启用(在职)2、离职5、停用")
    private Integer accountStatus;
    @ApiModelProperty(value = "入职时间")
    private LocalDate joinTime;
    @ApiModelProperty(value = "服务开始日")
    private LocalDate serviceTime;
    @ApiModelProperty(value = "职级：1、预备合伙人2、高级合伙人3、合伙人4、区域合伙人  关联systen_rank表主键id")
    private Long jobRank;
    @ApiModelProperty(value = "证件类型：1、身份证2、护照3、出生证4、户口本5、台湾居民往来大陆通行证6、香港同胞回乡证（通行证）7、澳门同胞回乡证（通行证）8、临时身份证9、港澳通行证10、台湾通行证11、驾驶证12、军人证13、其他证件")
    private Integer certificateType;
    @ApiModelProperty(value = "证件号码")
    private String certificateNumber;
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;
    @ApiModelProperty(value = "民族：1、汉族2、满族")
    private Integer nation;
    @ApiModelProperty(value = "籍贯省code")
    private Integer nativePlaceProvinceCode;
    @ApiModelProperty(value = "籍贯市code")
    private Integer nativePlaceCityCode;
    @ApiModelProperty(value = "政治面貌：1、共产党员2、团员3、群众4、其他")
    private Integer political;
    @ApiModelProperty(value = "薪资类型")
    private Integer salaryType;
    @ApiModelProperty(value = "是否管理岗")
    private Integer isManage;
    @ApiModelProperty(value = "是否超级管理员：1、是0、不是")
    private Integer isAdmin;
    @ApiModelProperty(value = "是否完成培训")
    private Integer trainStatus;
    @ApiModelProperty(value = "婚姻状况：1、已婚2、未婚3、离婚4、丧偶5、其他")
    private Integer marryStatus;
    @ApiModelProperty(value = "学历：1、小学2、初中3、高中4、中专5、大专6、本科7、研究生8、硕士9、博士")
    private Integer education;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "打卡方式：1、每日一课2、外勤打卡")
    private String punchType;
    @ApiModelProperty(value = "展业城市省code")
    private Integer promoteProvinceCode;
    @ApiModelProperty(value = "展业城市市code")
    private Integer promoteCityCode;
    @ApiModelProperty(value = "职业资格证号码")
    private String professionalCertificateNumber;
    @ApiModelProperty(value = "是否发送邀请：1、已发送 0、未发送  用于注册游客")
    private Integer isSend;
    @ApiModelProperty(value = "输入方式，1=后台添加，2=APP注册")
    private Integer importWay;
    @ApiModelProperty(value = "是否删除：1、删除 0、未删除")
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}