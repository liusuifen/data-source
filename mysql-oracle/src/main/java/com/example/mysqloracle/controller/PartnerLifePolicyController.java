package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.news.admin.PartnerLifePolicyService;
import io.swagger.annotations.ApiOperation;
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
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/partnerLifePolicy")
@Slf4j
public class PartnerLifePolicyController {

    @Autowired
    private PartnerLifePolicyService partnerLifePolicyService;


    /**
     *保单life_policy从合作方同步到保联
     */
    @ApiOperation("保单迁移")
    @PostMapping(value = "/sync")
    public CommonResult sync(@RequestBody Param param)  {
        log.info("合作方保单迁移请求入参{}",param);
        return partnerLifePolicyService.getAll(param);
    }

    /**
     *保单life_policy主表同步
     */
    @ApiOperation("迁移失败保单重新迁移")
    @PostMapping(value = "/errorSyncAgain")
    public CommonResult errorSyncAgain(@RequestBody Param param)  {
        log.info("保单迁移请求入参{}",param);
        return partnerLifePolicyService.getError(param);
    }
}

