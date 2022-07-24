package com.fastcampus.sr.fxprovider.admin.common;

import com.fastcampus.sr.fxprovider.common.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalRestControllerAdvice.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ApplicationException.class})
    protected ApiResponse<Void> handle(ApplicationException applicationException) {
        log.error("[ApplicationException] Occur exception.", applicationException);
        return ApiResponseGenerator.of(ApiResponseGenerator.FAILURE.getCode(), applicationException.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    protected ApiResponse<Void> handle(Throwable throwable) {
        log.error("[UnknownException] Occur exception.", throwable);
        return ApiResponseGenerator.FAILURE;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingRequestHeaderException.class})
    protected ApiResponse<Void> handle(MissingRequestHeaderException cause) {
        log.error("[MissingRequestHeaderException] Occur exception.", cause);
        return ApiResponseGenerator.of("C400", "요청헤더값이 누락되었습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
    })
    protected ApiResponse<Void> handle(Exception exception) {
        log.error("[BadRequestException] Occur exception.", exception);
        return ApiResponseGenerator.of("C400", "[잘못된 요청] 요청내용을 확인하세요.");
    }
}
