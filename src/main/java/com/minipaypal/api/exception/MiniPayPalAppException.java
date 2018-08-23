package com.minipaypal.api.exception;

import java.io.Serializable;

public class MiniPayPalAppException implements Serializable {

    private int errorCode;
    private String errorMessage;

    public MiniPayPalAppException() {
    }

    public MiniPayPalAppException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
