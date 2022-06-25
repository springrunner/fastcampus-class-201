package com.fastcampus.springrunner.divelog.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@EnableConfigurationProperties({AppProfileProperties.class})
//@ConfigurationPropertiesScan
public class ProductionConfiguration {
    private final AppProfileProperties appProfileProperties;

    public ProductionConfiguration(AppProfileProperties appProfileProperties) {
        this.appProfileProperties = appProfileProperties;
    }
    
    @PostConstruct
    public void setUp() {
        System.out.println(String.format("Name: %s, SiteUrl: %s", appProfileProperties.getName(), appProfileProperties.getSiteUrl()));
    }   
}
