package com.fastcampus.springrunner.divelog.common.log.loader;

import static com.fastcampus.springrunner.divelog.common.log.parser.AnnotationParser.parseAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import com.fastcampus.springrunner.divelog.common.log.TraceLog;

public class SimpleTraceLogLoader implements TraceLogLoader {
    private static final TraceLog DEFAULT_TRACE_LOG = new TraceLog(){
        @Override
        public Class<? extends Annotation> annotationType() {
            return TraceLog.class;
        }

        @Override
        public boolean enableTraceLog() {
            return true;
        }
        @Override
        public boolean enableArguments() {
            return true;
        }
        @Override
        public boolean enableReturnValue() {
            return true;
        }
    };

    private final ConcurrentHashMap<Method, TraceLog> methodTraceLogMap = new ConcurrentHashMap<>();

    @Override
    public TraceLog getTraceLog(Method invokeMethod) {
        TraceLog traceLog = methodTraceLogMap.get(invokeMethod);
        if (traceLog == null) {
            traceLog = loadTraceLog(invokeMethod);
            methodTraceLogMap.put(invokeMethod, traceLog);
        }
        return traceLog;
    }

    private TraceLog loadTraceLog(Method method) {
        TraceLog traceLog = parseAnnotation(method, TraceLog.class);
        if (traceLog == null) {
            return DEFAULT_TRACE_LOG;
        }
        return traceLog;
    }
}
