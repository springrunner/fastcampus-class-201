package com.fastcampus.sr.fxprovider.common.exception;

public abstract class ApplicationException extends RuntimeException {
    private String code;

    protected ApplicationException() {
    }

    protected ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }

    protected ApplicationException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    protected ApplicationException(Throwable throwable) {
        super(throwable);
    }

    public abstract ApplicationErrorCode getErrorCode();

    public String getCode() {
        return code;
    }
}
