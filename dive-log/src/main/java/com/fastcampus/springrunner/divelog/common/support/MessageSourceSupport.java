package com.fastcampus.springrunner.divelog.common.support;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceSupport {
    protected final MessageSource messageSource;

    public MessageSourceSupport(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    protected String message(String key, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, defaultMessage, locale);
    }

    protected String message(String key) {
        return message(key, null, null);
    }

    protected String message(String key, Object[] args) {
        return message(key, args, null);
    }

    protected String message(String key, String defaultMessage) {
        return message(key, null, defaultMessage);
    }
}
