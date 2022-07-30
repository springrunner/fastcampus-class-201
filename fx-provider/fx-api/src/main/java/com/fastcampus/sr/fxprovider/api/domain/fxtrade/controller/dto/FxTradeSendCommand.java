package com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto;

import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryCreator;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxTradeSendCommand {
    @NotEmpty
    private String senderName;
    @NotEmpty
    @Email
    private String senderEmail;
    @NotEmpty
    private String senderContactNumber;
    @NotEmpty
    private String senderAddress1;
    private String senderAddress2;
    @NotEmpty
    private String senderIdentifyNumber;

    @NotNull
    private Country sendCountry;
    @NotNull
    private Currency sendCurrency;
    @NotNull
    private BigDecimal sendMoney;

    @NotEmpty
    private String receiverName;
    @NotEmpty
    @Email
    private String receiverEmail;
    @NotEmpty
    private String receiverContactNumber;
    @NotEmpty
    private String receiverAddress1;
    private String receiverAddress2;
    @NotEmpty
    private String receiverIdentifyNumber;

    @NotNull
    private Country receiveCountry;
    @NotNull
    private Currency receiveCurrency;

    @Builder
    public FxTradeSendCommand(
            String senderName,
            String senderEmail,
            String senderContactNumber,
            String senderAddress1,
            String senderAddress2,
            String senderIdentifyNumber,
            Country sendCountry,
            Currency sendCurrency,
            BigDecimal sendMoney,
            String receiverName,
            String receiverEmail,
            String receiverContactNumber,
            String receiverAddress1,
            String receiverAddress2,
            String receiverIdentifyNumber,
            Country receiveCountry,
            Currency receiveCurrency) {
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContactNumber = senderContactNumber;
        this.senderAddress1 = senderAddress1;
        this.senderAddress2 = senderAddress2;
        this.senderIdentifyNumber = senderIdentifyNumber;
        this.sendCountry = sendCountry;
        this.sendCurrency = sendCurrency;
        this.sendMoney = sendMoney;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverContactNumber = receiverContactNumber;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.receiverIdentifyNumber = receiverIdentifyNumber;
        this.receiveCountry = receiveCountry;
        this.receiveCurrency = receiveCurrency;
    }

    public TradeHistoryCreator createCreator(String memberNumber, FxMoneyDto fxMoneyDto) {
        return TradeHistoryCreator.builder()
                .memberNumber(memberNumber)
                .senderName(getSenderName())
                .senderEmail(getSenderEmail())
                .senderContactNumber(getSenderContactNumber())
                .senderAddress1(getSenderAddress1())
                .senderAddress2(getSenderAddress2())
                .senderIdentifyNumber(getSenderIdentifyNumber())
                .sendCountry(getSendCountry())
                .sendCurrency(getSendCurrency())
                .sendRate(fxMoneyDto.getSendRate())
                .sendMoney(getSendMoney())
                .receiverName(getReceiverName())
                .receiverEmail(getReceiverEmail())
                .receiverContactNumber(getReceiverContactNumber())
                .receiverAddress1(getReceiverAddress1())
                .receiverAddress2(getReceiverAddress2())
                .receiverIdentifyNumber(getReceiverIdentifyNumber())
                .receiveCountry(getReceiveCountry())
                .receiveCurrency(getReceiveCurrency())
                .receiveRate(fxMoneyDto.getReceiveRate())
                .receiveMoney(fxMoneyDto.getReceiveMoney())
                .build();
    }
}
