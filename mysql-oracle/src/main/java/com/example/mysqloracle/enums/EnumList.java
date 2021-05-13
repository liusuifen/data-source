package com.example.mysqloracle.enums;

import java.util.List;
import java.util.Map;

public class EnumList {

    private Map<String,String> genderEnum;

    public EnumList(Map<String, String> genderEnum) {
        genderEnum.put("LAB0015","1");
        genderEnum.put("LAB0016","2");
        this.genderEnum = genderEnum;
    }



}
