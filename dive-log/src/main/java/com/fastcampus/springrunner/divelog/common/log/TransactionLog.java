package com.fastcampus.springrunner.divelog.common.log;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionLog {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String traceId;
    private LocalDateTime requestDateTime;
    private LocalDateTime responseDateTime;
    private int responseTime;

    public String getTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}
