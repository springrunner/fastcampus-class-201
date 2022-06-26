package com.fastcampus.springrunner.divelog.web.log.message;

import com.fastcampus.springrunner.divelog.web.log.WebTransactionLog;

public interface WebTraceLogMessageGenerator {

    String generateRequestLog(WebTransactionLog transactionLog);

    String generateResponseLog(WebTransactionLog transactionLog);

}
