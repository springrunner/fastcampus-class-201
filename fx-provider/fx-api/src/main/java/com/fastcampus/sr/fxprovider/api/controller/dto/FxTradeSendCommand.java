package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistoryCreator;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxTradeSendCommand {
    @NotEmpty
    private String senderName;
    @NotEmpty
    private String senderEmail;
    @NotEmpty
    private String senderContactNumber;
    @NotEmpty
    private String senderAddress1;
    private String senderAddress2;
    @NotEmpty
    private String senderIdentifyNumber;

    @NotNull
    private Currency sendCurrency;
    @NotNull
    private Double sendMoney;

    @NotEmpty
    private String receiverName;
    @NotEmpty
    private String receiverEmail;
    @NotEmpty
    private String receiverContactNumber;
    @NotEmpty
    private String receiverAddress1;
    private String receiverAddress2;
    @NotEmpty
    private String receiverIdentifyNumber;

    @NotNull
    private Currency receiveCurrency;

    @Builder
    public FxTradeSendCommand(String senderName,
                              String senderEmail,
                              String senderContactNumber,
                              String senderAddress1,
                              String senderAddress2,
                              String senderIdentifyNumber,
                              Currency sendCurrency,
                              Double sendMoney,
                              String receiverName,
                              String receiverEmail,
                              String receiverContactNumber,
                              String receiverAddress1,
                              String receiverAddress2,
                              String receiverIdentifyNumber,
                              Currency receiveCurrency) {
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContactNumber = senderContactNumber;
        this.senderAddress1 = senderAddress1;
        this.senderAddress2 = senderAddress2;
        this.senderIdentifyNumber = senderIdentifyNumber;
        this.sendCurrency = sendCurrency;
        this.sendMoney = sendMoney;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverContactNumber = receiverContactNumber;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.receiverIdentifyNumber = receiverIdentifyNumber;
        this.receiveCurrency = receiveCurrency;
    }

    public TradeHistoryCreator createCreator() {
        return TradeHistoryCreator.builder()
                .senderName(getSenderName())
                .senderEmail(getSenderEmail())
                .senderContactNumber(getSenderContactNumber())
                .senderAddress1(getSenderAddress1())
                .senderAddress2(getSenderAddress2())
                .senderIdentifyNumber(getSenderIdentifyNumber())
                .sendCurrency(getSendCurrency())
                .sendMoney(getSendMoney())
                .receiverName(getReceiverName())
                .receiverEmail(getReceiverEmail())
                .receiverContactNumber(getReceiverContactNumber())
                .receiverAddress1(getReceiverAddress1())
                .receiverAddress2(getReceiverAddress2())
                .receiverIdentifyNumber(getReceiverIdentifyNumber())
                .receiveCurrency(getReceiveCurrency())
                .build();
    }
}