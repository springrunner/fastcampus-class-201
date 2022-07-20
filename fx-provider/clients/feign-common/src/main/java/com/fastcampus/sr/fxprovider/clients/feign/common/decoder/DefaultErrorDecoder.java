package com.fastcampus.sr.fxprovider.clients.feign.common.decoder;

import com.fastcampus.sr.fxprovider.clients.feign.common.exception.FeignClientApiException;
import com.fastcampus.sr.fxprovider.clients.feign.common.exception.FeignClientNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class DefaultErrorDecoder implements ErrorDecoder {
    private static final Logger log = LoggerFactory.getLogger(DefaultErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("[FEIGN][{}]요청실패(status: {}, body: {})", methodKey, response.status(), response
                .body());
        if (HttpStatus.BAD_REQUEST.value() == response.status()) {
            return new FeignClientNotFoundException();
        } else {
            //TODO logger
            return new FeignClientApiException();
        }
    }
}
