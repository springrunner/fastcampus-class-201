package com.fastcampus.springrunner.divelog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("app-profile")
public class AppProfileProperties {
    private String profile;
    private String siteUrl;
    
    @ConstructorBinding
    public AppProfileProperties(String profile, String siteUrl) {
        this.profile = profile;
        this.siteUrl = siteUrl;
    }

    public String getName() {
        return profile;
    }

    public String getSiteUrl() {
        return siteUrl;
    }
}
