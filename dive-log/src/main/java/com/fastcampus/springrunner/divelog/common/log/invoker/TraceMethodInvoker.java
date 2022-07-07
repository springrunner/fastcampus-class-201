package com.fastcampus.springrunner.divelog.common.log.invoker;

import java.lang.reflect.Method;

import com.fastcampus.springrunner.divelog.common.log.Trace;

public interface TraceMethodInvoker {

    Trace getTraceLog(Method invokeMethod);

}
