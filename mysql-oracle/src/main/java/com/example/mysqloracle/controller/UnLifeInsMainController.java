package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.UnLifeInsMain;
import com.example.mysqloracle.service.UnLifeInsMainService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     *
     */
    @ApiOperation("")
    @GetMapping(value = "/move")
    @ResponseBody
    public CommonResult move()  {
        log.info("unLifeInsMain----------col");
        List<UnLifeInsMain> all = unLifeInsMainService.getAll();

        return new CommonResult(all);
    }

}

