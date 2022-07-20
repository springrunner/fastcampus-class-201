package com.fastcampus.sr.fxprovider.clients.feign.common.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

public class FeignBasicAuthenticationInterceptor implements RequestInterceptor {
    private final String authenticationKey;

    public FeignBasicAuthenticationInterceptor(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, String.format("Basic %s", this.authenticationKey));
    }
}
