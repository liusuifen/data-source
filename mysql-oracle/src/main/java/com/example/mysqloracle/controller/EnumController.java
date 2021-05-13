package com.example.mysqloracle.controller;

import com.example.mysqloracle.param.EnumParam;
import com.example.mysqloracle.service.eums.BasicEnumService;
import com.example.mysqloracle.service.impl.news.ParmaryUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnumController {

    @Autowired
    private BasicEnumService basicEnumService;

    @PostMapping("/enum")
    public Object login(@RequestBody EnumParam enumParam){
        switch (enumParam.getType()){
            case "gender":
                return basicEnumService.getGender();
            case "idType":
                return basicEnumService.getIdType();
            case "holderMarriage":
                return basicEnumService.getHolderMarriage();
            case "bank":
                return basicEnumService.getBank();
            case "nation":
                return basicEnumService.getNation();
            case "beneficiaryIsInsured":
                return basicEnumService.getBeneficiaryIsInsured();
            case "hasSSid":
                return basicEnumService.getHasSSid();
            case "sourceFrom":
                return basicEnumService.getSourceFrom();
            case "taxType":
                return basicEnumService.getTaxType();
            case "earnings":
                return basicEnumService.getEarningsType();
            case "sum":
                basicEnumService.getGender();
                basicEnumService.getIdType();
                basicEnumService.getHolderMarriage();
                basicEnumService.getBank();
                basicEnumService.getNation();
                basicEnumService.getHasSSid();
                basicEnumService.getSourceFrom();
                basicEnumService.getTaxType();
                basicEnumService.getEarningsType();
                return basicEnumService.getBeneficiaryIsInsured();
            default:
                return basicEnumService.getGender();
        }
    }

}
