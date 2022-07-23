package com.fastcampus.sr.fxprovider.api.documentation;

import com.fastcampus.sr.fxprovider.api.common.CustomApiResponseAdvisor;
import com.fastcampus.sr.fxprovider.api.common.GlobalRestControllerAdvice;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MockMvcFactory {
    private MockMvcFactory() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static MockMvc getMockMvc(Object... controllers) {
        return getMockMvcBuilder(controllers).build();
    }

    public static MockMvc getRestDocsMockMvc(RestDocumentationContextProvider restDocumentationContextProvider, Object... controllers) {
        return getRestDocsMockMvc(restDocumentationContextProvider, "fx-api.devfxprovider.com", controllers);
    }

    public static MockMvc getRestDocsMockMvc(RestDocumentationContextProvider restDocumentationContextProvider, String host, Object... controllers) {
        var documentationConfigurer = MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider);
        documentationConfigurer.uris().withScheme("https").withHost(host).withPort(443);
        return getMockMvcBuilder(controllers).apply(documentationConfigurer).build();
    }

    public static StandaloneMockMvcBuilder getMockMvcBuilder(Object... controllers) {
        var conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new ZonedDateTimeConverter());
        return MockMvcBuilders.standaloneSetup(controllers)
                .setControllerAdvice(
                        new GlobalRestControllerAdvice(),
                        new CustomApiResponseAdvisor()
                ).setConversionService(conversionService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(ObjectMapperUtils.getObjectMapper()));
    }

    public static class ZonedDateTimeConverter implements Converter<String, ZonedDateTime> {

        @Override
        public ZonedDateTime convert(String source) {
            return ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        }
    }
}
