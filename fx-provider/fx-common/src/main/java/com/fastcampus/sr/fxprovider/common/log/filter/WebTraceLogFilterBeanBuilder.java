package com.fastcampus.sr.fxprovider.common.log.filter;

import com.fastcampus.sr.fxprovider.common.log.TraceInfoManager;
import com.fastcampus.sr.fxprovider.common.log.WebTraceLog;
import com.fastcampus.sr.fxprovider.common.log.invoker.WebTraceMethodInvoker;
import com.fastcampus.sr.fxprovider.common.log.writer.DefaultWebTraceLogMessageWriter;
import com.fastcampus.sr.fxprovider.common.log.writer.WebTraceLogMessageWriter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import java.util.Objects;

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
        
        if (Objects.isNull(webTraceLogMessageGenerator)) {
            webTraceLogMessageGenerator = new DefaultWebTraceLogMessageWriter();
        }
        if (Objects.isNull(applyOrder)) {
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
