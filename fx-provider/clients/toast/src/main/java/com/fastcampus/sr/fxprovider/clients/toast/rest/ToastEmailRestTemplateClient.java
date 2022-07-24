package com.fastcampus.sr.fxprovider.clients.toast.rest;

import com.fastcampus.sr.fxprovider.clients.toast.exception.ToastEmailException;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailClient;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailRequest;
import com.fastcampus.sr.fxprovider.clients.toast.spec.ToastEmailResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToastEmailRestTemplateClient implements ToastEmailClient {
    private final ToastEmailRestTemplate toastEmailRestTemplate;
    public ToastEmailRestTemplateClient(ToastEmailRestTemplate toastEmailRestTemplate) {
        this.toastEmailRestTemplate = toastEmailRestTemplate;
    }

    @Override
    public ToastEmailResponse send(ToastEmailRequest request) {
        try {
            log.debug("[toast-email] Request: {}", request);
            return toastEmailRestTemplate.send(request);
        } catch (Exception e) {
            throw new ToastEmailException(e);
        }
    }
}
