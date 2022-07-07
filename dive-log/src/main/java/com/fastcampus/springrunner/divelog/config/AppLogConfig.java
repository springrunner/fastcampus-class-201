package com.fastcampus.springrunner.divelog.config;

import org.springframework.aop.Advisor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.common.log.TraceLogAdvisorBuilder;
import com.fastcampus.springrunner.divelog.common.log.WebTraceLog;
import com.fastcampus.springrunner.divelog.common.log.filter.WebTraceLogFilter;
import com.fastcampus.springrunner.divelog.common.log.filter.WebTraceLogFilterBeanBuilder;
import com.fastcampus.springrunner.divelog.common.log.invoker.DefaultWebTraceMethodInvoker;

@Configuration
public class AppLogConfig {
    @Bean
    public TraceInfoManager<WebTraceLog> traceInfoManager() {
        return new TraceInfoManager<>(WebTraceLog::new);
    }

    @Bean
    public Advisor traceLogAdvisor() {
        return new TraceLogAdvisorBuilder<WebTraceLog>()
                .traceInfoManager(traceInfoManager())
                .traceLogPointcutExpression(
                        "execution(* com.fastcampus.springrunner.divelog..*Controller.*(..)) "
                        + "|| execution(* com.fastcampus.springrunner.divelog..*Manager.*(..))")
                .build();
    }

    @Bean
    public FilterRegistrationBean<WebTraceLogFilter<WebTraceLog>> webTransactionLogFilter(
            RequestMappingHandlerMapping requestMappingHandlerMapping) {

        return new WebTraceLogFilterBeanBuilder<WebTraceLog>().traceInfoManager(traceInfoManager())
                .urlPatterns("/dive-resorts/*", "/dive-points/*", "/dive-logs/*")
                .webTraceLogLoader(new DefaultWebTraceMethodInvoker(requestMappingHandlerMapping))
                .applyOrder(Ordered.HIGHEST_PRECEDENCE).build();
    }
}
