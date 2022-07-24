package com.fastcampus.sr.fxprovider.clients.toast.rest;

import com.fastcampus.sr.fxprovider.clients.toast.config.ToastEmailConfigurationProperties;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailRequest;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailResponse;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ToastEmailRestTemplate {
    private ToastEmailConfigurationProperties toastEmailConfigurationProperties;
    private RestTemplate restTemplate;

    public ToastEmailRestTemplate(ToastEmailConfigurationProperties toastEmailConfigurationProperties) {
        this.toastEmailConfigurationProperties = toastEmailConfigurationProperties;
        this.restTemplate = new RestTemplateBuilder() // spring-boot 에서 제공
                .rootUri(toastEmailConfigurationProperties.getRootUri())
                .setConnectTimeout(toastEmailConfigurationProperties.getConnectTimeout())
                .setReadTimeout(toastEmailConfigurationProperties.getReadTimeout())
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter(ObjectMapperUtils.getObjectMapper()))
                .errorHandler(new ToastEmailExceptionHandler())
                .build();
    }

    public ToastEmailResponse send(ToastEmailRequest request) {
        return restTemplate.postForEntity(toastEmailConfigurationProperties.getSendUrl(), request, ToastEmailResponse.class).getBody();
    }
}
