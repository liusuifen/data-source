package com.example.mysqloracle.service.impl.eums;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.eums.BasicEnumMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.eums.BasicEnum;
import com.example.mysqloracle.entity.eums.BasicEnumVo;
import com.example.mysqloracle.service.eums.BasicEnumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 枚举管理 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-08
 */
@Service
public class BasicEnumServiceImpl extends ServiceImpl<BasicEnumMapper, BasicEnum> implements BasicEnumService {

    @Autowired
    private BasicEnumMapper basicEnumMapper;

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getGender() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getByGender();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("性别类型枚举同步成功");
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getIdType() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getByIdType();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("身份证类型枚举同步成功");
    }


    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getHolderMarriage() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getholderMarriage();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("婚姻类型枚举同步成功");
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getBank() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getBank();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("银行类型枚举同步成功");
    }


    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getNation() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getNation();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("国籍枚举同步成功");
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getBeneficiaryIsInsured() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getBeneficiaryIsInsured();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("与保险人关系枚举同步成功");
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getHasSSid() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getHasSSId();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("是否有社保枚举同步成功");
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public CommonResult getSourceFrom() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getSourceFrom();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("收入来源枚举同步成功");
    }

    @Override
    public CommonResult getTaxType() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getTaxType();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("税收类型枚举同步成功");
    }

    @Override
    public CommonResult getEarningsType() {
        Map<String, String> map = new HashMap<>();
        List<BasicEnumVo> byGender = basicEnumMapper.getEarningsType();
        for (BasicEnumVo basicEnumVo : byGender) {
            map.put(basicEnumVo.getId(),basicEnumVo.getNewValue());
        }
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            basicEnumMapper.updateByIds(entry.getValue(),entry.getKey());
        }
        return new CommonResult("收益类型枚举同步成功");
    }


    @Override
    public String getNewEnum(String id) {
        String newValue="0";
        if(id!=null && !"".equals(id)){
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
            newValue = basicEnumMapper.getNewValueById(id);
        }
        return newValue;
    }
}
