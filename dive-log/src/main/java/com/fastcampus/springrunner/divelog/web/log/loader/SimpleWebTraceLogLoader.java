package com.fastcampus.springrunner.divelog.web.log.loader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;

import com.fastcampus.springrunner.divelog.common.log.parser.AnnotationParser;
import com.fastcampus.springrunner.divelog.web.log.WebTraceLog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SimpleWebTraceLogLoader implements WebTraceLogLoader {
    private static final WebTraceLog DEFAULT_TRACE_WEB_LOG = new WebTraceLog() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return WebTraceLog.class;
        }

        @Override
        public boolean enableTraceWebLog() {
            return true;
        }

        @Override
        public boolean enableRequestBody() {
            return true;
        }

        @Override
        public boolean enableResponseBody() {
            return true;
        }

        @Override
        public String apiName() {
            return "";
        }
    };

    private final RequestMappingHandlerMapping handlerMapping;

    @Override
    public Method getTargetMethod(HttpServletRequest request) {
        try {
            // @see https://github.com/spring-projects/spring-boot/issues/28874
            if (!ServletRequestPathUtils.hasParsedRequestPath(request)) {
                ServletRequestPathUtils.parseAndCache(request);
            }

            HandlerExecutionChain handlerChain = handlerMapping.getHandler(request);
            if (handlerChain == null) {
                log.warn("RequestHandler not found.");
                return null;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handlerChain.getHandler();
            return handlerMethod.getMethod();
        } catch (Exception e) {
            log.warn("Controller invoke method not found.", e);
            return null;
        }
    }

    @Override
    public WebTraceLog getWebTraceLog(Method apiMethod) {
        if (apiMethod == null) {
            return DEFAULT_TRACE_WEB_LOG;
        }
        WebTraceLog webTraceLog = AnnotationParser.parseAnnotation(apiMethod, WebTraceLog.class);
        if (webTraceLog == null) {
            return DEFAULT_TRACE_WEB_LOG;
        }
        return webTraceLog;
    }
}
