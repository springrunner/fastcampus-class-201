package com.fastcampus.sr.fxprovider.api.common;

import com.fastcampus.sr.fxprovider.api.exception.ApiResponseJsonProcessingException;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;
import java.util.Objects;

@Slf4j
@RestControllerAdvice(basePackages = {"com.fastcampus.sr.fxprovider.api.controller"})
public class CustomApiResponseAdvisor implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        var type = GenericTypeResolver.resolveType(genericType(returnType), returnType.getContainingClass());

        if(Void.class.getName().equals(type.getTypeName())) {
            return false;
        }

        return !converterType.isAssignableFrom(ByteArrayHttpMessageConverter.class) &&
                !converterType.isAssignableFrom(ResourceHttpMessageConverter.class);
    }

    private Type genericType(MethodParameter returnType) {
        if(HttpEntity.class.isAssignableFrom(returnType.getParameterType())) {
            return ResolvableType.forType(returnType.getGenericParameterType()).getGeneric().getType();
        } else {
            return returnType.getGenericParameterType();
        }
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {

        var responseHttpStatus = HttpStatus.valueOf(
                ((ServletServerHttpResponse) response).getServletResponse().getStatus()
        );

        if(Objects.isNull(body)) {
            return responseHttpStatus.isError() ? ApiResponseGenerator.FAILURE : ApiResponseGenerator.SUCCESS;
        }

        var apiResponse = responseHttpStatus.isError() ? ApiResponseGenerator.failure(body) : ApiResponseGenerator.success(body);
        log.trace("[CustomApiResponseAdisor][ApiResponse] {}", apiResponse);

        if(selectedConverterType.isAssignableFrom(StringHttpMessageConverter.class)) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return ObjectMapperUtils.toPrettyJson(apiResponse);
            } catch (ObjectMapperUtils.JsonReadException jre) {
                log.warn("[CustomApiResponseAdisor][Exception] {}", jre);

                throw new ApiResponseJsonProcessingException(jre);
            }
        }

        return apiResponse;
    }
}
