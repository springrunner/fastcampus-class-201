package com.fastcampus.sr.fxprovider.clients.toast.stub;

import com.fastcampus.sr.fxprovider.clients.toast.config.ToastEmailConfigurationProperties;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailClient;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailRequest;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToastEmailStubClient implements ToastEmailClient {
    private final ToastEmailConfigurationProperties configurationProperties;

    public ToastEmailStubClient(ToastEmailConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Override
    public ToastEmailResponse send(ToastEmailRequest request) {
        log.debug("Toast email properties: {}", configurationProperties);

        var response = new ToastEmailResponse();
        var header =  new ToastEmailResponse.ToastEmailResponseHeader();
        header.setSuccessful(true);
        response.setHeader(header);
        var body = new ToastEmailResponse.ToastEmailResponseBody();
        response.setBody(body);
        return response;
    }
}
