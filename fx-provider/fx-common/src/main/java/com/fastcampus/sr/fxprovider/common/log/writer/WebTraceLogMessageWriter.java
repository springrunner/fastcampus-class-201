package com.fastcampus.sr.fxprovider.common.log.writer;

import com.fastcampus.sr.fxprovider.common.log.WebTraceLog;

public interface WebTraceLogMessageWriter {

    String generateRequestLog(WebTraceLog transactionLog);

    String generateResponseLog(WebTraceLog transactionLog);

}
