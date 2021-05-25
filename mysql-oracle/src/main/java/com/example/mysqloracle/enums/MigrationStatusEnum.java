package com.example.mysqloracle.enums;

/**
 * 1是，0否
 */
public enum MigrationStatusEnum {

    MIGRATION_STATUS_SUCCESS( 1, "迁移成功"),
    MIGRATION_STATUS_FAIL( 2, "迁移失败"),
    MIGRATION_STATUS_ALREADLY( 3, "早已迁移完成");


    private Integer code;
    private String desc;

    MigrationStatusEnum(Integer code, String desc) {
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
