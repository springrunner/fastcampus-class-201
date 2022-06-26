
package com.fastcampus.springrunner.divelog.web.log;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebTraceLog {

    boolean enableTraceWebLog() default true;
    boolean enableRequestBody() default false;
    boolean enableResponseBody() default false;

    String apiName() default "";
}
