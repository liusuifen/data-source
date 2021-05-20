package com.example.mysqloracle.param;

/**
 * 1是，0否
 */
public enum BooleanEnum {
    NO((short) 0, "否"),
    YES((short) 1, "是");

    private Short code;
    private String desc;

    BooleanEnum(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Short getCode() {
        return code;
    }

    public Byte getByteCode() {
        return code.byteValue();
    }

    public Integer getIntCode() {
        return code.intValue();
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
