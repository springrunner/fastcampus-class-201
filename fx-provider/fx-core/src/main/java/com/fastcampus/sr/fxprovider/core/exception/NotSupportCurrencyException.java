package com.fastcampus.sr.fxprovider.core.exception;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

public class NotSupportCurrencyException extends ApplicationException {
    @Override
    public ApplicationErrorCode getErrorCode() {
        return ApplicationErrorCode.NOT_SUPPORT_CURRENCY;
    }

    public NotSupportCurrencyException(String message) {
        super(ApplicationErrorCode.NOT_SUPPORT_CURRENCY.getCode(), message);
    }

    public NotSupportCurrencyException(Currency currency) {
        super(ApplicationErrorCode.NOT_SUPPORT_CURRENCY.getCode(), String.format("지원하지 않는 통화(Currency, %s)입니다.", currency));
    }
}
