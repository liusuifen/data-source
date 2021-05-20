package com.example.mysqloracle.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private String errorMessage;

    public BusinessException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.setErrorCode(errorCode);
        this.setErrorMessage(errorMessage);
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
