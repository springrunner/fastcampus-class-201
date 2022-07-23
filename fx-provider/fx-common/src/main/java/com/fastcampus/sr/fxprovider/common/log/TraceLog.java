package com.fastcampus.sr.fxprovider.common.log;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Getter
@Setter
public class TraceLog {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String traceId;
    private LocalDateTime requestDateTime;
    private LocalDateTime responseDateTime;
    private int responseTime;

    public String getTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}
