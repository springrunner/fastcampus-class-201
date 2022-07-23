package com.fastcampus.sr.fxprovider.api.config;

import com.fastcampus.sr.fxprovider.common.log.TraceInfoManager;
import com.fastcampus.sr.fxprovider.common.log.TraceLogAdvisorBuilder;
import com.fastcampus.sr.fxprovider.common.log.WebTraceLog;
import com.fastcampus.sr.fxprovider.common.log.filter.WebTraceLogFilter;
import com.fastcampus.sr.fxprovider.common.log.filter.WebTraceLogFilterBeanBuilder;
import com.fastcampus.sr.fxprovider.common.log.invoker.DefaultWebTraceMethodInvoker;
import org.springframework.aop.Advisor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class ApiLogConfig {
    @Bean
    public TraceInfoManager<WebTraceLog> traceInfoManager() {
        return new TraceInfoManager<>(WebTraceLog::new);
    }

    @Bean
    public Advisor traceLogAdvisor() {
        return new TraceLogAdvisorBuilder<WebTraceLog>()
                .traceInfoManager(traceInfoManager())
                .traceLogPointcutExpression(
                        "execution(* com.fastcampus.sr.fxprovider.api..*Handler.*(..)) "
                                + "|| execution(* com.fastcampus.sr.fxprovider.api..*Service.*(..))")
                .build();
    }

    @Bean
    public FilterRegistrationBean<WebTraceLogFilter<WebTraceLog>> webTransactionLogFilter(
            RequestMappingHandlerMapping requestMappingHandlerMapping) {

        return new WebTraceLogFilterBeanBuilder<WebTraceLog>().traceInfoManager(traceInfoManager())
                .urlPatterns("/api/v1/*")
                .webTraceLogLoader(new DefaultWebTraceMethodInvoker(requestMappingHandlerMapping))
                .applyOrder(Ordered.HIGHEST_PRECEDENCE).build();
    }
}
