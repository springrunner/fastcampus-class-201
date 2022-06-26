package com.fastcampus.springrunner.divelog.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties({ SiteProperties.class })
public class AppConfiguration {
    private final SiteProperties siteProperties;

    public AppConfiguration(SiteProperties siteProperties) {
        this.siteProperties = siteProperties;
    }

    @PostConstruct
    public void setUp() {
        log.debug("SiteProperties(authorName: {}, authorEmail: {})", siteProperties.getAuthorName(), siteProperties.getAuthorEmail());
    }
}
