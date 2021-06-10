
1：修改接口请求入参
2: 修改指定项目配置文件：对应的数据库
3：记得在对应的合作方库添加
CREATE TABLE "migration_log" (
  "id" bigint NOT NULL COMMENT '主键Id',
  "old_id" bigint unsigned DEFAULT NULL COMMENT '迁移老系统数据id',
  "source_partner_id" bigint unsigned NOT NULL DEFAULT '0' COMMENT '来源合作方id', 
  "status" tinyint unsigned NOT NULL DEFAULT '0' COMMENT '迁移状态1：迁移成功 2：迁移失败',
  "type" tinyint unsigned NOT NULL DEFAULT '0' COMMENT '迁移类型 1：保单',
  "created_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  "updated_at" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY ("id")
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据迁移日志表';
4：执行扩展字段长度和新增字段

--创建索引
ALTER TABLE `basic_bank` ADD UNIQUE (`code`);

--合作方
alter table life_policy  modify column policy_no varchar(50);
alter table life_policy  modify column remark varchar(255);
alter table life_policy_insured  modify column zip varchar(20);
alter table life_policy_beneficiary  modify column ratio decimal(10,2);
ALTER TABLE life_policy_holder ADD tax_type tinyint DEFAULT 0 COMMENT '税收类型' after `is_tax`;
ALTER TABLE life_policy_insured ADD tax_type tinyint DEFAULT 0 COMMENT '税收类型' after `is_tax`;

ALTER TABLE life_policy_progress ADD file_urls text  DEFAULT NULL  COMMENT '迁移影像资料上传地址' after `file`;
ALTER TABLE life_product_ratio_template ADD org_id bigint DEFAULT 0 COMMENT '所属机构,迁移数据使用字段，0：默认值' after `status`;
ALTER TABLE life_product_ratio_template_map ADD source_way tinyint DEFAULT 0 COMMENT '来源方式：0:源系统，1：迁移' after `template_id`;
ALTER TABLE life_product_ratio_template_detail ADD source_way tinyint DEFAULT 0 COMMENT '来源方式：0:源系统，1：迁移' after `remark`;

--保联
alter table partner_life_policy  modify column policy_no varchar(50);
alter table partner_life_policy  modify column remark varchar(255);
alter table partner_life_policy_insured  modify column zip varchar(20);
alter table partner_life_policy_beneficiary  modify column ratio decimal(10,2);
ALTER TABLE partner_life_policy_progress ADD file_urls JSON  NOT NULL  COMMENT '迁移影像资料上传地址' after `file`;
ALTER TABLE partner_life_policy_holder ADD tax_type tinyint DEFAULT 0 COMMENT '税收类型' after `is_tax`;
ALTER TABLE partner_life_policy_insured ADD tax_type tinyint DEFAULT 0 COMMENT '税收类型' after `is_tax`;
5：公用库数据处理common
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('CGB','广发银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('CQNS','农商重庆', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('SDBCL','深圳发展', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('SPB','深发/平安银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('ZGRCC','中国农村信用社', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('FJCIB','中国福建兴业银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('NCSB','农村商业银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('ZXSYB','中信实业银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('OTHER','其他银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('SZRCC','深圳农村信用合作社', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('NCRCC','农村信用社', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('NCRB','农村合作银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('ZGPB','中国人民银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('GDSDB','广东顺德农村商业银行股份有限公司', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('WEIXIN','微信号', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('DBSN2','星展银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('SCBCL','渣打银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('BOEAM','东亚银行有限公司', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('XHXBX','广东华兴银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('HQW','大新银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('LSKLV','乐山昆仑村镇银行', 0);
INSERT INTO `basic_bank`(`code`, `name`, `is_deleted`) VALUES ('HRCBC','黑龙江农村信用社', 0);







--产品迁移步骤
前提条件：
1、公司信息手动导入，老系统——>保联后台产品最新同步

步骤：
1、调用产品数据迁移接口
2、调用迁移失败的产品重试机制
3、调用产品标签生成接口
4、校验产品迁移接口


--折标迁移步骤
前提条件：

步骤：
1、调用折标模板迁移生成接口
2、折标数据迁移老系统——>合作方


--课程迁移步骤
前提条件：

步骤：
1、调用课程分类迁移
2、调用课程数据迁移
3、调用课程失败的重试迁移接口

--海报迁移
前提条件：

步骤：
1、调用海报迁移修改接口


--保单迁移步骤

前提条件：
1、确保组织机构，人员迁移，产品迁移完成

步骤：
1、修改数据库字段，新建表结构，新增表结构字段，新增银行数据
2、调用处理枚举接口
3、调用验证枚举是否处理验证
4、调用保单迁移老系统——>合作方接口
5、调用处理失败的保单重迁接口
6、调用验证保单迁移数据量接口
7、调用是否过犹豫期接口修改
8、调用根据生成客户数据接口
9、调用合作方——>保联后台接口
10、调用验证保单迁移数据量接口






ALTER TABLE `basic_bank` ADD UNIQUE (`code`);

