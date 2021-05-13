package com.example.mysqloracle.controller;

import com.example.mysqloracle.service.impl.news.ParmaryUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private ParmaryUserServiceImpl parmaryUserServiceImpl;

    @RequestMapping("/login")
    public Object login(Integer type){
        switch (type){
            case 1:
                return parmaryUserServiceImpl.sellPrimary();
            case 2:
                return parmaryUserServiceImpl.getList();
            default:
                return parmaryUserServiceImpl.sellSub();
        }
    }

}
