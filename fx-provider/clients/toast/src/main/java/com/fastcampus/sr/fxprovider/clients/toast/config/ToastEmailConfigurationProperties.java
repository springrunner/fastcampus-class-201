package com.fastcampus.sr.fxprovider.clients.toast.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Getter
@ToString
@ConfigurationProperties(prefix = "toast.email")
public class ToastEmailConfigurationProperties {
    private String rootUri;
    private Duration connectTimeout;
    private Duration readTimeout;
    private String authKey;

    @ConstructorBinding
    public ToastEmailConfigurationProperties(String rootUri, Duration connectTimeout, Duration readTimeout, String authKey) {
        this.rootUri = rootUri;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.authKey = authKey;
    }

    public String getSendUrl() {
        return String.format("/email/v2.0/appKeys/%s/sender/mail", getAuthKey());
    }
}
