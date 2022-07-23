package com.fastcampus.sr.fxprovider.api.common;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;

public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(ApplicationErrorCode applicationResponseCode) {
        this.code = applicationResponseCode.getCode();
        this.message = applicationResponseCode.getMessage();
    }

    public ApiResponse(ApplicationErrorCode applicationResponseCode, T data) {
        this.code = applicationResponseCode.getCode();
        this.message = applicationResponseCode.getMessage();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
