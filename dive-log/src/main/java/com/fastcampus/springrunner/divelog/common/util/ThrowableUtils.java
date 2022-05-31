package com.fastcampus.springrunner.divelog.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link Throwable} 지원용 유틸리티
 *
 * @author springrunner.kr@gmail.com
 */
public interface ThrowableUtils {

    /**
     * 스택내 최상위 예외를 찾아 반환합니다.
     *
     * @param throwable 예외 개체
     * @return
     */
    static Throwable getRootCause(Throwable throwable) {
        if (Objects.nonNull(throwable)) {
            return Stream.iterate(throwable, Throwable::getCause)
                    .filter(element -> Objects.isNull(element.getCause()))
                    .findFirst()
                    .orElse(throwable);
        }
        return throwable;
    }

    /**
     * 예외 개체에서 {@link org.springframework.boot.context.properties.bind.BindResult}를 추출합니다.
     * 없으면 {@literal null}을 반환합니다.
     *
     * @param error 예외 개체
     * @return
     */
    static BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }

}
