package com.fastcampus.springrunner.divelog.common.log.writer;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

public class DefaultTraceLogMessageWriter implements TraceLogMessageWriter {

    @Override
    public String generateInvocationCommonMessage(MethodInvocation invocation, boolean enableDetailLog) {
        String simpleClassName = invocation.getMethod().getDeclaringClass().getSimpleName();
        String methodName = invocation.getMethod().getName();
        return String.format("%s.%s(%s)", simpleClassName, methodName, generateArgumentsLog(invocation, enableDetailLog));
    }

    @Override
    public String generateRequestLogMessage(int logDepth, String invocationCommonMessage) {
        return generateLogBuilder(logDepth, "|> ", "|--> ")
                .append(invocationCommonMessage)
                .toString();
    }

    @Override
    public String generateReturnLogMessage(int logDepth, String invocationCommonMessage, MethodInvocation invocation,
                                           Object returnValue, int duration, boolean enableReturnValue) {
        return generateLogBuilder(logDepth, "|< ", "|<-- ")
                .append(invocationCommonMessage)
                .append(" ")
                .append(generateReturnValueLog(invocation, returnValue, duration, enableReturnValue))
                .toString();
    }

    @Override
    public String generateErrorLogMessage(int logDepth, String invocationCommonMessage, MethodInvocation invocation,
                                          Throwable throwable, int duration) {
        return generateLogBuilder(logDepth, "|X ", "|<X- ")
                .append(invocationCommonMessage)
                .append(" thrown " )
                .append(generateReturnValueLog(invocation, throwable, duration, true))
                .toString();
    }

    protected StringBuilder generateLogBuilder(int logDepth, String prefixLog, String suffixLog) {
        StringBuilder logMessage = new StringBuilder();
        if (logDepth == 1) {
            logMessage.append(prefixLog);
        } else {
            for (int i = 2; i < logDepth; i++) {
                logMessage.append("|   ");
            }
            logMessage.append(suffixLog);
        }
        return logMessage;
    }

    protected String generateArgumentsLog(MethodInvocation invocation, boolean enableDetailLog) {
        Parameter[] parameters = invocation.getMethod().getParameters();
        Object[] arguments = invocation.getArguments();
        StringBuilder messages = new StringBuilder();
        int parameterSize = parameters.length;
        for(int i=0; i<parameterSize; i++) {
            if (i > 0) {
                messages.append(", ");
            }
            if (enableDetailLog) {
                messages.append(generateArgumentValueLog(arguments[i]));
            } else {
                messages.append(generateArgumentValueSimpleLog(arguments[i]));
            }
        }
        return messages.toString();
    }

    protected String generateArgumentValueLog(Object argument) {
        return generateValueObjectLog(argument);
    }

    protected String generateArgumentValueSimpleLog(Object argument) {
        return generateValueObjectSimpleLog(argument);
    }

    @SuppressWarnings("rawtypes")
    protected String generateReturnValueLog(MethodInvocation invocation, Object returnValue, int duration, boolean enableDetailLog) {
        Class returnType = invocation.getMethod().getReturnType();
        String returnLogMessage;
        if (returnType == void.class) {
            returnLogMessage = "void";
        } else if (enableDetailLog) {
            returnLogMessage = generateValueObjectLog(returnValue);
        } else {
            returnLogMessage = generateValueObjectSimpleLog(returnValue);
        }
        return String.format("[%s] %,dms.", returnLogMessage, duration);
    }

    @SuppressWarnings("rawtypes")
    protected String generateValueObjectLog(Object value) {
        final int MAX_ARGUMENT_VALUE_LENGTH = 2048;
        if (value == null) {
            return null;
        }
        if (List.class.isAssignableFrom(value.getClass())) {
            List list = (List)value;
            if (list.size() > 1) {
                return String.format("[%s, ... (size: %,d)]", list.get(0), list.size());
            } else {
                return list.toString();
            }
        } else if (Collection.class.isAssignableFrom(value.getClass())) {
            return String.format("collection(size: %,d)", ((Collection)value).size());
        } else if (value.getClass().isArray()) {
            return String.format("array[length: %,d]", Array.getLength(value));
        } else if (Map.class.isAssignableFrom(value.getClass())) {
            return String.format("map[size: %,d]", ((Map)value).size());
        } else {
            String logMessage = value.toString();
            if (logMessage == null) {
                return "NULL";
            }
            if (logMessage.length() > MAX_ARGUMENT_VALUE_LENGTH) {
                return logMessage.substring(0, MAX_ARGUMENT_VALUE_LENGTH) + " ...";
            } else {
                return logMessage;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    protected String generateValueObjectSimpleLog(Object value) {
        if (value == null) {
            return null;
        }
        Class valueType = value.getClass();
        if (Collection.class.isAssignableFrom(valueType)) {
            return String.format("%s[size: %,d]", valueType.getSimpleName(), ((Collection)value).size());
        } else if (valueType.isArray()) {
            return String.format("array[length: %,d]", Array.getLength(value));
        } else if (Map.class.isAssignableFrom(value.getClass())) {
            return String.format("map[size: %,d]", ((Map)value).size());
        } else if (isSimpleType(value.getClass())) {
            return value.toString();
        } else if (String.class.isAssignableFrom(valueType)) {
            return formatSimpleStringValue(value.toString());
        } else {
            return valueType.getSimpleName();
        }
    }

    @SuppressWarnings("rawtypes")
    protected boolean isSimpleType(Class type) {
        return Boolean.class.equals(type) || Number.class.isAssignableFrom(type) || CharSequence.class.isAssignableFrom(type);
    }

    protected String formatSimpleStringValue(String value) {
        final int MAX_ARGUMENT_VALUE_LENGTH = 40;
        if (value.length() < MAX_ARGUMENT_VALUE_LENGTH) {
            return value;
        } else {
            return value.substring(0, MAX_ARGUMENT_VALUE_LENGTH) + " ...";
        }
    }

}
