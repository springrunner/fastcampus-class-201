package com.fastcampus.springrunner.divelog.common.log;

import java.lang.reflect.Method;

public class TraceLogConfigLoader {

    public static TraceLog getTraceLogConfig(Method invokeMethod, Object instance) {
        TraceLog traceLog = getTraceLogConfig(invokeMethod);
        if (traceLog == null && invokeMethod != null && instance != null) {
            try {
                Method instanceInvokeMethod = instance.getClass().getMethod(invokeMethod.getName(), invokeMethod.getParameterTypes());
                traceLog = getTraceLogConfig(instanceInvokeMethod);
            } catch (NoSuchMethodException e) {
                traceLog = null;
            }
        }
        return traceLog;
    }

    public static TraceLog getTraceLogConfig(Method invokeMethod) {
        TraceLog traceLog;
        if (invokeMethod == null) {
            traceLog = null;
        } else {
            traceLog = invokeMethod.getAnnotation(TraceLog.class);
            if (traceLog == null) {
                traceLog = invokeMethod.getDeclaringClass().getAnnotation(TraceLog.class);
            }
        }
        return traceLog;
    }
}
