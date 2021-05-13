package com.example.mysqloracle.enums;

/**
 * 性别枚举【新系统1-男 2-女】
 */
public enum GenderEnum {
    COMPANY_3_MAN("LAB0015", "1"),
    COMPANY_3_WOMAN( "LAB0016", "2");

    private String code;
    private String value;

    GenderEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
