package com.fastcampus.springrunner.divelog.common.log;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TraceLog {
    boolean enableTraceLog() default true;
    boolean enableArguments() default true;
    boolean enableReturnValue() default true;
}
