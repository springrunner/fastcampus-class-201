package com.fastcampus.springrunner.divelog.web.common;

import java.util.*;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.springrunner.divelog.config.ExtendReloadableResourceBundleMessageSource;

@RestController
public class MessageSourceRestController {
    private final MessageSource messageSource;

    public MessageSourceRestController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(Locale locale) {
        ExtendReloadableResourceBundleMessageSource messageSource = (ExtendReloadableResourceBundleMessageSource) this.messageSource;
        Map<String, Object> messages = new HashMap<>();

        Properties allMessages = messageSource.getProperties(locale);

        Set<Map.Entry<Object, Object>> entries = allMessages.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            messages.put((String) key, value);
        }

        return ResponseEntity.ok(messages);
    }
}
