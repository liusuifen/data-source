package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnLifeInsMain;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnLifeInsMainService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 前端控制器
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/unLifeInsMain")
@Slf4j
public class UnLifeInsMainController {

    @Autowired
    private UnLifeInsMainService unLifeInsMainService;

    /**
     *保单life_policy主表同步
     */
    @ApiOperation("保单迁移")
    @PostMapping(value = "/move")
    public CommonResult move(@RequestBody Param param)  {
        log.info("保单迁移请求入参{}",param);
        return unLifeInsMainService.getAll(param);
    }

    /**
     *保单life_policy主表同步
     */
    @ApiOperation("迁移失败保单重新迁移")
    @PostMapping(value = "/moveAgain")
    public CommonResult moveAgain(@RequestBody Param param)  {
        log.info("保单迁移请求入参{}",param);
        return unLifeInsMainService.getFail(param);
    }


    /**
     *获取税务类型
     */
    @ApiOperation("获取税务类型")
    @PostMapping(value = "/getTaxTypeCode")
    public CommonResult getTaxTypeCode()  {
        return unLifeInsMainService.getTaxType();
    }



}

