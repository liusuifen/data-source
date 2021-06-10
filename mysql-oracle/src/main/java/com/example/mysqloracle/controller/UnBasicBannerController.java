package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnBasicBannerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 渠道商-广告图展示图 前端控制器
 * </p>
 * 海报图迁移
 * @author blueSky
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/unBasicBanner")
@Slf4j
public class UnBasicBannerController {


    @Autowired
    private UnBasicBannerService unBasicBannerService;


    /**
     *保单life_policy主表同步
     */
    @ApiOperation("海报图迁移")
    @PostMapping(value = "/move")
    public CommonResult move(@RequestBody Param param)  {
        log.info("海报图迁移请求入参{}",param);
        return unBasicBannerService.getAll(param);
    }



}

