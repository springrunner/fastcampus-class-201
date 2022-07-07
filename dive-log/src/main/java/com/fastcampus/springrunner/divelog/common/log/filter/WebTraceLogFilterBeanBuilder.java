package com.fastcampus.springrunner.divelog.common.log.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import com.fastcampus.springrunner.divelog.common.log.TraceInfoManager;
import com.fastcampus.springrunner.divelog.common.log.WebTraceLog;
import com.fastcampus.springrunner.divelog.common.log.invoker.WebTraceMethodInvoker;
import com.fastcampus.springrunner.divelog.common.log.writer.DefaultWebTraceLogMessageWriter;
import com.fastcampus.springrunner.divelog.common.log.writer.WebTraceLogMessageWriter;

public class WebTraceLogFilterBeanBuilder<T extends WebTraceLog> {

    private String[] urlPatterns;
    private TraceInfoManager<T> traceInfoManager;
    private WebTraceMethodInvoker webTraceMethodInvoker;
    private WebTraceLogMessageWriter webTraceLogMessageGenerator;
    private Integer applyOrder;
    

    public WebTraceLogFilterBeanBuilder<T> urlPatterns(String... urlPatterns) {
        this.urlPatterns = urlPatterns;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> traceInfoManager(TraceInfoManager<T> traceInfoManager) {
        this.traceInfoManager = traceInfoManager;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> webTraceLogLoader(WebTraceMethodInvoker webTraceLogLoader) {
        this.webTraceMethodInvoker = webTraceLogLoader;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> webTraceLogMessageGenerator(WebTraceLogMessageWriter webTraceLogMessageWriter) {
        this.webTraceLogMessageGenerator = webTraceLogMessageWriter;
        return this;
    }
    public WebTraceLogFilterBeanBuilder<T> applyOrder(int applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public FilterRegistrationBean<WebTraceLogFilter<T>> build() {
        Assert.notNull(urlPatterns, "urlPatterns required.");
        Assert.notNull(traceInfoManager, "traceInfoManager required.");
        Assert.notNull(webTraceMethodInvoker, "webTraceLogLoader required.");
        
        if (webTraceLogMessageGenerator == null) {
            webTraceLogMessageGenerator = new DefaultWebTraceLogMessageWriter();
        }
        if (applyOrder == null) {
            applyOrder = Ordered.HIGHEST_PRECEDENCE;
        }

        WebTraceLogFilter<T> webTraceLogFilter = new WebTraceLogFilter<>(
            traceInfoManager, webTraceMethodInvoker, webTraceLogMessageGenerator);
        
        FilterRegistrationBean<WebTraceLogFilter<T>> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(webTraceLogFilter);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        filterRegistrationBean.setOrder(applyOrder);

        return filterRegistrationBean;
    }

}
