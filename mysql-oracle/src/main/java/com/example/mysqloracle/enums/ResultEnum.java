package com.example.mysqloracle.enums;

public enum ResultEnum {

    SYSTEM_EXCEPTION(500,"系统异常"),
    PARAM_EXCEPTION(400,"参数异常"),
    OTHER_EXCEPTION(999,"其他异常"),
    SUCCESSFUL(200,"操作成功");

    private int code;

    private String msg;

    ResultEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
