package com.fastcampus.sr.fxprovider.clients.toast.exception;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

public class ToastEmailException extends ApplicationException {
    public ToastEmailException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public ApplicationErrorCode getErrorCode() {
        return ApplicationErrorCode.TOAST_EMAIL_EXCEPTION;
    }
}
