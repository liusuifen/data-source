package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnLifeProductRatio;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.server.DataApiService;
import com.example.mysqloracle.service.old.UnLifeProductRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 寿险产品系数 前端控制器
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@RestController
@RequestMapping("/unLifeProductRatio")
public class UnLifeProductRatioController {


    @Autowired
    private UnLifeProductRatioService unLifeProductRatioService;

    @PostMapping("/moveRatio")
    public CommonResult moveRatio(@RequestBody Param param){
        CommonResult commonResult = unLifeProductRatioService.getRateData(param);
        return commonResult;
    }

    /**
     * 生成数据模板
     * @param param
     * @return
     */
    @RequestMapping("/saveTemplate")
    public CommonResult saveTemplate(@RequestBody Param param){
        CommonResult commonResult = unLifeProductRatioService.saveTemplate(param);
        return commonResult;
    }

}

