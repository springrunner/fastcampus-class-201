package com.fastcampus.springrunner.divelog.common.log.filter;

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
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fastcampus.springrunner.divelog.common.log.TraceInfo;
import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.common.log.TraceLog;
import com.fastcampus.springrunner.divelog.common.log.WebTrace;
import com.fastcampus.springrunner.divelog.common.log.WebTraceLog;
import com.fastcampus.springrunner.divelog.common.log.invoker.WebTraceMethodInvoker;
import com.fastcampus.springrunner.divelog.common.log.writer.WebTraceLogMessageWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import static java.util.Objects.nonNull;

public class WebTraceLogFilter<T extends WebTraceLog> extends OncePerRequestFilter {
    private static final String LOGGER_NAME_TRACE_PREFIX = "TRACE.WEB.";
    private static final String LOGGER_NAME_TRANSACTION = "TRANSACTION";
    private static final int REQUEST_CONTENT_CACHE_LIMIT = 8 * 1024;
    private static final int RESPONSE_CONTENT_CACHE_LIMIT = 8 * 1024;

    private final Logger transactionLogger = LoggerFactory.getLogger(LOGGER_NAME_TRANSACTION);
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))).build();

    private final TraceInfoManager<T> traceInfoManager;
    private final WebTraceMethodInvoker webTraceLogLoader;
    private final WebTraceLogMessageWriter webTraceLogMessageGenerator;

    public WebTraceLogFilter(
            TraceInfoManager<T> traceInfoManager,
            WebTraceMethodInvoker webTraceMethodInvoker,
            WebTraceLogMessageWriter webTraceLogMessageWriter) {
        this.traceInfoManager = traceInfoManager;
        this.webTraceLogLoader = webTraceMethodInvoker;
        this.webTraceLogMessageGenerator = webTraceLogMessageWriter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        FilterChainExecutor executor = () -> filterChain.doFilter(request, response);

        Method apiMethod = webTraceLogLoader.getTargetMethod(request);
        if (nonNull(apiMethod)) {
            WebTrace webTraceLog = webTraceLogLoader.getWebTrace(apiMethod);

            if (webTraceLog.enableWebTraceLog()) {
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

    private void doFilterInternalAndLogging(WebTrace webTraceLog, FilterChain filterChain,
                                            ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, Logger logger)
            throws ServletException, IOException {

        TraceInfo<T> traceInfo = traceInfoManager.startLog();
        T traceLog = traceInfo.getTraceLog();
        try {
            traceInfoManager.setPaddingTraceJob(() -> {
                saveWebRequestLogInfo(traceLog, request, webTraceLog.enableRequestBody(), webTraceLog.apiName());
                logger.debug(webTraceLogMessageGenerator.generateRequestLog(traceLog));
            });
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            traceLog.setError(ex.toString());
            throw ex;
        } finally {
            traceInfoManager.executePaddingTraceJob();
            saveWebResponseLogInfo(traceLog, response, webTraceLog.enableResponseBody());
            logger.debug(webTraceLogMessageGenerator.generateResponseLog(traceLog));
            traceInfoManager.endLog();

            writeTransactionLog(traceLog);
        }
    }

    private void doFilterInternalWithTransactionLog(WebTrace webTraceLog, FilterChain filterChain,
                                                    ContentCachingRequestWrapper request, ContentCachingResponseWrapper response)
            throws ServletException, IOException {
        TraceInfo<T> traceInfo = traceInfoManager.startLog();

        T transactionLog = traceInfo.getTraceLog();
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
        transactionLog.setRequestBody(getRequestBody(request, enableRequestBodyLog));
        transactionLog.setRemoteAddress(request.getRemoteAddr());
        transactionLog.setForwarded(request.getHeader("X-Forwarded-For"));
        transactionLog.setHttpMethod(request.getMethod());
        transactionLog.setQueryString(request.getQueryString());
        transactionLog.setRequestUrl(request.getRequestURI());
        transactionLog.setApiName(apiName);
    }

    private String getRequestBody(HttpServletRequest request, boolean enableRequestBodyLog) {
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
        return body;
    }

    private String getBody(byte[] buf, int maxSize) {
        String body = "";

        if (buf.length > 0) {
            body = new String(buf).replaceAll("[\r\n]", "");
            if (body.length() > maxSize) {
                body = body.substring(0, maxSize) + " ...";
            }
        }

        return body;
    }

    private void saveWebResponseLogInfo(T transactionLog, HttpServletResponse response, boolean enableResponseBodyLog) {
        transactionLog.setResponseBody(getResponseBody(response, enableResponseBodyLog));
        transactionLog.setHttpStatus(response.getStatus());
    }

    private String getResponseBody(HttpServletResponse response, boolean enableResponseBodyLog) {
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
        return body;
    }

    /**
     * <code>[TRANSACTION]</code> 영역 로그 작성
     *
     * @param traceLog
     */
    private void writeTransactionLog(TraceLog traceLog) {
        try {
            if (transactionLogger.isInfoEnabled()) {
                transactionLogger.info(objectMapper.writeValueAsString(traceLog));
            } else {
                transactionLogger.debug(objectMapper.writeValueAsString(traceLog));
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
