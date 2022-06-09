package com.fastcampus.springrunner.divelog;

import java.lang.annotation.*;

import org.junit.jupiter.api.Tag;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Tag("unit")
public @interface UnitTest {

}
