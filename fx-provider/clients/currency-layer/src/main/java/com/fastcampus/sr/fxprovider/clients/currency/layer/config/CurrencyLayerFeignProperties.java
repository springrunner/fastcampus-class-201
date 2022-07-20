package com.fastcampus.sr.fxprovider.clients.currency.layer.config;

import com.fastcampus.sr.fxprovider.clients.feign.common.config.FeignConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("clients")
public class CurrencyLayerFeignProperties {
    private FeignConfigurationProperties currencyLayer;

    @ConstructorBinding
    public CurrencyLayerFeignProperties(FeignConfigurationProperties currencyLayer) {
        this.currencyLayer = currencyLayer;
    }

    public FeignConfigurationProperties getCurrencyLayer() {
        return currencyLayer;
    }
}
