
package com.fastcampus.springrunner.divelog.common.log;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebTrace {

    boolean enableWebTraceLog() default true;
    boolean enableRequestBody() default false;
    boolean enableResponseBody() default false;

    String apiName() default "";
}
