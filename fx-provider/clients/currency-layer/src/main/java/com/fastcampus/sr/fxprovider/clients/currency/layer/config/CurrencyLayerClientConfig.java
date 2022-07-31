package com.fastcampus.sr.fxprovider.clients.currency.layer.config;

import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerClient;
import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerFeignClient;
import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerStubClient;
import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerRestClient;
import com.fastcampus.sr.fxprovider.clients.feign.factory.FeignClientFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyLayerClientConfig {

    @ConditionalOnProperty(value = "clients.currency-layer.mode", havingValue = "feign")
    @EnableConfigurationProperties({CurrencyLayerFeignProperties.class})
    public class CurrencyLayerFeignClientConfig {


        @Bean
        public CurrencyLayerFeignClient feignClient(CurrencyLayerFeignProperties feignProperties,
                                                    ObjectFactory<HttpMessageConverters> messageConverters,
                                                    ObjectProvider<HttpMessageConverterCustomizer> customizers) {
            return FeignClientFactory.createBuilder(feignProperties.getCurrencyLayer(), messageConverters, customizers)
                    .requestInterceptor(new CurrencyLayerAuthenticationInterceptor(feignProperties.getCurrencyLayer().getAuthenticationKey()))
                    .target(CurrencyLayerFeignClient.class, feignProperties.getCurrencyLayer().getRootUri());
        }

        @Bean
        public CurrencyLayerClient currencyLayerClient(CurrencyLayerFeignClient feignClient) {
            return new CurrencyLayerRestClient(feignClient);
        }
    }

    @ConditionalOnProperty(value = "clients.currency-layer.mode", havingValue = "stub", matchIfMissing = true)
    public class CurrencyLayerStubClientConfig {
        @Bean
        public CurrencyLayerClient currencyLayerClient() {
            return new CurrencyLayerStubClient();
        }
    }
}
