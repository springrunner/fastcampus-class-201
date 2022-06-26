package com.fastcampus.springrunner.divelog.web.log.loader;


import javax.servlet.http.HttpServletRequest;

import com.fastcampus.springrunner.divelog.web.log.WebTraceLog;

import java.lang.reflect.Method;

public interface WebTraceLogLoader {

    Method getTargetMethod(HttpServletRequest request);

    WebTraceLog getWebTraceLog(Method apiMethod);

}
