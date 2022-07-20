package com.fastcampus.sr.fxprovider.clients.feign.factory;

import feign.Contract;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 애노테이션을 사용하기 위해서 SpringMvcContract 활성화
 */
public class SpringMvcContractFactory {
    public static Contract createSpringContract(List<FeignFormatterRegistrar> feignFormatterRegistrarList) {
        return new SpringMvcContract(new ArrayList<>(), defaultConversionService(feignFormatterRegistrarList));
    }

    private static ConversionService defaultConversionService(List<FeignFormatterRegistrar> feignFormatterRegistrarList) {
        var formattingConversionService = new DefaultFormattingConversionService();
        for(FeignFormatterRegistrar registrar: feignFormatterRegistrarList) {
            registrar.registerFormatters(formattingConversionService);
        }
        return formattingConversionService;
    }
}
