package com.fastcampus.springrunner.divelog.web.log.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.web.log.WebTransactionLog;
import com.fastcampus.springrunner.divelog.web.log.loader.WebTraceLogLoader;
import com.fastcampus.springrunner.divelog.web.log.message.SimpleWebTraceLogMessageGenerator;
import com.fastcampus.springrunner.divelog.web.log.message.WebTraceLogMessageGenerator;

public class WebTraceLogFilterBeanBuilder<T extends WebTransactionLog> {

    private String[] urlPatterns;
    private TraceInfoManager<T> traceInfoManager;
    private WebTraceLogLoader webTraceLogLoader;
    private WebTraceLogMessageGenerator webTraceLogMessageGenerator;
    private Integer applyOrder;
    

    public WebTraceLogFilterBeanBuilder<T> urlPatterns(String... urlPatterns) {
        this.urlPatterns = urlPatterns;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> traceInfoManager(TraceInfoManager<T> traceInfoManager) {
        this.traceInfoManager = traceInfoManager;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> webTraceLogLoader(WebTraceLogLoader webTraceLogLoader) {
        this.webTraceLogLoader = webTraceLogLoader;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> webTraceLogMessageGenerator(WebTraceLogMessageGenerator webTraceLogMessageGenerator) {
        this.webTraceLogMessageGenerator = webTraceLogMessageGenerator;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> applyOrder(int applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public FilterRegistrationBean<WebTraceLogFilter<T>> build() {
        Assert.notNull(urlPatterns, "urlPatterns required.");
        Assert.notNull(traceInfoManager, "traceInfoManager required.");
        Assert.notNull(webTraceLogLoader, "webTraceLogLoader required.");
        
        if (webTraceLogMessageGenerator == null) {
            webTraceLogMessageGenerator = new SimpleWebTraceLogMessageGenerator();
        }
        if (applyOrder == null) {
            applyOrder = Ordered.HIGHEST_PRECEDENCE;
        }

        WebTraceLogFilter<T> webTraceLogFilter = new WebTraceLogFilter<>(
            traceInfoManager, webTraceLogLoader, webTraceLogMessageGenerator);
        
        FilterRegistrationBean<WebTraceLogFilter<T>> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(webTraceLogFilter);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.setOrder(applyOrder);

        return filterRegistrationBean;
    }

}
