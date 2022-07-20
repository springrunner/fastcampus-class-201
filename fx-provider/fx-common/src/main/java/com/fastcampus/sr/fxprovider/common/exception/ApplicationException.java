package com.fastcampus.sr.fxprovider.common.exception;

public abstract class ApplicationException extends RuntimeException {
    protected ApplicationException() {}
    protected ApplicationException(String message) {super(message);}
    protected ApplicationException(String message, Throwable throwable) {super(message,throwable);}
    protected ApplicationException(Throwable throwable) {super(throwable);}

    public abstract String getCode();
}
