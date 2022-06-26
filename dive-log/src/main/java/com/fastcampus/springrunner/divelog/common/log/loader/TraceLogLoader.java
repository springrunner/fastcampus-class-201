package com.fastcampus.springrunner.divelog.common.log.loader;

import java.lang.reflect.Method;

import com.fastcampus.springrunner.divelog.common.log.TraceLog;

public interface TraceLogLoader {

    TraceLog getTraceLog(Method invokeMethod);

}
