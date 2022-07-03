package com.fastcampus.springrunner.divelog.common.log.writer;

import com.fastcampus.springrunner.divelog.common.log.WebTraceLog;

public interface WebTraceLogMessageWriter {

    String generateRequestLog(WebTraceLog transactionLog);

    String generateResponseLog(WebTraceLog transactionLog);

}
