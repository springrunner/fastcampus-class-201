package com.fastcampus.sr.fxprovider.common.log.writer;


import com.fastcampus.sr.fxprovider.common.log.WebTraceLog;

import java.util.Objects;

public class DefaultWebTraceLogMessageWriter implements WebTraceLogMessageWriter {

    @Override
    public String generateRequestLog(WebTraceLog transactionLog) {
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
    public String generateResponseLog(WebTraceLog transactionLog) {
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
