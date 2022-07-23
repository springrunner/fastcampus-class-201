package com.fastcampus.sr.fxprovider.common.log.invoker;


import com.fastcampus.sr.fxprovider.common.log.Trace;

import java.lang.reflect.Method;

public interface TraceMethodInvoker {

    Trace getTraceLog(Method invokeMethod);

}
