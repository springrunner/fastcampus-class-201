package com.fastcampus.sr.fxprovider.common.log;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trace {
    boolean enableTraceLog() default true;
    boolean enableArguments() default false;
    boolean enableReturnValue() default false;
}
