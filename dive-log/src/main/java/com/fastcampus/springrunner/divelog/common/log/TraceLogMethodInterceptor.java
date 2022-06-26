package com.fastcampus.springrunner.divelog.common.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fastcampus.springrunner.divelog.common.log.loader.TraceLogLoader;
import com.fastcampus.springrunner.divelog.common.log.writer.TraceLogMessageGenerator;

public class TraceLogMethodInterceptor<T extends TransactionLog> implements MethodInterceptor {
    private static final String LOGGER_NAME_PREFIX = "TRACE.";

    private final TraceInfoManager<T> traceInfoManager;
    private final TraceLogLoader traceLogLoader;
    private final TraceLogMessageGenerator logMessageGenerator;

    public TraceLogMethodInterceptor(
            TraceInfoManager<T> traceInfoManager, 
            TraceLogLoader traceLogLoader,
            TraceLogMessageGenerator logMessageGenerator) {
        
        this.traceInfoManager = traceInfoManager;
        this.traceLogLoader = traceLogLoader;
        this.logMessageGenerator = logMessageGenerator;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceLog traceLog = traceLogLoader.getTraceLog(invocation.getMethod());
        MethodInvocationExecutor executor = invocation::proceed;
        if (traceLog.enableTraceLog()) {
            String loggerName = LOGGER_NAME_PREFIX + invocation.getMethod().getDeclaringClass().getName();
            Logger logger = LoggerFactory.getLogger(loggerName);
            if (logger.isDebugEnabled()) {
                executor = () -> invokeAndLogging(traceLog, logger, invocation);
            }
        }
        return executor.execute();
    }

    private Object invokeAndLogging(TraceLog traceLog, Logger logger, MethodInvocation invocation) throws Throwable {
        TraceInfo<T> traceInfo = traceInfoManager.startLog();
        String commonMessage = logMessageGenerator.generateInvocationCommonMessage(invocation,
                traceLog.enableArguments());
        logger.debug(logMessageGenerator.generateRequestLogMessage(traceInfo.getLogDepth(), commonMessage));
        try {
            Object returnValue = invocation.proceed();

            logger.debug(logMessageGenerator.generateReturnLogMessage(traceInfo.getLogDepth(), commonMessage,
                    invocation, returnValue, traceInfo.getTraceDuration(), traceLog.enableReturnValue()));

            return returnValue;
        } catch (Throwable ex) {
            logger.debug(logMessageGenerator.generateErrorLogMessage(traceInfo.getLogDepth(), commonMessage, invocation,
                    ex, traceInfo.getTraceDuration()));

            throw ex;
        } finally {
            traceInfoManager.endLog();
        }
    }

    @FunctionalInterface
    public interface MethodInvocationExecutor<R, X extends Throwable> {
        R execute() throws X;
    }
}
