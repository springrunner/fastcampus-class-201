package com.fastcampus.springrunner.divelog.web.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointNotFoundException;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DivePointNotFoundException.class)
    public ResponseEntity<?> handlerDivePointNotFoundException(DivePointNotFoundException ex, WebRequest webRequest) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("timeStampe", LocalDateTime.now());
        errorMap.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }
}
