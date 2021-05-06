package com.example.mysqloracle.enums;

/**
 * 保单状态
 */
public enum StatusEnum {
    承保中(0, 0),
    YES( 1, 0);

    private Integer code;
    private Integer newCode;

    StatusEnum(Integer code, Integer desc) {
        this.code = code;
        this.newCode = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getDesc() {
        return newCode;
    }

    public void setDesc(Integer desc) {
        this.newCode = desc;
    }
}
