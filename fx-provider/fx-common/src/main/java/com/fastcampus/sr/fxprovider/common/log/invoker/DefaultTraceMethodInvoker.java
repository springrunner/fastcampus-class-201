package com.fastcampus.sr.fxprovider.common.log.invoker;


import com.fastcampus.sr.fxprovider.common.log.Trace;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.fastcampus.sr.fxprovider.common.log.parser.AnnotationParser.parseAnnotation;


public class DefaultTraceMethodInvoker implements TraceMethodInvoker {
    private static final Trace DEFAULT_TRACE_LOG = new Trace(){
        @Override
        public Class<? extends Annotation> annotationType() {
            return Trace.class;
        }

        @Override
        public boolean enableTraceLog() {
            return true;
        }
        @Override
        public boolean enableArguments() {
            return false;
        }
        @Override
        public boolean enableReturnValue() {
            return false;
        }
    };

    private final ConcurrentHashMap<Method, Trace> methodTraceLogMap = new ConcurrentHashMap<>();

    @Override
    public Trace getTraceLog(Method invokeMethod) {
        Trace traceLog = methodTraceLogMap.get(invokeMethod);
        if (Objects.isNull(traceLog)) {
            traceLog = loadTraceLog(invokeMethod);
            methodTraceLogMap.put(invokeMethod, traceLog);
        }
        return traceLog;
    }

    private Trace loadTraceLog(Method method) {
        Trace traceLog = parseAnnotation(method, Trace.class);
        if (Objects.isNull(traceLog)) {
            return DEFAULT_TRACE_LOG;
        }
        return traceLog;
    }
}
