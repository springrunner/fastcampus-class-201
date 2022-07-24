package com.fastcampus.sr.fxprovider.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Map;

@ConfigurationProperties("authentication")
public class AuthenticationConfigurationProperties {
    private boolean enable;
    private List<String> paths;
    private Map<String, String> authKeys;

    @ConstructorBinding
    public AuthenticationConfigurationProperties(boolean enable, List<String> paths, Map<String, String> authKeys) {
        this.enable = enable;
        this.paths = paths;
        this.authKeys = authKeys;
    }

    public boolean isEnable() {
        return enable;
    }

    public List<String> getPaths() {
        return paths;
    }

    public Map<String, String> getAuthKeys() {
        return authKeys;
    }
}
