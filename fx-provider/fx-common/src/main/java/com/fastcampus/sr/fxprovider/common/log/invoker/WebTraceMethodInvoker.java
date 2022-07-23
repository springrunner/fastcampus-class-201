package com.fastcampus.sr.fxprovider.common.log.invoker;



import com.fastcampus.sr.fxprovider.common.log.WebTrace;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

public interface WebTraceMethodInvoker {

    Method getTargetMethod(HttpServletRequest request);

    WebTrace getWebTrace(Method apiMethod);

}
