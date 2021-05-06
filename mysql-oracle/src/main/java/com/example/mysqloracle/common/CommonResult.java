package com.example.mysqloracle.common;

import com.example.mysqloracle.enums.ResultEnum;

public class CommonResult<T> {
    private T data;
    private String message;
    private Integer code;

    public CommonResult() {
    }

    public CommonResult(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public CommonResult(String message, Integer code) {
        this(null, message, code);
    }

    public CommonResult(ResultEnum resultEnum) {
        this(null, resultEnum.getMsg(), resultEnum.getCode());
    }

    public CommonResult(T data) {
        this(data, ResultEnum.SUCCESSFUL.getMsg(), ResultEnum.SUCCESSFUL.getCode());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
