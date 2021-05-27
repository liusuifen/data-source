package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.news.admin.LifeProductsMapper;
import com.example.mysqloracle.dao.old.*;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.param.Param;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 前端控制器
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/checkMove")
@Slf4j
public class CheckController {

    @Autowired
    private UnLifeInsMainMapper unLifeInsMainMapper;

    @Autowired
    private UnLifeInsInsuredMapper  unLifeInsInsuredMapper;

    @Autowired
    private UnLifeInsBeneficiaryMapper unLifeInsBeneficiaryMapper;

    @Autowired
    private UnLifeInsContentMapper unLifeInsContentMapper;

    @Autowired
    private UnLifeInsStateMapper unLifeInsStateMapper;

    @Autowired
    private LifePolicyMapper lifePolicyMapper;

    @Autowired
    private LifePolicyInsuredMapper lifePolicyInsuredMapper;

    @Autowired
    private LifePolicyHolderMapper lifePolicyHolderMapper;


    @Autowired
    private LifePolicyBeneficiaryMapper lifePolicyBeneficiaryMapper;

    @Autowired
    private LifePolicyProductMapper lifePolicyProductMapper;

    @Autowired
    private LifePolicyProgressMapper lifePolicyProgressMapper;

    @Autowired
    private LifePolicyStatusMapper lifePolicyStatusMapper;

    @Autowired
    private LifePolicyDocumentMapper lifePolicyDocumentMapper;

    @Autowired
    private UnLifeProductAllMapper unLifeProductAllMapper;

    @Autowired
    private LifeProductsMapper lifeProductsMapper;

    @Autowired
    private LifeProductMapper lifeProductMapper;





    /**
     *保单life_policy从合作方同步到保联
     */
    @ApiOperation("校验查询")
    @PostMapping(value = "/checkPolicy")
    public CommonResult checkPolicy(@RequestBody Param param)  {
        log.info("合作方保单迁移请求入参{}",param);
        HashMap<String, Integer> map = new HashMap<>();
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldMainNum = unLifeInsMainMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境un_life_ins_main保单表总数",oldMainNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newPolicyNum = lifePolicyMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy保单表总数",newPolicyNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newHolderNum = lifePolicyHolderMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_holder投保人表总数",newHolderNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldInsureNum = unLifeInsInsuredMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境un_life_ins_insured被保人表总数",oldInsureNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newInsuredNum = lifePolicyInsuredMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_insured被保人表总数",newInsuredNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldBeneficiaryNum = unLifeInsBeneficiaryMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境un_life_ins_beneficiary受益人表总数",oldBeneficiaryNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newBeneficiaryNum = lifePolicyBeneficiaryMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_beneficiary受益人表总数",newBeneficiaryNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldContentNum = unLifeInsContentMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境un_life_ins_content产品表总数",oldContentNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newProductNum = lifePolicyProductMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_product产品表总数",newProductNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldStateNum = unLifeInsStateMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境un_life_ins_state进度表总数",oldStateNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newProgressNum = lifePolicyProgressMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_progres进度表总数",newProgressNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newStatusNum = lifePolicyStatusMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_status状态表总数",newStatusNum);
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newDocumentNum = lifePolicyDocumentMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境life_policy_document文档表总数",newDocumentNum);
        return new CommonResult(map);
    }


    /**
     *保单life_policy从合作方同步到保联
     */
    @ApiOperation("校验查询")
    @PostMapping(value = "/checkProductMove")
    public CommonResult checkProduct(@RequestBody Param param)  {

        HashMap<String, List<String>> map = new HashMap<>();
        //老系统的产品code
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<String> oldCode = unLifeProductAllMapper.selectProductCode(param.getChannelId());

        //保联系统产品code
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        List<String> adminCode = lifeProductsMapper.selectProductCode();

        //合作方产品code
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<String> partnerCode = lifeProductMapper.selectProductCode();

        //从保联系统中和老系统产品code中获取交集
        adminCode.retainAll(oldCode);
        List<String> admin=new ArrayList<>();
        Iterator<String> iterator = oldCode.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (adminCode.contains(next)) {
                iterator.remove();
            }else {
                admin.add(next);
                log.info("没有从老系统迁移至保联后台的产品code:{}，渠道号：{}",next,param.getChannelId());
            }
        }
        map.put("渠道号"+param.getChannelId()+"没有从老系统迁移至保联后台的产品code",admin);

        partnerCode.retainAll(oldCode);
        List<String> partner=new ArrayList<>();
        Iterator<String> iterators = oldCode.iterator();
        while (iterators.hasNext()) {
            String next = iterators.next();
            if (partnerCode.contains(next)) {
                iterators.remove();
            }else {
                partner.add(next);
                log.info("没有从老系统迁移至合作方后台的产品code:{}，渠道号：{}",next,param.getChannelId());
            }
        }
        map.put("渠道号"+param.getChannelId()+"没有从老系统迁移至合作方后台的产品code",partner);
        return new CommonResult(map);
    }



}

