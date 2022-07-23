package com.fastcampus.sr.fxprovider.common.log;

import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;

public class TraceInfoManager<T extends TraceLog> {
    private static final String MDC_TRACE_ID = "TID";

    private final ThreadLocal<TraceInfo<T>> traceInfoStore = new ThreadLocal<>();
    private final ThreadLocal<Runnable> paddingTraceJobStore = new ThreadLocal<>();

    private final Supplier<T> traceLogMaker;

    public TraceInfoManager(Supplier<T> traceLogMaker) {
        this.traceLogMaker = traceLogMaker;
    }

    public TraceInfo<T> startLog() {
        TraceInfo<T> traceInfo = traceInfoStore.get();
        if (Objects.isNull(traceInfo)) {
            T transactionLog = traceLogMaker.get();
            traceInfo = new TraceInfo<>(transactionLog);
            if (MDC.get(MDC_TRACE_ID) == null) {
                MDC.put(MDC_TRACE_ID, traceInfo.getTraceId());
            }
            transactionLog.setTraceId(traceInfo.getTraceId());
            transactionLog.setRequestDateTime(LocalDateTime.now());
            traceInfoStore.set(traceInfo);
        } else {
            executePaddingTraceJob();
            traceInfo.incrementLogDepth();
        }
        return traceInfo;
    }

    public TraceInfo<T> endLog() {
        executePaddingTraceJob();
        TraceInfo<T> traceInfo = traceInfoStore.get();
        if (traceInfo.isRootLogDepth()) {
            TraceLog transactionLog = traceInfo.getTraceLog();
            transactionLog.setResponseDateTime(LocalDateTime.now());
            transactionLog.setResponseTime(traceInfo.getTraceDuration());
            traceInfoStore.remove();
            MDC.clear();
        } else {
            traceInfo.decrementLogDepth();
        }
        return traceInfo;
    }

    public Optional<T> traceLog() {
        TraceInfo<T> traceInfo = traceInfoStore.get();

        if (nonNull(traceInfo)) {
            return Optional.ofNullable(traceInfo.getTraceLog());
        }

        return Optional.empty();
    }

    public void setPaddingTraceJob(Runnable runnable) {
        paddingTraceJobStore.set(runnable);
    }

    public void executePaddingTraceJob() {
        Runnable paddingTraceJob = paddingTraceJobStore.get();
        if (nonNull(paddingTraceJob)) {
            paddingTraceJob.run();
            paddingTraceJobStore.remove();
        }
    }
}
