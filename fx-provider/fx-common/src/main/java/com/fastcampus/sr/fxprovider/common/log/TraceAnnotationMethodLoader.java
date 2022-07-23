package com.fastcampus.sr.fxprovider.common.log;

import java.lang.reflect.Method;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class TraceAnnotationMethodLoader {

    public static Trace getTrace(Method invokeMethod, Object instance) {
        Trace traceLog = getTrace(invokeMethod);
        if (isNull(traceLog) && nonNull(invokeMethod) && nonNull(invokeMethod)) {
            try {
                Method instanceInvokeMethod = instance.getClass().getMethod(invokeMethod.getName(), invokeMethod.getParameterTypes());
                traceLog = getTrace(instanceInvokeMethod);
            } catch (NoSuchMethodException e) {
                traceLog = null;
            }
        }

        return traceLog;
    }

    public static Trace getTrace(Method invokeMethod) {
        Trace traceLog;
        if (Objects.isNull(invokeMethod)) {
            traceLog = null;
        } else {
            traceLog = invokeMethod.getAnnotation(Trace.class);
            if (Objects.isNull(traceLog)) {
                traceLog = invokeMethod.getDeclaringClass().getAnnotation(Trace.class);
            }
        }

        return traceLog;
    }
}
