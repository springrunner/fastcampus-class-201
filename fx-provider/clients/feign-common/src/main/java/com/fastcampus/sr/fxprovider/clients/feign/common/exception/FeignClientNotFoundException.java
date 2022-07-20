package com.fastcampus.sr.fxprovider.clients.feign.common.exception;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;

public class FeignClientNotFoundException extends ApplicationException {
    @Override
    public String getCode() {
        return "E404";
    }
}
