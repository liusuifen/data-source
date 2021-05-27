package com.example.mysqloracle.dao.eums;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.eums.BasicEnum;
import com.example.mysqloracle.entity.eums.BasicEnumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 枚举管理 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-08
 */
@Mapper
public interface BasicEnumMapper extends BaseMapper<BasicEnum> {


    @Update("update basic_enum set new_value=#{newValue} where id=#{id}")
    int updateByIds(@Param("newValue") String newVale,@Param("id") String id);

    /**
     * 性别 1男2女3不详
     * @return
     */
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '男' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '女' THEN\n" +
            "\t\t2 \n" +
            "\t\tELSE 3\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.holderGender \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tholder_gender AS holderGender \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_gender UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_gender AS holderGender \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_gender \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getByGender();

    /**
     * 证件类型：1身份证2护照3出生证4户口本5其他6军人证7驾照8学生证9工作证10士兵证11回乡证12临时身份证13警官证14台胞证15港澳通行证16港澳居民来往内地通行证17港澳居住证18台湾通行证19无证件
     * @return
     */
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '身份证' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '居民身份证' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '护照' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '出生证' THEN\n" +
            "\t\t3 \n" +
            "\t\tWHEN '户口本' THEN\n" +
            "\t\t4 \n" +
            "\t\tWHEN '居民户口薄' THEN\n" +
            "\t\t4 \n" +
            "\t\tWHEN '户口簿' THEN\n" +
            "\t\t4 \n" +
            "\t\tWHEN '其他' THEN\n" +
            "\t\t5 \n" +
            "\t\tWHEN '军人证' THEN\n" +
            "\t\t6 \n" +
            "\t\tWHEN '驾驶证' THEN\n" +
            "\t\t7 \n" +
            "\t\tWHEN '学生证' THEN\n" +
            "\t\t8 \n" +
            "\t\tWHEN '工作证' THEN\n" +
            "\t\t9 \n" +
            "\t\tWHEN '士兵证' THEN\n" +
            "\t\t10 \n" +
            "\t\tWHEN '回乡证' THEN\n" +
            "\t\t11 \n" +
            "\t\tWHEN '临时身份证' THEN\n" +
            "\t\t12 \n" +
            "\t\tWHEN '武警身份证' THEN\n" +
            "\t\t13 \n" +
            "\t\tWHEN '台胞证' THEN\n" +
            "\t\t14 \n" +
            "\t\tWHEN '港澳台居民往来内地通行证' THEN\n" +
            "\t\t15 \n" +
            "\t\tWHEN '港澳居民来往内地通行证' THEN\n" +
            "\t\t16 \n" +
            "\t\tWHEN '港澳居住证' THEN\n" +
            "\t\t17 \n" +
            "\t\tWHEN '台湾通行证' THEN\n" +
            "\t\t18 \n" +
            "\t\tWHEN '无证件' THEN\n" +
            "\t\t19 ELSE 5 \n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.idType \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tholder_ID_type AS idType \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_ID_type UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tID_type AS idType \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tID_type UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_ID_type AS idType \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_ID_type \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getByIdType();

