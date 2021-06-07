package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.eums.BasicEnumMapper;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.news.admin.*;
import com.example.mysqloracle.dao.old.*;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.admin.PartnerLifePolicy;
import com.example.mysqloracle.enums.PartnerEnum;
import com.example.mysqloracle.param.Param;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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

    @Autowired
    private PartnerLifePolicyMapper partnerLifePolicyMapper;

    @Autowired
    private PartnerLifePolicyHolderMapper partnerLifePolicyHolderMapper;

    @Autowired
    private PartnerLifePolicyInsuredMapper partnerLifePolicyInsuredMapper;

    @Autowired
    private PartnerLifePolicyBeneficiaryMapper partnerLifePolicyBeneficiaryMapper;

    @Autowired
    private PartnerLifePolicyProductMapper partnerLifePolicyProductMapper;

    @Autowired
    private PartnerLifePolicyProgressMapper partnerLifePolicyProgressMapper;


    @Autowired
    private PartnerLifePolicyStatusMapper partnerLifePolicyStatusMapper;

    @Autowired
    private PartnerLifePolicyDocumentMapper partnerLifePolicyDocumentMapper;

    @Autowired
    private BasicEnumMapper basicEnumMapper;




    /**
     *保单life_policy从合作方同步到保联
     */
    @ApiOperation("校验查询")
    @PostMapping(value = "/checkPolicy")
    public CommonResult checkPolicy(@RequestBody Param param)  {
        Long partnerId= PartnerEnum.getPartnerIdByChannelId(param.getChannelId());
        log.info("合作方保单迁移请求入参{}",param);
        HashMap<String, Integer> map = new HashMap<>();
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldMainNum = unLifeInsMainMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境【un_life_ins_main】保单表总数",oldMainNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newPolicyNum = lifePolicyMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy】保单表总数",newPolicyNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminPolicyNum = partnerLifePolicyMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy】保单表总数",adminPolicyNum);
        log.info("保单数量：老系统:{},新系统合作方:{},新系统保联:{}",oldMainNum,newPolicyNum,adminPolicyNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newHolderNum = lifePolicyHolderMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_holder】投保人表总数",newHolderNum);


        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminHolderNum = partnerLifePolicyHolderMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_holder】投保人表总数",adminHolderNum);

        log.info("投保人数量：老系统:{},新系统合作方:{},新系统保联:{}",oldMainNum,newHolderNum,adminHolderNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldInsureNum = unLifeInsInsuredMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境【un_life_ins_insured】被保人表总数",oldInsureNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newInsuredNum = lifePolicyInsuredMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_insured】被保人表总数",newInsuredNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminInsuredNum = partnerLifePolicyInsuredMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_insured】被保人表总数",adminInsuredNum);

        log.info("被保人数量：老系统:{},新系统合作方:{},新系统保联:{}",oldInsureNum,newInsuredNum,adminInsuredNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldBeneficiaryNum = unLifeInsBeneficiaryMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境【un_life_ins_beneficiary】受益人表总数",oldBeneficiaryNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newBeneficiaryNum = lifePolicyBeneficiaryMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_beneficiary】受益人表总数",newBeneficiaryNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminBeneficiaryNum = partnerLifePolicyBeneficiaryMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_beneficiaryd】受益人表总数",adminBeneficiaryNum);

        log.info("受益人数量：老系统:{},新系统合作方:{},新系统保联:{}",oldBeneficiaryNum,newBeneficiaryNum,adminBeneficiaryNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldContentNum = unLifeInsContentMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境【un_life_ins_content】产品表总数",oldContentNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newProductNum = lifePolicyProductMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_product】产品表总数",newProductNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminProductNum = partnerLifePolicyProductMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_product】产品表总数",adminProductNum);

        log.info("产品表数量：老系统:{},新系统合作方:{},新系统保联:{}",oldContentNum,newProductNum,adminProductNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        Integer oldStateNum = unLifeInsStateMapper.selectByChannelId(param.getChannelId());
        map.put("合作方"+param.getChannelId()+"-老系统环境【un_life_ins_state】进度表总数",oldStateNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newProgressNum = lifePolicyProgressMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_progress】进度表总数",newProgressNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminProgressNum = partnerLifePolicyProgressMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_progress】进度表总数",adminProgressNum);

        log.info("进度表数量：老系统:{},新系统合作方:{},新系统保联:{}",oldStateNum,newProgressNum,adminProgressNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newStatusNum = lifePolicyStatusMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_status】状态表总数",newStatusNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminStatusNum = partnerLifePolicyStatusMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_status】状态表总数",adminStatusNum);

        log.info("状态数量：新系统合作方:{},新系统保联:{}",newStatusNum,adminStatusNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer newDocumentNum = lifePolicyDocumentMapper.selectByChannelId();
        map.put("合作方"+param.getChannelId()+"-新系统环境合作方【life_policy_document】文档表总数",newDocumentNum);

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        Integer adminDocumentNum = partnerLifePolicyDocumentMapper.selectByChannelId(partnerId);
        map.put("合作方"+param.getChannelId()+"-新系统环境保联【partner_life_policy_product】文档表总数",adminDocumentNum);

        log.info("文档数量：新系统合作方:{},新系统保联:{}",newDocumentNum,adminDocumentNum);
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

        List<String> code=new ArrayList<>();
        code.addAll(oldCode);
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

        partnerCode.retainAll(code);
        List<String> partner=new ArrayList<>();
        Iterator<String> iterators = code.iterator();
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

    /**
     *
     */
    @ApiOperation("校验查询")
    @PostMapping(value = "/checkEnum")
    public CommonResult checkEnum()  {
        HashMap<String, String> map = new HashMap<>();
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<String> code = basicEnumMapper.checkEnum();
        code.add("LAH0001");
        code.add("LAH0002");
        code.add("LAH0003");
        code.add("LAH0004");
        code.add("LAH0005");
        code.add("LAH0006");
        code.add("LAH0007");
        code.add("LAH0008");
        code.add("LAH0009");
        code.add("LAH000A");
        code.add("LAH000B");
        code.add("LAH000C");
        code.add("LAH000D");
        code.add("LAH000E");
        code.add("LAH000F");
        code.add("LAH000G");
        code.add("LAH000H");
        code.add("LAH000I");
        for (String str : code) {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.COMMON.toString());
            String newValue = basicEnumMapper.getNewValueById(str);
            if(newValue==null || "".equals(newValue)){
                map.put("没有处理的枚举值id:"+str,str);
            }
        }
        return new CommonResult(map);
    }

    public static void main(String[] args) {
        List<String> str=new ArrayList<>();
        str.add("1");
        str.add("2");
        List<String> a=new ArrayList<>();
        a.addAll(str);
        Iterator<String> iterator = str.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            iterator.remove();
        }
        System.out.println(a);
        System.out.println(str);
    }



}

