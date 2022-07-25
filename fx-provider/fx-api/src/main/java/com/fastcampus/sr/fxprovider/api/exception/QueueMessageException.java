package com.fastcampus.sr.fxprovider.api.exception;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode;
import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

import static com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode.QUEUE_MESSAGE_EXCEPTION;

public class QueueMessageException extends ApplicationException {
    public QueueMessageException(Throwable cause) {
        super(QUEUE_MESSAGE_EXCEPTION.getCode(), QUEUE_MESSAGE_EXCEPTION.getMessage(), cause);
    }

    @Override
    public ApplicationErrorCode getErrorCode() {
        return QUEUE_MESSAGE_EXCEPTION;
    }
}
