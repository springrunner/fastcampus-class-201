package com.fastcampus.springrunner.divelog.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

//@EntityScan({
//    "com.fastcampus.springrunner.divelog.core.divelog.domain",
//    "com.fastcampus.springrunner.divelog.core.diveresort.domain"
//    })
@EntityScan(basePackageClasses = {DiveResort.class, DiveLog.class})
@Configuration
public class MyJpaConfiguration {
}
