package com.example.mysqloracle.enums;

/**
 * 渠道对应的合作方关系
 */
public enum BannerEnum {

    //佳兆业
    Banner_顶图banner("BAB0003", "顶图banner"),
    Banner_产品("BAB0006", "产品"),
    Banner_理念("BAB0007", "理念"),
    Banner_增员("BAB0008", "增员"),
    Banner_生日("BAB0009", "生日"),
    Banner_每日一课("BAB000A", "每日一课"),
    Banner_弹窗宣传图("BAB000B", "弹窗宣传图"),
    Banner_节日("BAB000C", "节日"),
    Banner_节气("BAB000D", "节气"),
    Banner_获客("BAB000E", "获客");


    private String code;
    private String name;

    BannerEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code){
        for (BannerEnum ele : values()) {
            if(ele.getCode().equals(code))
                return ele.getName();
        }
        return null;
    }
}
