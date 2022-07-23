package com.fastcampus.sr.fxprovider.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fastcampus.sr.fx.provider.core.annotation.RestDocsTest;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxCurrencyDto;
import com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator;
import com.fastcampus.sr.fxprovider.api.documentation.MockMvcFactory;
import com.fastcampus.sr.fxprovider.api.documentation.RestDocumentationUtils;
import com.fastcampus.sr.fxprovider.api.service.FxRateQueryService;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

@RestDocsTest
class FxRateRestControllerDocsTest {

    @Mock
    FxRateQueryService fxRateQueryService;
    @InjectMocks
    FxRateRestController fxRateRestController;

    @Test
    void testGetFxRates(RestDocumentationContextProvider contextProvider) throws Exception {
        Mockito.when(fxRateQueryService.getFxRate(any()))
                .thenReturn(Arrays.asList(
                        FxCurrencyDto.of(FxCurrency.create(Currency.KRW, 1321.55d)),
                        FxCurrencyDto.of(FxCurrency.create(Currency.JPY, 132.15d))
                ));

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxRateRestController)
                .perform(RestDocumentationRequestBuilders.get("/api/v1/fx-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("get-v1-fx-rates",
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("targetCurrency").attributes(DocumentFormatGenerator.generatedEnumAttrs(Currency.class, Currency::getDescription)).description("대상통화").optional()
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("응답코드(정상: 0000)"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답메시지(정상: OK)"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답데이터"),
                                fieldWithPath("data.fxCurrencies[]").type(JsonFieldType.ARRAY).description("환율"),
                                fieldWithPath("data.fxCurrencies[].currency").type(JsonFieldType.STRING).description("통화"),
                                fieldWithPath("data.fxCurrencies[].rate").type(JsonFieldType.NUMBER).description("환율")

                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-fx-rates",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestParameters(
                                                        RequestDocumentation.parameterWithName("targetCurrency").attributes(DocumentFormatGenerator.generatedEnumAttrs(Currency.class, Currency::getDescription)).description("대상통화").optional()
                                                )
                                                .responseFields(
                                                        fieldWithPath("code").type(JsonFieldType.STRING).description("응답코드(정상: 0000)"),
                                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답메시지(정상: OK)"),
                                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답데이터"),
                                                        fieldWithPath("data.fxCurrencies[]").type(JsonFieldType.ARRAY).description("환율"),
                                                        fieldWithPath("data.fxCurrencies[].currency").type(JsonFieldType.STRING).description("통화"),
                                                        fieldWithPath("data.fxCurrencies[].rate").type(JsonFieldType.NUMBER).description("환율")

                                                )
                                                .build()
                                )
                        )

                );
    }
}