package com.fastcampus.sr.fxprovider.clients.feign.factory;

import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import java.beans.Encoder;

public class FeignEncoderFactory {
    private FeignEncoderFactory() {
        throw new IllegalStateException("Factory class");
    }
}
