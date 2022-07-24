package com.fastcampus.sr.fxprovider.common.exception;

import static com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode.FAILURE_API_RESPONSE_JSON_PROCESSING;

public class ApiResponseJsonProcessingException extends ApplicationException {
    @Override
    public ApplicationErrorCode getErrorCode() {
        return FAILURE_API_RESPONSE_JSON_PROCESSING;
    }

    public ApiResponseJsonProcessingException(Throwable cause) {
        super(FAILURE_API_RESPONSE_JSON_PROCESSING.getCode(), FAILURE_API_RESPONSE_JSON_PROCESSING.getMessage(), cause);
    }
}
