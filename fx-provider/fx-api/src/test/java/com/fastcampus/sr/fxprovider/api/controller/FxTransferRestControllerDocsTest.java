package com.fastcampus.sr.fxprovider.api.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fastcampus.sr.fx.provider.core.annotation.RestDocsTest;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.documentation.MockMvcFactory;
import com.fastcampus.sr.fxprovider.api.documentation.RestDocumentationUtils;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.FxTransferRestController;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTransferFacade;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTransferTradeHistoryQueryService;
import com.fastcampus.sr.fxprovider.common.Constant;
import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator.customFormat;
import static com.fastcampus.sr.fxprovider.api.documentation.DocumentFormatGenerator.zonedDateTimeFormat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@RestDocsTest
class FxTransferRestControllerDocsTest {
    @Mock
    FxTransferFacade fxTransferFacade;
    @InjectMocks
    FxTransferRestController fxTransferRestController;

    @Test
    @DisplayName("송금내역 API문서")
    void testSendMoney(RestDocumentationContextProvider contextProvider) throws Exception {
        String memberNumber = "20220724134101";

        FxTradeSendCommand sendCommand = FxTradeSendCommand.builder()
                .sendCountry(Country.KOR)
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .senderName("송금자")
                .senderEmail("sender@gmail.com")
                .senderAddress1("강원도 동해시")
                .senderAddress2("감추7길 어딘가")
                .senderContactNumber("+82-010-0000-0000")
                .senderIdentifyNumber("111111-1111111")
                .receiverName("수취인")
                .receiverEmail("receiver@gmail.com")
                .receiverAddress1("일본")
                .receiverAddress2("어딘가?")
                .receiveCountry(Country.JPN)
                .receiveCurrency(Currency.JPY)
                .receiverContactNumber("+81-0000-0000-0000")
                .receiverIdentifyNumber("12345-678-1234")
                .build();
        String request = ObjectMapperUtils.toPrettyJson(sendCommand);

        FxTransferHistory fxTransferHistory = FxTransferHistory.builder()
                .memberNumber(memberNumber)
                .sendCountry(Country.KOR)
                .sendCurrency(sendCommand.getSendCurrency())
                .sendMoney(sendCommand.getSendMoney())
                .sendFxRate(BigDecimal.valueOf(1321.55d))
                .senderName(sendCommand.getSenderName())
                .senderEmail(sendCommand.getSenderEmail())
                .senderAddress1(sendCommand.getSenderAddress1())
                .senderAddress2(sendCommand.getSenderAddress2())
                .senderContactNumber(sendCommand.getSenderContactNumber())
                .senderIdentifyNumber(sendCommand.getSenderIdentifyNumber())
                .receiveCountry(Country.JPN)
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
        Mockito.when(fxTransferFacade.sendMoney(anyString(), any()))
                .thenReturn(FxTransferHistoryDto.of(fxTransferHistory));

        FieldDescriptor[] requestFields = {
                fieldWithPath("sendCountry").type(STRING).description("송금국가"),
                fieldWithPath("sendCurrency").type(STRING).description("송금통화"),
                fieldWithPath("sendMoney").type(NUMBER).description("송금액"),
                fieldWithPath("senderName").type(STRING).description("송금자 이름"),
                fieldWithPath("senderEmail").type(STRING).description("송금자 이메일"),
                fieldWithPath("senderAddress1").type(STRING).description("송금자 주소1"),
                fieldWithPath("senderAddress2").type(STRING).description("송금자 주소2"),
                fieldWithPath("senderContactNumber").type(STRING).description("송금자 연락처"),
                fieldWithPath("senderIdentifyNumber").type(STRING).description("송금자 식별번호"),
                fieldWithPath("receiveCountry").type(STRING).description("수취국가"),
                fieldWithPath("receiveCurrency").type(STRING).description("수취통화"),
                fieldWithPath("receiverName").type(STRING).description("수취자 이름"),
                fieldWithPath("receiverEmail").type(STRING).description("수취자 이메일"),
                fieldWithPath("receiverAddress1").type(STRING).description("수취자 주소1"),
                fieldWithPath("receiverAddress2").type(STRING).description("수취자 주소2"),
                fieldWithPath("receiverContactNumber").type(STRING).description("수취자 연락처"),
                fieldWithPath("receiverIdentifyNumber").type(STRING).description("수취자 식별번호")
        };

        FieldDescriptor[] responseFields = {
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data").type(OBJECT).description("응답데이터"),
                fieldWithPath("data.tradeNumber").type(STRING).description("거래번호"),
                fieldWithPath("data.memberNumber").type(STRING).description("회원번호"),
                fieldWithPath("data.transferStatus").type(STRING).description("송금상태"),
                fieldWithPath("data.transferStatusDesc").type(STRING).description("송금상태 설명"),
                fieldWithPath("data.requestDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래요청일시"),
                fieldWithPath("data.inProgressDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래진행일시").optional(),
                fieldWithPath("data.canceledDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래완료일시").optional(),
                fieldWithPath("data.cancelReason").type(STRING).description("취소사유").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래취소일시").optional(),
                fieldWithPath("data.sendCountry").type(STRING).description("송금국가"),
                fieldWithPath("data.sendCurrency").type(STRING).description("송금통화"),
                fieldWithPath("data.sendMoney").type(NUMBER).description("송금액"),
                fieldWithPath("data.sendFxRate").type(NUMBER).description("송금 환율"),
                fieldWithPath("data.senderName").type(STRING).description("송금자 이름"),
                fieldWithPath("data.senderEmail").type(STRING).description("송금자 이메일"),
                fieldWithPath("data.senderContactNumber").type(STRING).description("송금자 연락처"),
                fieldWithPath("data.senderAddress1").type(STRING).description("송금자 주소1"),
                fieldWithPath("data.senderAddress2").type(STRING).description("송금자 주소2"),
                fieldWithPath("data.senderIdentifyNumber").type(STRING).description("송금자 식별번호(여권번호, 외국인번호 등)"),
                fieldWithPath("data.receiveCountry").type(STRING).description("수취국가"),
                fieldWithPath("data.receiveCurrency").type(STRING).description("수취통화"),
                fieldWithPath("data.receiveMoney").type(NUMBER).description("수취금액"),
                fieldWithPath("data.receiveFxRate").type(NUMBER).description("수취 환율"),
                fieldWithPath("data.receiverName").type(STRING).description("수취자 이름"),
                fieldWithPath("data.receiverEmail").type(STRING).description("수취자 이메일"),
                fieldWithPath("data.receiverContactNumber").type(STRING).description("수취자 연락처"),
                fieldWithPath("data.receiverAddress1").type(STRING).description("수취자 주소1"),
                fieldWithPath("data.receiverAddress2").type(STRING).description("수취자 주소2"),
                fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("수취자 식별번호(여권번호, 외국인번호 등)")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxTransferRestController)
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
                                headerWithName(Constant.HEADER_MEMBER_NUMBER).description("회원번호")
                        ),
                        requestFields(
                                requestFields
                        ),
                        responseFields(
                                responseFields
                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("post-v1-trade-send",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestHeaders(
                                                        headerWithName(Constant.HEADER_MEMBER_NUMBER).description("회원번호")
                                                )
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

    @Test
    @DisplayName("송금내역 조회")
    void testGetTradeHistory(RestDocumentationContextProvider contextProvider) throws Exception {
        String memberNumber = "20220724141501";
        String tradeNumber = UUID.randomUUID().toString();

        FxTransferHistory fxTransferHistory = FxTransferHistory.builder()
                .memberNumber(memberNumber)
                .sendCountry(Country.KOR)
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .sendFxRate(BigDecimal.valueOf(1321.55d))
                .senderName("송금자")
                .senderEmail("sender@fxprovider.com")
                .senderAddress1("강원도 동해시")
                .senderAddress2("어딘가")
                .senderContactNumber("+82-010-0000-0000")
                .senderIdentifyNumber("111111-1111111")
                .receiveCountry(Country.JPN)
                .receiveCurrency(Currency.JPY)
                .receiveMoney(BigDecimal.valueOf(999_999d))
                .receiverName("수취자")
                .receiverEmail("receiver@fxprovider.com")
                .receiverAddress1("오사카 어딘가")
                .receiverAddress2("어딘가")
                .receiverContactNumber("+81-0000-0000-0000")
                .receiverIdentifyNumber("1234-567-8910")
                .receiveFxRate(BigDecimal.valueOf(132.15d))
                .build();

        Mockito.when(fxTransferFacade.findByMemberNumberAndTradeNumber(anyString(), anyString()))
                        .thenReturn(FxTransferHistoryDto.of(fxTransferHistory));

        FieldDescriptor[] responseFields = {
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data").type(OBJECT).description("응답데이터"),
                fieldWithPath("data.tradeNumber").type(STRING).description("거래번호"),
                fieldWithPath("data.memberNumber").type(STRING).description("회원번호"),
                fieldWithPath("data.transferStatus").type(STRING).description("송금상태"),
                fieldWithPath("data.transferStatusDesc").type(STRING).description("송금상태 설명"),
                fieldWithPath("data.requestDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래요청일시"),
                fieldWithPath("data.inProgressDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래진행일시").optional(),
                fieldWithPath("data.canceledDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래완료일시").optional(),
                fieldWithPath("data.cancelReason").type(STRING).description("취소사유").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(zonedDateTimeFormat()).description("거래취소일시").optional(),
                fieldWithPath("data.sendCountry").type(STRING).description("송금국가"),
                fieldWithPath("data.sendCurrency").type(STRING).description("송금통화"),
                fieldWithPath("data.sendMoney").type(NUMBER).description("송금액"),
                fieldWithPath("data.sendFxRate").type(NUMBER).description("송금 환율"),
                fieldWithPath("data.senderName").type(STRING).description("송금자 이름"),
                fieldWithPath("data.senderEmail").type(STRING).description("송금자 이메일"),
                fieldWithPath("data.senderContactNumber").type(STRING).description("송금자 연락처"),
                fieldWithPath("data.senderAddress1").type(STRING).description("송금자 주소1"),
                fieldWithPath("data.senderAddress2").type(STRING).description("송금자 주소2"),
                fieldWithPath("data.senderIdentifyNumber").type(STRING).description("송금자 식별번호(여권번호, 외국인번호 등)"),
                fieldWithPath("data.receiveCountry").type(STRING).description("수취국가"),
                fieldWithPath("data.receiveCurrency").type(STRING).description("수취통화"),
                fieldWithPath("data.receiveMoney").type(NUMBER).description("수취금액"),
                fieldWithPath("data.receiveFxRate").type(NUMBER).description("수취 환율"),
                fieldWithPath("data.receiverName").type(STRING).description("수취자 이름"),
                fieldWithPath("data.receiverEmail").type(STRING).description("수취자 이메일"),
                fieldWithPath("data.receiverContactNumber").type(STRING).description("수취자 연락처"),
                fieldWithPath("data.receiverAddress1").type(STRING).description("수취자 주소1"),
                fieldWithPath("data.receiverAddress2").type(STRING).description("수취자 주소2"),
                fieldWithPath("data.receiverIdentifyNumber").type(STRING).description("수취자 식별번호(여권번호, 외국인번호 등)")};

        MockMvcFactory.getRestDocsMockMvc(contextProvider, fxTransferRestController)
                .perform(RestDocumentationRequestBuilders.get("/api/v1/trade/{tradeNumber}", tradeNumber)
                        .header(Constant.HEADER_MEMBER_NUMBER, memberNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("get-v1-trade-history",
                        RestDocumentationUtils.getDocumentRequest(),
                        RestDocumentationUtils.getDocumentResponse(),
                        requestHeaders(
                                headerWithName(Constant.HEADER_MEMBER_NUMBER).description("회원번호")
                        ),
                        pathParameters(
                                parameterWithName("tradeNumber").attributes(customFormat("STRING")).description("거래번호")
                        ),
                        responseFields(
                                responseFields
                        )))
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-trade-history",
                                RestDocumentationUtils.getDocumentRequest(),
                                RestDocumentationUtils.getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .requestHeaders(
                                                        headerWithName(Constant.HEADER_MEMBER_NUMBER).description("회원번호")
                                                )
                                                .pathParameters(
                                                        parameterWithName("tradeNumber").attributes(customFormat("STRING")).description("거래번호")
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