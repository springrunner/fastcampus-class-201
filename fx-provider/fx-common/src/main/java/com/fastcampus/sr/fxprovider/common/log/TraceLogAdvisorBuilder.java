package com.fastcampus.sr.fxprovider.common.log;

import com.fastcampus.sr.fxprovider.common.log.invoker.DefaultTraceMethodInvoker;
import com.fastcampus.sr.fxprovider.common.log.invoker.TraceMethodInvoker;
import com.fastcampus.sr.fxprovider.common.log.writer.DefaultTraceLogMessageWriter;
import com.fastcampus.sr.fxprovider.common.log.writer.TraceLogMessageWriter;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import java.util.Objects;

import static java.util.Objects.nonNull;

public class TraceLogAdvisorBuilder<T extends TraceLog> {

    private String traceLogPointcutExpression;
    private TraceInfoManager<T> traceInfoManager;
    private TraceMethodInvoker traceMethodInvoker;
    private TraceLogMessageWriter traceLogMessageWriter;
    private Integer applyOrder;

    public TraceLogAdvisorBuilder<T> traceLogPointcutExpression(String traceLogPointcutExpression) {
        this.traceLogPointcutExpression = traceLogPointcutExpression;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceInfoManager(TraceInfoManager<T> traceInfoManager) {
        this.traceInfoManager = traceInfoManager;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceLogLoader(TraceMethodInvoker traceLogLoader) {
        this.traceMethodInvoker = traceLogLoader;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceLogMessageGenerator(TraceLogMessageWriter traceLogMessageWriter) {
        this.traceLogMessageWriter = traceLogMessageWriter;
        return this;
    }

    public TraceLogAdvisorBuilder<T> applyOrder(int applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public Advisor build() {
        Assert.notNull(traceInfoManager, "traceInfoManager required.");
        if (Objects.isNull(traceMethodInvoker)) {
            traceMethodInvoker = new DefaultTraceMethodInvoker();
        }
        if (Objects.isNull(traceLogMessageWriter)) {
            traceLogMessageWriter = new DefaultTraceLogMessageWriter();
        }
        if (Objects.isNull(applyOrder)) {
            applyOrder = Ordered.HIGHEST_PRECEDENCE + 1;
        }

        ComposablePointcut pointcut = new ComposablePointcut(new AnnotationMatchingPointcut(Trace.class));
        pointcut.union(new AnnotationMatchingPointcut(null, Trace.class));

        if(nonNull(traceLogMessageWriter)) {
            AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
            expressionPointcut.setExpression(traceLogPointcutExpression);
            pointcut.union((Pointcut) expressionPointcut);    
        }
        
        TraceLogMethodInterceptor<T> methodInterceptor = new TraceLogMethodInterceptor<>(
                traceInfoManager,
                traceMethodInvoker,
                traceLogMessageWriter);

        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, methodInterceptor);
        pointcutAdvisor.setOrder(applyOrder);
        return pointcutAdvisor;
    }
}
