package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxTradeSendResponse {
    private String senderName;
    private String senderEmail;
    private String senderContactNumber;
    private String senderAddress1;
    private String senderAddress2;
    private String senderIdentifyNumber;

    private Currency sendCurrency;
    private Double sendMoney;

    private String receiverName;
    private String receiverEmail;
    private String receiverContactNumber;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverIdentifyNumber;

    private Currency receiveCurrency;
    private Double receiveMoney;

    public FxTradeSendResponse(String senderName,
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
                               Currency receiveCurrency,
                               Double receiveMoney) {
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
        this.receiveMoney = receiveMoney;
    }

    public static FxTradeSendResponse of(TradeHistory source){
        FxTradeSendResponse response = new FxTradeSendResponse();
        response.senderName = source.getSenderName();
        response.senderEmail = source.getSenderEmail();
        response.senderContactNumber = source.getSenderContactNumber();
        response.senderAddress1 = source.getSenderAddress1();
        response.senderAddress2 = source.getSenderAddress2();
        response.senderIdentifyNumber = source.getSenderIdentifyNumber();
        response.sendCurrency = source.getSendCurrency();
        response.sendMoney = source.getSendMoney();

        response.receiverName = source.getReceiverName();
        response.receiverEmail = source.getReceiverEmail();
        response.receiverContactNumber = source.getReceiverContactNumber();
        response.receiverAddress1 = source.getReceiverAddress1();
        response.receiverAddress2 = source.getReceiverAddress2();
        response.receiverIdentifyNumber = source.getReceiverIdentifyNumber();
        response.receiveCurrency = source.getReceiveCurrency();
        response.receiveMoney = source.getReceiveMoney();

        return response;
    }
}
