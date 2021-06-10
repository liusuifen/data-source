package com.example.mysqloracle.enums;

/**
 * 1是，0否
 */
public enum MigrationTypeEnum {

    MIGRATION_TYPE_POLICY( 1, "保单"),
    MIGRATION_TYPE_PRODUCT( 2, "产品"),
    MIGRATION_TYPE_POLICY_TO_ADMIN(3,"合作方同步到保联"),
    MIGRATION_TYPE_RATE( 4, "折标"),
    MIGRATION_TYPE_COURSE( 5, "课程"),
    MIGRATION_TYPE_UNBANNER(6,"海报");


    private Integer code;
    private String desc;

    MigrationTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public Byte getByteCode() {
        return code.byteValue();
    }

    public Integer getIntCode() {
        return code.intValue();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
