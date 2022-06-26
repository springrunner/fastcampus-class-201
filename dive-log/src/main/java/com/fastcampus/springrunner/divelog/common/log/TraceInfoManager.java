package com.fastcampus.springrunner.divelog.common.log;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.MDC;

public class TraceInfoManager<T extends TransactionLog> {
    private static final String MDC_TRACE_ID = "TID";

    private final ThreadLocal<TraceInfo<T>> traceInfoStore = new ThreadLocal<>();
    private final ThreadLocal<Runnable> paddingTraceJobStore = new ThreadLocal<>();

    private final Supplier<T> transactionLogMaker;

    public TraceInfoManager(Supplier<T> transactionLogMaker) {
        this.transactionLogMaker = transactionLogMaker;
    }

    public TraceInfo<T> startLog() {
        TraceInfo<T> traceInfo = traceInfoStore.get();
        if (traceInfo == null) {
            T transactionLog = transactionLogMaker.get();
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
            TransactionLog transactionLog = traceInfo.getTransactionLog();
            transactionLog.setResponseDateTime(LocalDateTime.now());
            transactionLog.setResponseTime(traceInfo.getTraceDuration());
            traceInfoStore.remove();
            MDC.clear();
        } else {
            traceInfo.decrementLogDepth();
        }
        return traceInfo;
    }

    public Optional<T> transactionLog() {
        TraceInfo<T> traceInfo = traceInfoStore.get();

        if (traceInfo != null) {
            return Optional.ofNullable(traceInfo.getTransactionLog());
        }

        return Optional.empty();
    }

    public void setPaddingTraceJob(Runnable runnable) {
        paddingTraceJobStore.set(runnable);
    }

    public void executePaddingTraceJob() {
        Runnable paddingTraceJob = paddingTraceJobStore.get();
        if (paddingTraceJob != null) {
            paddingTraceJob.run();
            paddingTraceJobStore.remove();
        }
    }

}
