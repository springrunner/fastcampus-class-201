package com.fastcampus.springrunner.divelog.common.log.invoker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;

import com.fastcampus.springrunner.divelog.common.log.parser.AnnotationParser;
import com.fastcampus.springrunner.divelog.common.log.WebTrace;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultWebTraceMethodInvoker implements WebTraceMethodInvoker {
    private static final WebTrace DEFAULT_TRACE_WEB_LOG = new WebTrace() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return WebTrace.class;
        }

        @Override
        public boolean enableWebTraceLog() {
            return true;
        }

        @Override
        public boolean enableRequestBody() {
            return false;
        }

        @Override
        public boolean enableResponseBody() {
            return false;
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
    public WebTrace getWebTrace(Method apiMethod) {
        if (Objects.isNull(apiMethod)) {
            return DEFAULT_TRACE_WEB_LOG;
        }
        
        WebTrace webTraceLog = AnnotationParser.parseAnnotation(apiMethod, WebTrace.class);
        if (Objects.isNull(webTraceLog)) {
            return DEFAULT_TRACE_WEB_LOG;
        }
        
        return webTraceLog;
    }
}
