package com.example.mysqloracle.controller;


import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.old.UnVideoLearnMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.Org;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.server.DataApiService;
import com.example.mysqloracle.service.old.UnVideoCategoryService;
import com.example.mysqloracle.service.old.UnVideoLearnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/contentMove")
@Slf4j
public class ContentController {

    @Autowired
    private UnVideoLearnService unVideoLearnService;

    @Autowired
    private UnVideoCategoryService unVideoCategoryService;



    @PostMapping("/move")
    public CommonResult move(@RequestBody Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        CommonResult videoLearn = unVideoLearnService.getAllByChannleId(param);
        return new CommonResult(videoLearn);
    }

    /**
     * 失败的重试迁移
     * @param param
     * @return
     */
    @PostMapping("/moveAgain")
    public CommonResult moveAgain(@RequestBody Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        CommonResult videoLearn = unVideoLearnService.getError(param);
        return new CommonResult(videoLearn);
    }


    @PostMapping("/categoryMove")
    public CommonResult categoryMove(@RequestBody Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.ADMIN.toString());
        CommonResult videoCategory = unVideoCategoryService.getAllCategory(param);
        return new CommonResult(videoCategory);
    }



}

