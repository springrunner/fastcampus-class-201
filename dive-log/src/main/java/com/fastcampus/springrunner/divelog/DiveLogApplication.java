package com.fastcampus.springrunner.divelog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DiveLogApplication {

    public static void main(String[] args) {
        log.info("Info level logging.");
        SpringApplication.run(DiveLogApplication.class, args);
    }

}