package com.fastcampus.springrunner.divelog.web.log.message;

import java.util.Objects;

import com.fastcampus.springrunner.divelog.web.log.WebTransactionLog;

public class SimpleWebTraceLogMessageGenerator implements WebTraceLogMessageGenerator {

    @Override
    public String generateRequestLog(WebTransactionLog transactionLog) {
        String message = String.format("[REQ] host=%s, method=%s, url=%s", 
                transactionLog.getRemoteAddress(),
                transactionLog.getHttpMethod(), 
                transactionLog.toRequestUrl());

        if (Objects.nonNull(transactionLog.getRequestBody())) {
            message += ", body=" + transactionLog.getRequestBody();
        }

        return message;
    }

    @Override
    public String generateResponseLog(WebTransactionLog transactionLog) {
        String message = String.format("[RES] host=%s, method=%s, url=%s, status=%s", 
                transactionLog.getRemoteAddress(),
                transactionLog.getHttpMethod(), 
                transactionLog.toRequestUrl(), 
                transactionLog.getHttpStatus());

        if (Objects.nonNull(transactionLog.getRequestBody())) {
            message += ", body=" + transactionLog.getRequestBody();
        }

        if (Objects.nonNull(transactionLog.getError())) {
            message += ", error=" + transactionLog.getError();
        }

        return message;
    }

}
