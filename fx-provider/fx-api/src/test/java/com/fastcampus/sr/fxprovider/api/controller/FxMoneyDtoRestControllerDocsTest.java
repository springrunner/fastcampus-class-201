package com.fastcampus.sr.fxprovider.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fastcampus.sr.fx.provider.core.annotation.RestDocsTest;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator;
import com.fastcampus.sr.fxprovider.api.documentation.MockMvcFactory;
import com.fastcampus.sr.fxprovider.api.documentation.RestDocumentationUtils;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.FxTradeRestController;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTradeFacade;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.TradeHistoryQueryService;
import com.fastcampus.sr.fxprovider.common.Constant;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator.customFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@RestDocsTest
class FxMoneyDtoRestControllerDocsTest {
    @Mock
    FxTradeFacade fxTradeFacade;
    @Mock
    TradeHistoryQueryService tradeHistoryQueryService;
    @InjectMocks
    FxTradeRestController fxTradeRestController;

    @Test
    void testSendMoney(RestDocumentationContextProvider contextProvider) throws Exception {
        String memberNumber = "20220724134101";

        FxTradeSendCommand sendCommand = FxTradeSendCommand.builder()
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .senderName("?????????")
                .senderEmail("sender@gmail.com")
                .senderAddress1("????????? ?????????")
                .senderAddress2("??????7??? ?????????")
                .senderContactNumber("+82-010-0000-0000")
                .senderIdentifyNumber("111111-1111111")
                .receiverName("?????????")
                .receiverEmail("receiver@gmail.com")
                .receiverAddress1("??????")
                .receiverAddress2("??????????")
                .receiverContactNumber("")
                .receiveCurrency(Currency.JPY)
                .receiverContactNumber("+81-0000-0000-0000")
                .receiverIdentifyNumber("12345-678-1234")
                .build();
        String request = ObjectMapperUtils.toPrettyJson(sendCommand);

        var fxCurrencies = Arrays.asList(
                FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1321.55d)),
                FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(132.15d))
        );

        TradeHistory tradeHistory = TradeHistory.builder()
                .memberNumber(memberNumber)
                .sendCurrency(sendCommand.getSendCurrency())
                .sendMoney(sendCommand.getSendMoney())
                .sendFxRate(BigDecimal.valueOf(1321.55d))
                .senderName(sendCommand.getSenderName())
                .senderEmail(sendCommand.getSenderEmail())
                .senderAddress1(sendCommand.getSenderAddress1())
                .senderAddress2(sendCommand.getSenderAddress2())
                .senderContactNumber(sendCommand.getSenderContactNumber())
                .senderIdentifyNumber(sendCommand.getSenderIdentifyNumber())
                .receiveCurrency(sendCommand.getReceiveCurrency())
                .receiveMoney(BigDecimal.valueOf(999_999d))
                .receiverName(sendCommand.getReceiverName())
                .receiverEmail(sendCommand.getReceiverEmail())
                .receiverAddress1(sendCommand.getReceiverAddress1())
                .receiverAddress2(sendCommand.getReceiverAddress2())
                .receiverContactNumber(sendCommand.getReceiverContactNumber())
                .receiverIdentifyNumber(sendCommand.getReceiverIdentifyNumber())
                .receiveFxRate(BigDecimal.valueOf(132.15d))
                .build();
        Mockito.when(fxTradeFacade.sendMoney(anyString(), any()))
                .thenReturn(tradeHistory);

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxTradeRestController)
                .perform(RestDocumentationRequestBuilders.post("/api/v1/trade/send")
                        .header(Constant.HEADER_MEMBER_NUMBER, memberNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("post-v1-trade-send",
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        requestHeaders(
                                headerWithName(Constant.HEADER_MEMBER_NUMBER).description("????????????")
                        ),
                        requestFields(
                                fieldWithPath("sendCurrency").type(STRING).description("????????????"),
                                fieldWithPath("sendMoney").type(NUMBER).description("?????????"),
                                fieldWithPath("senderName").type(STRING).description("????????? ??????"),
                                fieldWithPath("senderEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("senderAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("senderAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("senderContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("senderIdentifyNumber").type(STRING).description("????????? ????????????"),
                                fieldWithPath("receiveCurrency").type(STRING).description("????????????"),
                                fieldWithPath("receiverName").type(STRING).description("????????? ??????"),
                                fieldWithPath("receiverEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("receiverAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("receiverAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("receiverContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("receiverIdentifyNumber").type(STRING).description("????????? ????????????")
                        ),
                        responseFields(
                                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                                fieldWithPath("data").type(OBJECT).description("???????????????"),
                                fieldWithPath("data.tradeNumber").type(STRING).description("????????????"),
                                fieldWithPath("data.memberNumber").type(STRING).description("????????????"),
                                fieldWithPath("data.tradeStatus").type(STRING).description("????????????"),
                                fieldWithPath("data.tradeStatusDesc").type(STRING).description("???????????? ??????"),
                                fieldWithPath("data.requestDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????"),
                                fieldWithPath("data.inProgressDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.canceledDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.completedDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.sendCurrency").type(STRING).description("????????????"),
                                fieldWithPath("data.sendMoney").type(NUMBER).description("?????????"),
                                fieldWithPath("data.sendFxRate").type(NUMBER).description("?????? ??????"),
                                fieldWithPath("data.senderName").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.senderEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.senderContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.senderAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("data.senderAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("data.senderIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)"),
                                fieldWithPath("data.receiveCurrency").type(STRING).description("????????????"),
                                fieldWithPath("data.receiveMoney").type(NUMBER).description("????????????"),
                                fieldWithPath("data.receiveFxRate").type(NUMBER).description("?????? ??????"),
                                fieldWithPath("data.receiverName").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.receiverEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.receiverContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.receiverAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("data.receiverAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)")
                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("post-v1-trade-send",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestHeaders(
                                                        headerWithName(Constant.HEADER_MEMBER_NUMBER).description("????????????")
                                                )
                                                .requestFields(
                                                        fieldWithPath("sendCurrency").description("????????????"),
                                                        fieldWithPath("sendMoney").description("?????????"),
                                                        fieldWithPath("senderName").description("????????? ??????"),
                                                        fieldWithPath("senderEmail").description("????????? ?????????"),
                                                        fieldWithPath("senderAddress1").description("????????? ??????1"),
                                                        fieldWithPath("senderAddress2").description("????????? ??????2"),
                                                        fieldWithPath("senderContactNumber").description("????????? ?????????"),
                                                        fieldWithPath("senderIdentifyNumber").description("????????? ????????????"),
                                                        fieldWithPath("receiveCurrency").description("????????????"),
                                                        fieldWithPath("receiverName").description("????????? ??????"),
                                                        fieldWithPath("receiverEmail").description("????????? ?????????"),
                                                        fieldWithPath("receiverAddress1").description("????????? ??????1"),
                                                        fieldWithPath("receiverAddress2").description("????????? ??????2"),
                                                        fieldWithPath("receiverContactNumber").description("????????? ?????????"),
                                                        fieldWithPath("receiverIdentifyNumber").description("????????? ????????????")
                                                )
                                                .responseFields(
                                                        fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                                                        fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                                                        fieldWithPath("data").type(OBJECT).description("???????????????"),
                                                        fieldWithPath("data.tradeNumber").type(STRING).description("????????????"),
                                                        fieldWithPath("data.memberNumber").type(STRING).description("????????????"),
                                                        fieldWithPath("data.tradeStatus").type(STRING).description("????????????"),
                                                        fieldWithPath("data.tradeStatusDesc").type(STRING).description("???????????? ??????"),
                                                        fieldWithPath("data.requestDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????"),
                                                        fieldWithPath("data.inProgressDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.canceledDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.completedDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.sendCurrency").type(STRING).description("????????????"),
                                                        fieldWithPath("data.sendMoney").type(NUMBER).description("?????????"),
                                                        fieldWithPath("data.sendFxRate").type(NUMBER).description("?????? ??????"),
                                                        fieldWithPath("data.senderName").type(STRING).description("????????? ??????"),
                                                        fieldWithPath("data.senderEmail").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.senderContactNumber").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.senderAddress1").type(STRING).description("????????? ??????1"),
                                                        fieldWithPath("data.senderAddress2").type(STRING).description("????????? ??????2"),
                                                        fieldWithPath("data.senderIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)"),
                                                        fieldWithPath("data.receiveCurrency").type(STRING).description("????????????"),
                                                        fieldWithPath("data.receiveMoney").type(NUMBER).description("????????????"),
                                                        fieldWithPath("data.receiveFxRate").type(NUMBER).description("?????? ??????"),
                                                        fieldWithPath("data.receiverName").type(STRING).description("????????? ??????"),
                                                        fieldWithPath("data.receiverEmail").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.receiverContactNumber").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.receiverAddress1").type(STRING).description("????????? ??????1"),
                                                        fieldWithPath("data.receiverAddress2").type(STRING).description("????????? ??????2"),
                                                        fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)")
                                                )
                                                .build()
                                )
                        )
                );
    }

    @Test
    @DisplayName("???????????? ??????")
    void testGetTradeHistory(RestDocumentationContextProvider contextProvider) throws Exception {
        String memberNumber = "20220724141501";
        String tradeNumber = UUID.randomUUID().toString();

        TradeHistory tradeHistory = TradeHistory.builder()
                .memberNumber(memberNumber)
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .sendFxRate(BigDecimal.valueOf(1321.55d))
                .senderName("?????????")
                .senderEmail("sender@fxprovider.com")
                .senderAddress1("????????? ?????????")
                .senderAddress2("?????????")
                .senderContactNumber("+82-010-0000-0000")
                .senderIdentifyNumber("111111-1111111")
                .receiveCurrency(Currency.JPY)
                .receiveMoney(BigDecimal.valueOf(999_999d))
                .receiverName("?????????")
                .receiverEmail("receiver@fxprovider.com")
                .receiverAddress1("????????? ?????????")
                .receiverAddress2("?????????")
                .receiverContactNumber("+81-0000-0000-0000")
                .receiverIdentifyNumber("1234-567-8910")
                .receiveFxRate(BigDecimal.valueOf(132.15d))
                .build();

        Mockito.when(tradeHistoryQueryService.findByMemberNumberAndTradeNumber(anyString(), anyString()))
                        .thenReturn(tradeHistory);

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxTradeRestController)
                .perform(RestDocumentationRequestBuilders.get("/api/v1/trade/{tradeNumber}", tradeNumber)
                        .header(Constant.HEADER_MEMBER_NUMBER, memberNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("get-v1-trade-history",
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        requestHeaders(
                                headerWithName(Constant.HEADER_MEMBER_NUMBER).description("????????????")
                        ),
                        pathParameters(
                                parameterWithName("tradeNumber").attributes(customFormat("STRING")).description("????????????")
                        ),
                        responseFields(
                                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                                fieldWithPath("data").type(OBJECT).description("???????????????"),
                                fieldWithPath("data.tradeNumber").type(STRING).description("????????????"),
                                fieldWithPath("data.memberNumber").type(STRING).description("????????????"),
                                fieldWithPath("data.tradeStatus").type(STRING).description("????????????"),
                                fieldWithPath("data.tradeStatusDesc").type(STRING).description("???????????? ??????"),
                                fieldWithPath("data.requestDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????"),
                                fieldWithPath("data.inProgressDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.canceledDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.completedDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                fieldWithPath("data.sendCurrency").type(STRING).description("????????????"),
                                fieldWithPath("data.sendMoney").type(NUMBER).description("?????????"),
                                fieldWithPath("data.sendFxRate").type(NUMBER).description("?????? ??????"),
                                fieldWithPath("data.senderName").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.senderEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.senderContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.senderAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("data.senderAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("data.senderIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)"),
                                fieldWithPath("data.receiveCurrency").type(STRING).description("????????????"),
                                fieldWithPath("data.receiveMoney").type(NUMBER).description("????????????"),
                                fieldWithPath("data.receiveFxRate").type(NUMBER).description("?????? ??????"),
                                fieldWithPath("data.receiverName").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.receiverEmail").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.receiverContactNumber").type(STRING).description("????????? ?????????"),
                                fieldWithPath("data.receiverAddress1").type(STRING).description("????????? ??????1"),
                                fieldWithPath("data.receiverAddress2").type(STRING).description("????????? ??????2"),
                                fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)")
                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-trade-history",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestHeaders(
                                                        headerWithName(Constant.HEADER_MEMBER_NUMBER).description("????????????")
                                                )
                                                .pathParameters(
                                                        parameterWithName("tradeNumber").attributes(customFormat("STRING")).description("????????????")
                                                )
                                                .responseFields(
                                                        fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                                                        fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                                                        fieldWithPath("data").type(OBJECT).description("???????????????"),
                                                        fieldWithPath("data.tradeNumber").type(STRING).description("????????????"),
                                                        fieldWithPath("data.memberNumber").type(STRING).description("????????????"),
                                                        fieldWithPath("data.tradeStatus").type(STRING).description("????????????"),
                                                        fieldWithPath("data.tradeStatusDesc").type(STRING).description("???????????? ??????"),
                                                        fieldWithPath("data.requestDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????"),
                                                        fieldWithPath("data.inProgressDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.canceledDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.completedDateTime").type(STRING).attributes(DocumentFormatGenerator.zonedDateTimeFormat()).description("??????????????????").optional(),
                                                        fieldWithPath("data.sendCurrency").type(STRING).description("????????????"),
                                                        fieldWithPath("data.sendMoney").type(NUMBER).description("?????????"),
                                                        fieldWithPath("data.sendFxRate").type(NUMBER).description("?????? ??????"),
                                                        fieldWithPath("data.senderName").type(STRING).description("????????? ??????"),
                                                        fieldWithPath("data.senderEmail").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.senderContactNumber").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.senderAddress1").type(STRING).description("????????? ??????1"),
                                                        fieldWithPath("data.senderAddress2").type(STRING).description("????????? ??????2"),
                                                        fieldWithPath("data.senderIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)"),
                                                        fieldWithPath("data.receiveCurrency").type(STRING).description("????????????"),
                                                        fieldWithPath("data.receiveMoney").type(NUMBER).description("????????????"),
                                                        fieldWithPath("data.receiveFxRate").type(NUMBER).description("?????? ??????"),
                                                        fieldWithPath("data.receiverName").type(STRING).description("????????? ??????"),
                                                        fieldWithPath("data.receiverEmail").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.receiverContactNumber").type(STRING).description("????????? ?????????"),
                                                        fieldWithPath("data.receiverAddress1").type(STRING).description("????????? ??????1"),
                                                        fieldWithPath("data.receiverAddress2").type(STRING).description("????????? ??????2"),
                                                        fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("????????? ????????????(????????????, ??????????????? ???)")
                                                )
                                                .build()
                                )
                        )
                );
    }
}