    /**
     * 婚姻状态枚举
     * @return
     */
    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '已婚' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '已婚（有配偶）' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '未婚' THEN\n" +
            "\t\t2\n" +
            "\t\tWHEN '未婚（无配偶）' THEN\n" +
            "\t\t2\n" +
            "\t\tWHEN '离婚' THEN\n" +
            "\t\t3\n" +
            "\t\tWHEN '丧偶' THEN\n" +
            "\t\t4\n" +
            "\t\tWHEN '初婚' THEN\n" +
            "\t\t6\n" +
            "\t\tWHEN '再婚' THEN\n" +
            "\t\t7\n" +
            "\t\tWHEN '复婚' THEN\n" +
            "\t\t8\n" +
            "\t\tELSE 5 \n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.holderMarriage \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tholder_marriage AS holderMarriage \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_marriage UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_marriage AS holderMarriage \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_marriage \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getholderMarriage();


    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '工商银行' THEN\n" +
            "\t\t'ICBC' \n" +
            "\t\tWHEN '农业银行' THEN\n" +
            "\t\t'ABC' \n" +
            "\t\tWHEN '中国银行' THEN\n" +
            "\t\t'BOC'\n" +
            "\t\tWHEN '建设银行' THEN\n" +
            "\t\t'CCB'\n" +
            "\t\tWHEN '交通银行' THEN\n" +
            "\t\t'COMM'\n" +
            "\t\tWHEN '中信银行' THEN \n" +
            "\t\t'CITIC'\n" +
            "\t\tWHEN '华夏银行' THEN\n" +
            "\t\t'HXBANK'\n" +
            "\t\tWHEN '民生银行' THEN\n" +
            "\t\t'CMBC'\n" +
            "\t\tWHEN '广发银行' THEN\n" +
            "\t\t'CGB'\n" +
            "\t\tWHEN '平安银行' THEN\n" +
            "\t\t'SPABANK'\n" +
            "\t\tWHEN '招商银行' THEN\n" +
            "\t\t'CMB'\n" +
            "\t\tWHEN '兴业银行' THEN\n" +
            "\t\t'CIB'\n" +
            "\t\tWHEN '浦发银行' THEN\n" +
            "\t\t'SPDB'\n" +
            "\t\tWHEN '农商重庆' THEN\n" +
            "\t\t'CQNS'\n" +
            "\t\tWHEN '深圳发展' THEN\n" +
            "\t\t'SDBCL'\n" +
            "\t\tWHEN '中国工商银行' THEN\n" +
            "\t\t'ICBC'\n" +
            "\t\tWHEN '中国农业银行' THEN\n" +
            "\t\t'ABC'\n" +
            "\t\tWHEN '中国建设银行' THEN\n" +
            "\t\t'CCB'\n" +
            "\t\tWHEN '上海浦东发展银行' THEN\n" +
            "\t\t'SPDB'\n" +
            "\t\tWHEN '东莞农村商业银行' THEN\n" +
            "\t\t'DRCBCL'\n" +
            "\t\tWHEN '东莞银行' THEN\n" +
            "\t\t'BOD'\n" +
            "\t\tWHEN '中信实业银行' THEN\n" +
            "\t\t'ZXSYB'\n" +
            "\t\tWHEN '中信银行' THEN\n" +
            "\t\t'CITIC'\n" +
            "\t\tWHEN '中信银行（总对总）' THEN\n" +
            "\t\t'CITIC'\n" +
            "\t\tWHEN '中国上海浦东发展银行' THEN\n" +
            "\t\t'SPDB'\n" +
            "\t\tWHEN '中国交通银行' THEN\n" +
            "\t\t'COMM'\n" +
            "\t\tWHEN '中国人民银行' THEN\n" +
            "\t\t'ZGPB'\n" +
            "\t\tWHEN '中国光大银行' THEN\n" +
            "\t\t'CEB'\n" +
            "\t\tWHEN '中国农村信用社' THEN\n" +
            "\t\t'ZGRCC'\n" +
            "\t\tWHEN '中国华夏银行' THEN\n" +
            "\t\t'HXBANK'\n" +
            "\t\tWHEN '中国广东发展银行' THEN\n" +
            "\t\t'CGB'\n" +
            "\t\tWHEN '中国建设银行（转帐／现金）' THEN\n" +
            "\t\t'CCB'\n" +
            "\t\tWHEN '中国招商银行' THEN\n" +
            "\t\t'CMB'\n" +
            "\t\tWHEN '中国招商银行（银行代收）' THEN\n" +
            "\t\t'CMB'\n" +
            "\t\tWHEN '中国民生银行' THEN\n" +
            "\t\t'CMBC'\n" +
            "\t\tWHEN '中国福建兴业银行' THEN\n" +
            "\t\t'FJCIB'\n" +
            "\t\tWHEN '中国邮政储蓄' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tWHEN '中国邮政储蓄银行' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tWHEN '中国邮政储蓄银行有限责任公司' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tWHEN '交通银行（银联不支持 ）' THEN\n" +
            "\t\t'COMM'\n" +
            "\t\tWHEN '光大银行' THEN\n" +
            "\t\t'CEB'\n" +
            "\t\tWHEN '其他银行' THEN\n" +
            "\t\t'OTHER'\n" +
            "\t\tWHEN '农村信用社' THEN\n" +
            "\t\t'NCRCC'\n" +
            "\t\tWHEN '农村合作银行' THEN\n" +
            "\t\t'NCRB'\n" +
            "\t\tWHEN '农村商业银行' THEN\n" +
            "\t\t'NCSB'\n" +
            "\t\tWHEN '北京银行' THEN\n" +
            "\t\t'BJBANK'\n" +
            "\t\tWHEN '宁波银行' THEN\n" +
            "\t\t'NBBANK'\n" +
            "\t\tWHEN '广东发展银行' THEN\n" +
            "\t\t'GDB'\n" +
            "\t\tWHEN '广东顺德农村商业银行股份有限公司' THEN\n" +
            "\t\t'GDSDB'\n" +
            "\t\tWHEN '微信号' THEN\n" +
            "\t\t'WEIXIN'\n" +
            "\t\tWHEN '招商银行（信用卡）' THEN\n" +
            "\t\t'CMB'\n" +
            "\t\tWHEN '未知' THEN\n" +
            "\t\t'OTHER'\n" +
            "\t\tWHEN '浙商银行' THEN\n" +
            "\t\t'CZBANK'\n" +
            "\t\tWHEN '深发/平安银行' THEN\n" +
            "\t\t'SPB'\n" +
            "\t\tWHEN '深圳农村信用合作社' THEN\n" +
            "\t\t'SZRCC'\n" +
            "\t\tWHEN '深圳发展' THEN\n" +
            "\t\t'SDBCL'\n" +
            "\t\tWHEN '深圳发展银行' THEN\n" +
            "\t\t'SDBCL'\n" +
            "\t\tWHEN '邮储银行' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tWHEN '邮政储蓄银行' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tWHEN '邮政银行' THEN\n" +
            "\t\t'PSBC'\n" +
            "\t\tELSE 'OTHER'\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.bankName\n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tbank_name AS bankName \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tbank_name\n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getBank();


    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '中国' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '中国台湾' THEN\n" +
            "\t\t2 \n" +
            "\t\tELSE \n" +
            "\t\t1\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.nation\n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tnation  \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tnation UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_nation AS nation \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_nation \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getNation();


    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '本人' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '配偶' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '丈夫' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '夫妻' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '妻子' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '子女' THEN\n" +
            "\t\t3\n" +
            "\t\tWHEN '儿子' THEN\n" +
            "\t\t3\n" +
            "\t\tWHEN '女儿' THEN\n" +
            "\t\t3\n" +
            "\t\tWHEN '父母' THEN\n" +
            "\t\t4\n" +
            "\t\tWHEN '父亲' THEN\n" +
            "\t\t4\n" +
            "\t\tWHEN '母亲' THEN\n" +
            "\t\t4\n" +
            "\t\tWHEN '其他' THEN\n" +
            "\t\t5 \n" +
            "\t\tWHEN '其它' THEN\n" +
            "\t\t5 \n" +
            "\t\tWHEN '其他亲属' THEN\n" +
            "\t\t5 \n" +
            "\t\tWHEN '祖孙' THEN\n" +
            "\t\t6 \n" +
            "\t\tELSE 100 \n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.beneficiaryIsInsured \n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tbeneficiary_is_insured AS beneficiaryIsInsured \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tbeneficiary_is_insured  UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\trel_holder_insured AS beneficiaryIsInsured \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\trel_holder_insured  UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\trel_insured_holder AS beneficiaryIsInsured \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\trel_insured_holder\n" +
            "\t\t) a \n" +
            "\t);\n")
    List<BasicEnumVo> getBeneficiaryIsInsured();

    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '是' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '有社保' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '否' THEN\n" +
            "\t\t0\n" +
            "\t\tWHEN '无社保' THEN\n" +
            "\t\t0\n" +
            "\t\tWHEN '女' THEN\n" +
            "\t\t2\n" +
            "\t\tELSE \n" +
            "\t\t0\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.ssid\n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tholder_has_SSID   as ssid\n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_has_SSID UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\thas_insured_SSID AS ssid \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\thas_insured_SSID \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getHasSSId();


    @Select("\t\t\tSELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '工薪' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '个体' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '个体或农业收入' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '私营' THEN\n" +
            "\t\t3\n" +
            "\t\tWHEN '房屋出租' THEN\n" +
            "\t\t4\n" +
            "\t\tWHEN '证券投资' THEN\n" +
            "\t\t5\n" +
            "\t\tWHEN '银行利息' THEN\n" +
            "\t\t6\n" +
            "\t\tWHEN '其他' THEN\n" +
            "\t\t7\n" +
            "\t\tELSE \n" +
            "\t\t7\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.insuredSalaryFrom\n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_salary_from   as insuredSalaryFrom\n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_salary_from  UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\tholder_salary_from AS beneficiaryIsInsured \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_salary_from \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getSourceFrom();


    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '仅为中国税收居民' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '仅是 中国税收居民' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '仅为非居民' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '仅为非中国税收居民' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '既是中国税收居民又是其他国家（地区）税收' THEN\n" +
            "\t\t3 \n" +
            "\t\tWHEN '即是中国又是其他地区税收居民' THEN\n" +
            "\t\t3 \n" +
            "\t\tWHEN '既是中国税收居民又是其他国家（地区）税收居民' THEN\n" +
            "\t\t3 \n" +
            "\t\tELSE 1 \n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\t\t'LAH0001',\n" +
            "\t\t'LAH0002',\n" +
            "\t\t'LAH0003',\n" +
            "\t\t'LAH0004',\n" +
            "\t\t'LAH0005',\n" +
            "\t\t'LAH0006',\n" +
            "\t\t'LAH0007',\n" +
            "\t\t'LAH0008',\n" +
            "\t\t'LAH0009',\n" +
            "\t\t'LAH000A',\n" +
            "\t\t'LAH000B',\n" +
            "\t\t'LAH000C',\n" +
            "\t\t'LAH000D',\n" +
            "\t\t'LAH000E',\n" +
            "\t\t'LAH000F',\n" +
            "\t\t'LAH000G',\n" +
            "\t\t'LAH000H',\n" +
            "\t'LAH000I' \n" +
            "\t);")
    List<BasicEnumVo> getTaxType();


    @Select("SELECT\n" +
            "\tid,\n" +
            "\tmodule,\n" +
            "\tcategory,\n" +
            "\tcompany,\n" +
            "\tenum_code AS enumCode,\n" +
            "\tenum_name AS enumName,\n" +
            "CASE\n" +
            "\t\tenum_name \n" +
            "\t\tWHEN '生存受益人' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '生存' THEN\n" +
            "\t\t1\n" +
            "\t\tWHEN '生存金' THEN\n" +
            "\t\t1 \n" +
            "\t\tWHEN '身故' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '身故受益人' THEN\n" +
            "\t\t2 \n" +
            "\t\tWHEN '除身故保险金以外的其他保险金受益人' THEN\n" +
            "\t\t2 \n" +
            "\t\tELSE \n" +
            "\t\t1\n" +
            "\tEND newValue \n" +
            "FROM\n" +
            "\tun_basic_enum \n" +
            "WHERE\n" +
            "\tid IN (\n" +
            "\tSELECT DISTINCT\n" +
            "\t\ta.type\n" +
            "\tFROM\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\ttype   as type\n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\ttype \n" +
            "\t\t) a \n" +
            "\t);")
    List<BasicEnumVo> getEarningsType();


    @Select("select new_value from basic_enum where id=#{id}")
    String getNewValueById(@Param("id") String id);


    @Select("select a.* from \n" +
            "\t (SELECT \n" +
            "\t\t\t\tholder_gender AS code \n" +
            "\t\tFROM \n" +
            "\t\t\t\tun_life_ins_main\n" +
            "\t\tGROUP BY \n" +
            "\t\t\t\tholder_gender UNION ALL\n" +
            "\t\tSELECT \n" +
            "\t\t\t\tinsured_gender AS code \n" +
            "\t\tFROM \n" +
            "\t\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\t\n" +
            "\t\t\t\tinsured_gender UNION ALL\t\t\t\n" +
            "\t\tSELECT\n" +
            "\t\t\tholder_ID_type AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_ID_type UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tID_type AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tID_type UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_ID_type AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_ID_type UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\tholder_marriage AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_marriage UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_marriage AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_marriage UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\tbank_name AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tbank_name UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\tnation  as code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tnation UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_nation AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_nation UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tbeneficiary_is_insured AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tbeneficiary_is_insured  UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\trel_holder_insured AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\trel_holder_insured  UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\trel_insured_holder AS code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\trel_insured_holder UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\ttype   as code \n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_beneficiary \n" +
            "\t\tGROUP BY\n" +
            "\t\t\ttype UNION ALL\n" +
            "\t\t\tSELECT\n" +
            "\t\t\tholder_has_SSID   as code\n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_main \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tholder_has_SSID  UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\thas_insured_SSID AS code\n" +
            "\t\tFROM\n" +
            "\t\tun_life_ins_insured\n" +
            "\t\tGROUP BY\n" +
            "\t\thas_insured_SSID UNION ALL\n" +
            "\t\tSELECT\n" +
            "\t\t\tinsured_salary_from   as code\n" +
            "\t\tFROM\n" +
            "\t\t\tun_life_ins_insured \n" +
            "\t\tGROUP BY\n" +
            "\t\t\tinsured_salary_from UNION ALL\n" +
            "\t\tSELECT \n" +
            "\t\tholder_salary_from AS code\n" +
            "\t\tFROM\n" +
            "\t\tun_life_ins_main\n" +
            "\t\tGROUP BY\n" +
            "\t\tholder_salary_from) a where a.code!=\"\"")
    List<String> checkEnum();






}
