package com.fastcampus.sr.fxprovider.common.log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TraceInfo<T extends TraceLog> {
    private static final int ROOT_LOG_DEPTH = 0;

    private int logDepth;
    private final String traceId;
    private final List<Long> requestTimeList;
    private final T traceLog;

    TraceInfo(T transLog) {
        logDepth = ROOT_LOG_DEPTH;

        traceId = UUID.randomUUID().toString().replace("-", "");
        traceLog = transLog;
        requestTimeList = new ArrayList<>();
        requestTimeList.add(System.currentTimeMillis());
    }

    public void incrementLogDepth() {
        logDepth++;
        requestTimeList.add(System.currentTimeMillis());
    }

    public void decrementLogDepth() {
        requestTimeList.remove(logDepth);
        logDepth--;
    }

    public int getTraceDuration() {
        return (int)(System.currentTimeMillis() - requestTimeList.get(logDepth));
    }

    public int getLogDepth() {
        return logDepth;
    }

    public boolean isRootLogDepth() {
        return logDepth <= ROOT_LOG_DEPTH;
    }

    public String getTraceId() {
        return traceId;
    }

    public T getTraceLog() {
        return traceLog;
    }

}
