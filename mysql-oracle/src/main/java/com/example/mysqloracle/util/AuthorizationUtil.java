package com.example.mysqloracle.util;

import com.example.mysqloracle.enums.BooleanEnum;
import com.example.mysqloracle.param.MapParam;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AuthorizationUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

    public static String createSign(Map<String,Object> paramMap,String secretKey) throws Exception {
        logger.info("原数据：{}",paramMap);
        Map<String, Object>  sortMap = MapUtil.sortMap(paramMap);
        logger.info("排序后：{}",sortMap);
//        String json = JSONObject.toJSONString(sortMap);
//        logger.debug("转换json：{}",json);
        String combineString = MapUtil.combineParams(sortMap);
        logger.info("拼接字符串：{}",combineString);
        return SHAUtil.hmacsha1(combineString,secretKey);
    }

    public static void main(String[] args) throws Exception {
//        {"isMaintenanceFee":1,"isAfterHesitate":1,"id":15848683840995328},
//        "sign":"f3869af9e3f3595f6e53bfc9d3aa0c1b18bf5d407a08ccbdf4f5257f928812e9","app_id":"2020112515091144","ts":"1610101281"}
        List<Map> syncList = Lists.newArrayList();
//            syncList.add(MapParam.create("isMaintenanceFee","1")
//                    .add("partnerStatus", PartnerStatus.SUCCESSFUL.getCode())
//                    .add("id", 15848683840995328L)
//                    .add("isAfterHesitate", 1));
        MapParam policyMap =MapParam.create("id",15848683840995328L)
                .add("isAfterHesitate", BooleanEnum.YES.getCode())
                .add("isMaintenanceFee","1");
        Map<String,Object> paramMap = MapParam.create("partner_life_policy",policyMap);
        paramMap.put("ts",String.valueOf(System.currentTimeMillis()/1000));
        paramMap.put("app_id","2020112515091144");
        String sign = createSign(paramMap,"fdaeaeckshfdaeaeckshfdaeaeckshrf");
        logger.info("签名："+sign);
    }

}
