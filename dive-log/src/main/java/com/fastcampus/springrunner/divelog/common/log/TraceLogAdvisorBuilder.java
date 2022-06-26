package com.fastcampus.springrunner.divelog.common.log;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

import com.fastcampus.springrunner.divelog.common.log.loader.SimpleTraceLogLoader;
import com.fastcampus.springrunner.divelog.common.log.loader.TraceLogLoader;
import com.fastcampus.springrunner.divelog.common.log.writer.GeneralTraceLogMessageGenerator;
import com.fastcampus.springrunner.divelog.common.log.writer.TraceLogMessageGenerator;

public class TraceLogAdvisorBuilder<T extends TransactionLog> {

    private String traceLogPointcutExpression;
    private TraceInfoManager<T> traceInfoManager;
    private TraceLogLoader traceLogLoader;
    private TraceLogMessageGenerator traceLogMessageGenerator;
    private Integer applyOrder;

    public TraceLogAdvisorBuilder<T> traceLogPointcutExpression(String traceLogPointcutExpression) {
        this.traceLogPointcutExpression = traceLogPointcutExpression;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceInfoManager(TraceInfoManager<T> traceInfoManager) {
        this.traceInfoManager = traceInfoManager;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceLogLoader(TraceLogLoader traceLogLoader) {
        this.traceLogLoader = traceLogLoader;
        return this;
    }

    public TraceLogAdvisorBuilder<T> traceLogMessageGenerator(TraceLogMessageGenerator traceLogMessageGenerator) {
        this.traceLogMessageGenerator = traceLogMessageGenerator;
        return this;
    }

    public TraceLogAdvisorBuilder<T> applyOrder(int applyOrder) {
        this.applyOrder = applyOrder;
        return this;
    }

    public Advisor build() {
        Assert.notNull(traceLogPointcutExpression, "traceLogPointcutExpression required.");
        Assert.notNull(traceInfoManager, "traceInfoManager required.");
        if (traceLogLoader == null) {
            traceLogLoader = new SimpleTraceLogLoader();
        }
        if (traceLogMessageGenerator == null) {
            traceLogMessageGenerator = new GeneralTraceLogMessageGenerator();
        }
        if (applyOrder == null) {
            applyOrder = Ordered.HIGHEST_PRECEDENCE + 1;
        }

        ComposablePointcut pointcut = new ComposablePointcut(new AnnotationMatchingPointcut(TraceLog.class));
        pointcut.union(new AnnotationMatchingPointcut(null, TraceLog.class));

        AspectJExpressionPointcut expressionPointcut = new AspectJExpressionPointcut();
        expressionPointcut.setExpression(traceLogPointcutExpression);
        pointcut.union((Pointcut) expressionPointcut);

        TraceLogMethodInterceptor<T> methodInterceptor = new TraceLogMethodInterceptor<>(
                traceInfoManager,
                traceLogLoader, 
                traceLogMessageGenerator);

        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, methodInterceptor);
        pointcutAdvisor.setOrder(applyOrder);
        return pointcutAdvisor;
    }

}
