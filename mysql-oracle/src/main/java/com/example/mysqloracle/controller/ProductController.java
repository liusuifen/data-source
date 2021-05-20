package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.server.DataApiService;
import com.example.mysqloracle.service.old.UnLifeInsMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 寿险保单-投保单主表 前端控制器
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/productMove")
@Slf4j
public class ProductController {

    @Autowired
    private DataApiService dataApiService;

    @PostMapping("/imports")
    public CommonResult imports(@RequestBody Param param){
        CommonResult commonResult = dataApiService.productMove(param);
        return commonResult;
    }


    @RequestMapping("/errorImport")
    public CommonResult errorImport(@RequestBody Param param){
        CommonResult commonResult = dataApiService.getFail(param);
        return commonResult;
    }


    @RequestMapping("/createTag")
    public CommonResult createTag(@RequestBody Param param){
        CommonResult commonResult = dataApiService.createTags(param);
        return commonResult;
    }

}

