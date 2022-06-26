package com.fastcampus.springrunner.divelog.web.log;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;

public class ResponseBodyLogAdvice implements ResponseBodyAdvice<ResponseEntity<?>> {
    private final TraceInfoManager<WebTransactionLog> traceInfoManager;

    public ResponseBodyLogAdvice(TraceInfoManager<WebTransactionLog> traceInfoManager) {
        this.traceInfoManager = traceInfoManager;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(ResponseEntity.class);
    }

    @Override
    public ResponseEntity<?> beforeBodyWrite(ResponseEntity<?> body, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        return null;
    }
}
