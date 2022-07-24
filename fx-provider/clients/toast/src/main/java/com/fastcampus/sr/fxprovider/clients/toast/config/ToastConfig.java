package com.fastcampus.sr.fxprovider.clients.toast.config;

import com.fastcampus.sr.fxprovider.clients.toast.rest.ToastEmailRestTemplate;
import com.fastcampus.sr.fxprovider.clients.toast.rest.ToastEmailRestTemplateClient;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailClient;
import com.fastcampus.sr.fxprovider.clients.toast.stub.ToastEmailStubClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ToastConfig {
    @Configuration
    @ConditionalOnProperty(value="toast.client-mode", havingValue = "stub", matchIfMissing = true)
    @EnableConfigurationProperties({ToastEmailConfigurationProperties.class})
    public static class StubConfig {
        @Bean
        public ToastEmailClient toastEmailClient(ToastEmailConfigurationProperties configurationProperties) {
            return new ToastEmailStubClient(configurationProperties);
        }
    }

    @Configuration
    @ConditionalOnProperty(value="toast.client-mode", havingValue = "rest", matchIfMissing = true)
    @EnableConfigurationProperties({ToastEmailConfigurationProperties.class})
    public static class RestTemplateConfig {
        @Bean
        public ToastEmailRestTemplate toastEmailRestTemplate(ToastEmailConfigurationProperties toastEmailConfigurationProperties) {
            return new ToastEmailRestTemplate(toastEmailConfigurationProperties);
        }

        @Bean
        public ToastEmailClient toastEmailClient(ToastEmailRestTemplate toastEmailRestTemplate) {
            return new ToastEmailRestTemplateClient(toastEmailRestTemplate);
        }
    }
}
