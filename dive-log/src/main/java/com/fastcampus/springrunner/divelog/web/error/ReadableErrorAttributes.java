package com.fastcampus.springrunner.divelog.web.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fastcampus.springrunner.divelog.common.util.ThrowableUtils;

/**
 * 스프링 부트에 기본 구현체인 {@link DefaultErrorAttributes}에 message 속성을 덮어쓰기 할 목적으로 작성한
 * 컴포넌트이다.
 * <p>
 * DefaultErrorAttributes 는 message 속성을 예외 객체의 값을 사용하기 때문에 사용자가 읽기에 좋은 문구가 아니다.
 * 해당 메시지를 보다 읽기 좋은 문구로 가공해서 제공한다.
 *
 * @author springrunner.kr@gmail.com
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ReadableErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final DefaultErrorAttributes delegate;

    private MessageSource messageSource;

    public ReadableErrorAttributes(MessageSource messageSource) {
        this(messageSource, false);
    }

    public ReadableErrorAttributes(MessageSource messageSource, boolean includeException) {
        this.delegate = new DefaultErrorAttributes();
        this.messageSource = messageSource;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        log.debug("나는 일하고 있다.");

        Map<String, Object> attributes = delegate.getErrorAttributes(webRequest, options);
        attributes.put("timeStamp", LocalDateTime.now());
        Throwable error = getError(webRequest);

        if (Objects.nonNull(error)) {
            addErrorTrace(attributes, error);
            
            log.debug("Locale: {}, Error class: {}", LocaleContextHolder.getLocale(), error.getClass().getSimpleName());
            if (MessageSourceResolvable.class.isAssignableFrom(error.getClass())) {
                String errorMessage = messageSource.getMessage((MessageSourceResolvable) error, LocaleContextHolder.getLocale());

                attributes.put("message", errorMessage);
            } else {
                // Exception.{ClassName}
                // Exception.MethodArgumentNotValidException
                String errorCode = String.format("Exception.%s", error.getClass().getSimpleName());
                String errorMessage = messageSource.getMessage(errorCode, new Object[0], error.getMessage(),
                        LocaleContextHolder.getLocale());

                attributes.put("message", errorMessage);
            }

            // errors 속성에 메시지를 사용자 친화적으로 변경하도록 하겠습니다.
            BindingResult bindingResult = ThrowableUtils.extractBindingResult(error);
            if (Objects.nonNull(bindingResult)) {
                List<String> errors = bindingResult.getAllErrors().stream()
                        .map(oe -> messageSource.getMessage(oe, webRequest.getLocale())).collect(Collectors.toList());

                attributes.put("errors", errors);
            }
        }

        return attributes;
    }

    private void addErrorTrace(Map<String, Object> attributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        attributes.put("trace", stackTrace.toString());
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return delegate.getError(webRequest);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception error) {
        return delegate.resolveException(request, response, handler, error);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
