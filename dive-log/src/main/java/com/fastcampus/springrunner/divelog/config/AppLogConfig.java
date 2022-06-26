package com.fastcampus.springrunner.divelog.config;

import org.springframework.aop.Advisor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.common.log.TraceLogAdvisorBuilder;
import com.fastcampus.springrunner.divelog.web.log.WebTransactionLog;
import com.fastcampus.springrunner.divelog.web.log.filter.WebTraceLogFilter;
import com.fastcampus.springrunner.divelog.web.log.filter.WebTraceLogFilterBeanBuilder;
import com.fastcampus.springrunner.divelog.web.log.loader.SimpleWebTraceLogLoader;

@Configuration
public class AppLogConfig {
    @Bean
    public TraceInfoManager<WebTransactionLog> traceInfoManager() {
        return new TraceInfoManager<>(WebTransactionLog::new);
    }

    @Bean
    public Advisor traceLogAdvisor() {
        return new TraceLogAdvisorBuilder<WebTransactionLog>()
                .traceInfoManager(traceInfoManager())
                .traceLogPointcutExpression(
                        "execution(* com.fastcampus.springrunner.divelog..*Controller.*(..)) "
                        + "|| execution(* com.fastcampus.springrunner.divelog..*Manager.*(..))")
                .build();
    }

    @Bean
    public FilterRegistrationBean<WebTraceLogFilter<WebTransactionLog>> webTransactionLogFilter(
            RequestMappingHandlerMapping requestMappingHandlerMapping) {
        
        return new WebTraceLogFilterBeanBuilder<WebTransactionLog>()
                .traceInfoManager(traceInfoManager())
                .urlPatterns("/dive-resorts/*", "/dive-points/*", "/dive-logs/*")
                .webTraceLogLoader(new SimpleWebTraceLogLoader(requestMappingHandlerMapping))
                .applyOrder(Ordered.HIGHEST_PRECEDENCE)
                .build();
    }
}
