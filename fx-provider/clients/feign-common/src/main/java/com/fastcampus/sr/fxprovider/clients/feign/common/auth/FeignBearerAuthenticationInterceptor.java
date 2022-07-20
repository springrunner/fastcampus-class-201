package com.fastcampus.sr.fxprovider.clients.feign.common.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

public class FeignBearerAuthenticationInterceptor implements RequestInterceptor {
    private final String authenticationKey;

    public FeignBearerAuthenticationInterceptor(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", this.authenticationKey));
    }
}
