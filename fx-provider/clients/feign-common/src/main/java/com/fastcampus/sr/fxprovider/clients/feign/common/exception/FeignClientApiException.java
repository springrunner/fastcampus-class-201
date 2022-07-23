package com.fastcampus.sr.fxprovider.clients.feign.common.exception;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

public class FeignClientApiException extends ApplicationException {
    @Override
    public ApplicationErrorCode getErrorCode() {
        return ApplicationErrorCode.FEIGN_CLIENT_API_EXCEPTION;
    }
}
