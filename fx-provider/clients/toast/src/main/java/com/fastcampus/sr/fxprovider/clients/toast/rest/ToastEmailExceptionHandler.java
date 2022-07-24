package com.fastcampus.sr.fxprovider.clients.toast.rest;

import com.fastcampus.sr.fxprovider.clients.toast.exception.ToastEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;

@Slf4j
public class ToastEmailExceptionHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try {
            super.handleError(response);
        } catch (RestClientResponseException rcre) {
            log.error("[toast-email] occur exception", rcre);
            throw new ToastEmailException(rcre);
        }
    }
}
