package com.fastcampus.sr.fxprovider.clients.currency.layer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CurrencyLayerAuthenticationInterceptor implements RequestInterceptor {
    private final String apikey;

    public CurrencyLayerAuthenticationInterceptor(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("apikey", apikey);
    }
}
