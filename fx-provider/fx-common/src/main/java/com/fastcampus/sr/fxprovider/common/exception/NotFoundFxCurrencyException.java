package com.fastcampus.sr.fxprovider.common.exception;

public class NotFoundFxCurrencyException extends ApplicationException {
    @Override
    public ApplicationErrorCode getErrorCode() {
        return ApplicationErrorCode.NOT_FOUND_FX_CURRENCY;
    }
}
