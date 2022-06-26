package com.fastcampus.springrunner.divelog.common.log.writer;

import org.aopalliance.intercept.MethodInvocation;

public interface TraceLogMessageGenerator {

    String generateInvocationCommonMessage(MethodInvocation methodInvocation, boolean enableDetailLog);

    String generateRequestLogMessage(int logDepth, String invocationCommonMessage);

    String generateReturnLogMessage(int logDepth, String invocationCommonMessage, MethodInvocation invocation,
            Object returnValue, int duration, boolean enableReturnValue);

    String generateErrorLogMessage(int logDepth, String invocationCommonMessage, MethodInvocation invocation,
            Throwable throwable, int duration);
}
