package com.fastcampus.sr.fxprovider.clients.feign.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Validated
@ConfigurationProperties("feign")
public class FeignConfigurationProperties {

    @NotEmpty
    private String rootUri;
    @NotNull
    private Integer maxRequests;
    @NotNull
    private Integer maxRequestsPerHost;
    @NotNull
    private Duration connectTimeout;
    @NotNull
    private Duration readTimeout;
    @NotNull
    private Integer maxIdleConnections;
    @NotNull
    private Duration keepAliveDuration;

    @Nullable
    private String authenticationKey;

    /**
     *
     * @param rootUri
     * @param maxRequests
     * @param maxRequestsPerHost
     * @param connectTimeout
     * @param readTimeout
     * @param maxIdleConnections
     * @param keepAliveDuration 커넥션 유지기간(seconds)
     * @param authenticationKey
     */
    @ConstructorBinding
    public FeignConfigurationProperties(String rootUri,
                                        Integer maxRequests,
                                        Integer maxRequestsPerHost,
                                        Duration connectTimeout,
                                        Duration readTimeout,
                                        Integer maxIdleConnections,
                                        Duration keepAliveDuration,
                                        @Nullable String authenticationKey) {
        this.rootUri = rootUri;
        this.maxRequests = maxRequests;
        this.maxRequestsPerHost = maxRequestsPerHost;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDuration = keepAliveDuration;
        this.authenticationKey = authenticationKey;
    }

    public String getRootUri() {
        return rootUri;
    }

    public Integer getMaxRequests() {
        return maxRequests;
    }

    public Integer getMaxRequestsPerHost() {
        return maxRequestsPerHost;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public Integer getMaxIdleConnections() {
        return maxIdleConnections;
    }

    public Duration getKeepAliveDuration() {
        return keepAliveDuration;
    }

    @Nullable
    public String getAuthenticationKey() {
        return authenticationKey;
    }
}
