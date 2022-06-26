package com.fastcampus.springrunner.divelog.web.log.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fastcampus.springrunner.divelog.common.log.TraceInfo;
import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.common.log.TransactionLog;
import com.fastcampus.springrunner.divelog.web.log.WebTraceLog;
import com.fastcampus.springrunner.divelog.web.log.WebTransactionLog;
import com.fastcampus.springrunner.divelog.web.log.loader.WebTraceLogLoader;
import com.fastcampus.springrunner.divelog.web.log.message.WebTraceLogMessageGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class WebTraceLogFilter<T extends WebTransactionLog> extends OncePerRequestFilter {
    private static final String LOGGER_NAME_TRACE_PREFIX = "TRACE.WEB.";
    private static final String LOGGER_NAME_TRANSACTION = "TRANSACTION";
    private static final int REQUEST_CONTENT_CACHE_LIMIT = 8 * 1024;
    private static final int RESPONSE_CONTENT_CACHE_LIMIT = 8 * 1024;

    private final Logger transactionLogger = LoggerFactory.getLogger(LOGGER_NAME_TRANSACTION);
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))).build();

    private final TraceInfoManager<T> traceInfoManager;
    private final WebTraceLogLoader webTraceLogLoader;
    private final WebTraceLogMessageGenerator logMessageGenerator;

    public WebTraceLogFilter(
            TraceInfoManager<T> traceInfoManager, 
            WebTraceLogLoader webTraceLogLoader,
            WebTraceLogMessageGenerator logMessageGenerator) {
        this.traceInfoManager = traceInfoManager;
        this.webTraceLogLoader = webTraceLogLoader;
        this.logMessageGenerator = logMessageGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        FilterChainExecutor executor = () -> filterChain.doFilter(request, response);
        
        Method apiMethod = webTraceLogLoader.getTargetMethod(request); 
        if (apiMethod != null) {
            WebTraceLog webTraceLog = webTraceLogLoader.getWebTraceLog(apiMethod);
            
            if (webTraceLog.enableTraceWebLog()) {
                String loggerName = LOGGER_NAME_TRACE_PREFIX + apiMethod.getDeclaringClass().getName();
                Logger logger = LoggerFactory.getLogger(loggerName);

                if (logger.isInfoEnabled() || logger.isDebugEnabled()) {
                    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request,
                            REQUEST_CONTENT_CACHE_LIMIT);
                    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

                    if (logger.isDebugEnabled()) {
                        executor = () -> doFilterInternalAndLogging(webTraceLog, filterChain, requestWrapper,
                                responseWrapper, logger);
                    } else {
                        executor = () -> doFilterInternalWithTransactionLog(webTraceLog, filterChain, requestWrapper,
                                responseWrapper);
                    }
                }
            }
        }
        executor.execute();
    }

    private void doFilterInternalAndLogging(WebTraceLog webTraceLog, FilterChain filterChain,
            ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, Logger logger)
            throws ServletException, IOException {
        TraceInfo<T> traceInfo = traceInfoManager.startLog();
        
        T transactionLog = traceInfo.getTransactionLog();
        try {
            traceInfoManager.setPaddingTraceJob(() -> {
                saveWebRequestLogInfo(transactionLog, request, webTraceLog.enableRequestBody(), webTraceLog.apiName());
                logger.debug(logMessageGenerator.generateRequestLog(transactionLog));
            });
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            transactionLog.setError(ex.toString());
            throw ex;
        } finally {
            traceInfoManager.executePaddingTraceJob();
            saveWebResponseLogInfo(transactionLog, response, webTraceLog.enableResponseBody());
            logger.debug(logMessageGenerator.generateResponseLog(transactionLog));
            traceInfoManager.endLog();
            
            writeTransactionLog(transactionLog);
        }
    }

    private void doFilterInternalWithTransactionLog(WebTraceLog webTraceLog, FilterChain filterChain,
            ContentCachingRequestWrapper request, ContentCachingResponseWrapper response)
            throws ServletException, IOException {
        TraceInfo<T> traceInfo = traceInfoManager.startLog();
        T transactionLog = traceInfo.getTransactionLog();
        try {
            traceInfoManager.setPaddingTraceJob(() -> {
                saveWebRequestLogInfo(transactionLog, request, webTraceLog.enableRequestBody(), webTraceLog.apiName());
            });
            
            filterChain.doFilter(request, response);
        } finally {
            saveWebResponseLogInfo(transactionLog, response, webTraceLog.enableResponseBody());
            traceInfoManager.endLog();
            writeTransactionLog(transactionLog);
        }
    }

    private void saveWebRequestLogInfo(T transactionLog, HttpServletRequest request, boolean enableRequestBodyLog,
            String apiName) {
        String body;
        if (request instanceof ContentCachingRequestWrapper) {
            if (enableRequestBodyLog) {
                ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
                body = getBody(requestWrapper.getContentAsByteArray(), REQUEST_CONTENT_CACHE_LIMIT);
            } else {
                body = "(****)";
            }
        } else {
            body = "[unsupported]";
        }
        transactionLog.setRequestBody(body);
        transactionLog.setRemoteAddress(request.getRemoteAddr());
        transactionLog.setForwarded(request.getHeader("X-Forwarded-For"));
        transactionLog.setHttpMethod(request.getMethod());
        transactionLog.setRequestUrl(request.getRequestURI()
                + (!StringUtils.hasText(request.getQueryString()) ? "" : "?" + request.getQueryString()));
        transactionLog.setApiName(apiName);
    }

    private void saveWebResponseLogInfo(T transactionLog, HttpServletResponse response, boolean enableResponseBodyLog) {
        String body;
        if (response instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
            String contentType = response.getContentType();
            if (contentType != null && contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                if (enableResponseBodyLog) {
                    body = getBody(responseWrapper.getContentAsByteArray(), RESPONSE_CONTENT_CACHE_LIMIT);
                } else {
                    body = "(****)";
                }
            } else {
                body = "[unsupported content-type]";
            }
            try {
                responseWrapper.copyBodyToResponse();
            } catch (IOException e) {
                logger.debug("response stream already closed.", e);
            }
        } else {
            body = "[unsupported]";
        }
        transactionLog.setResponseBody(body);
        transactionLog.setHttpStatus(response.getStatus());
    }

    private String getBody(byte[] buf, int maxSize) {
        String body;
        if (buf.length > 0) {
            body = new String(buf).replaceAll("[\r\n]", "");
            if (body.length() > maxSize) {
                body = body.substring(0, maxSize) + " ...";
            }
        } else {
            body = "";
        }
        return body;
    }

    private void writeTransactionLog(TransactionLog transactionLog) {
        try {
            if (transactionLogger.isInfoEnabled()) {
                transactionLogger.info(objectMapper.writeValueAsString(transactionLog));
            } else {
                transactionLogger.debug(objectMapper.writeValueAsString(transactionLog));
            }
        } catch (JsonProcessingException e) {
            transactionLogger.error("transaction log writer error.", e);
        }
    }

    @FunctionalInterface
    public interface FilterChainExecutor {
        void execute() throws ServletException, IOException;
    }

}
