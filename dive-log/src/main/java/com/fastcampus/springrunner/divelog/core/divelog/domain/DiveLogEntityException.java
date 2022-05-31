package com.fastcampus.springrunner.divelog.core.divelog.domain;

import com.fastcampus.springrunner.divelog.common.exception.SystemException;

@SuppressWarnings("serial")
public class DiveLogEntityException extends SystemException {
    public DiveLogEntityException(String foramt, Object... args) {
        super(String.format(foramt, args));
    }
}
