package com.fastcampus.sr.fxprovider.clients.feign.common.exception;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

public class FeignClientNotFoundException extends ApplicationException {
    @Override
    public ApplicationErrorCode getErrorCode() {
        return ApplicationErrorCode.FEIGN_CLIENT_NOT_FOUND_TARGET_SERVER;
    }
}
