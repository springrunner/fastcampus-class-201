package com.fastcampus.sr.fxprovider.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fastcampus.sr.fx.provider.core.annotation.RestDocsTest;
import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto.FxCurrencyRateDto;
import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto.FxMoneyCalculateRequest;
import com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator;
import com.fastcampus.sr.fxprovider.api.documentation.MockMvcFactory;
import com.fastcampus.sr.fxprovider.api.documentation.RestDocumentationUtils;
import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.FxRateRestController;
import com.fastcampus.sr.fxprovider.api.domain.fxrate.service.FxRateQueryService;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.enums.MarginType;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxMoneyCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

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
                        FxCurrencyRateDto.of(FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1321.55d))),
                        FxCurrencyRateDto.of(FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(132.15d)))
                ));

        FieldDescriptor[] responseFields = {
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data").type(OBJECT).description("응답데이터"),
                fieldWithPath("data.fxCurrencies[]").type(ARRAY).description("환율"),
                fieldWithPath("data.fxCurrencies[].currency").type(STRING).description("통화"),
                fieldWithPath("data.fxCurrencies[].rate").type(NUMBER).description("환율")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxRateRestController)
                .perform(RestDocumentationRequestBuilders.get("/api/v1/fx-rates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("targetCurrency", "KRW")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("get-v1-fx-rates", // Spring REST Docs
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("targetCurrency").attributes(DocumentFormatGenerator.generatedEnumAttrs(Currency.class, Currency::getDescription)).description("대상통화").optional()
                        ),
                        responseFields(
                                responseFields
                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-fx-rates", // ePage API를 통해 openapi3.yml 생성
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestParameters(
                                                        RequestDocumentation.parameterWithName("targetCurrency").attributes(DocumentFormatGenerator.generatedEnumAttrs(Currency.class, Currency::getDescription)).description("대상통화").optional()
                                                )
                                                .responseFields(
                                                        responseFields

                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    @DisplayName("환율계산")
    void testCalculateFx(RestDocumentationContextProvider contextProvider) throws Exception {
        var fxCurrencies = Arrays.asList(
                FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1321.55d)),
                FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(132.15d))
        );

        var fxMargins = Arrays.asList(
                new FxMargin(BigDecimal.valueOf(100), BigDecimal.valueOf(200), MarginType.FIX, BigDecimal.valueOf(10))
        );


        Mockito.when(fxRateQueryService.calculateFxMoney(any()))
                .thenReturn(
                        FxMoneyCalculator.calculate(fxCurrencies, fxMargins, Currency.KRW, BigDecimal.valueOf(1_000_000d), Currency.JPY)
                );

        FxMoneyCalculateRequest calculateRequest = FxMoneyCalculateRequest.builder()
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .receiveCurrency(Currency.JPY)
                .build();
        String request = ObjectMapperUtils.toPrettyJson(calculateRequest);

        FieldDescriptor[] requestFields = {
                fieldWithPath("sendCurrency").description("송금통화"),
                fieldWithPath("sendMoney").description("송금액"),
                fieldWithPath("receiveCurrency").description("수취통화")
        };
        FieldDescriptor[] responseFields = {
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data").type(OBJECT).description("응답데이터"),
                fieldWithPath("data.sendCurrency").type(STRING).description("송금통화"),
                fieldWithPath("data.sendMoney").type(NUMBER).description("송금액"),
                fieldWithPath("data.receiveCurrency").type(STRING).description("수취통화"),
                fieldWithPath("data.expectReceiveMoney").type(NUMBER).description("예상수취금액")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxRateRestController)
                .perform(RestDocumentationRequestBuilders.post("/api/v1/fx-calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("post-v1-fx-calculate",
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        requestFields(
                                requestFields
                        ),
                        responseFields(
                                responseFields

                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("post-v1-fx-calculate",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestFields(
                                                        requestFields
                                                )
                                                .responseFields(
                                                        responseFields
                                                )
                                                .build()
                                )
                        )
                );
    }
}