package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TradeHistoryCreator {
    private String memberNumber;
    private String senderName;
    private String senderEmail;
    private String senderContactNumber;
    private String senderAddress1;
    private String senderAddress2;
    private String senderIdentifyNumber;
    private Country sendCountry;
    private Currency sendCurrency;
    private BigDecimal sendRate;
    private BigDecimal sendMoney;
    private String receiverName;
    private String receiverEmail;
    private String receiverContactNumber;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverIdentifyNumber;
    private Country receiveCountry;
    private Currency receiveCurrency;
    private BigDecimal receiveRate;
    private BigDecimal receiveMoney;

    @Builder
    public TradeHistoryCreator(String memberNumber,
                               String senderName,
                               String senderEmail,
                               String senderContactNumber,
                               String senderAddress1,
                               String senderAddress2,
                               String senderIdentifyNumber,
                               Country sendCountry,
                               Currency sendCurrency,
                               BigDecimal sendRate,
                               BigDecimal sendMoney,
                               String receiverName,
                               String receiverEmail,
                               String receiverContactNumber,
                               String receiverAddress1,
                               String receiverAddress2,
                               String receiverIdentifyNumber,
                               Country receiveCountry,
                               Currency receiveCurrency,
                               BigDecimal receiveRate,
                               BigDecimal receiveMoney) {
        this.memberNumber = memberNumber;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContactNumber = senderContactNumber;
        this.senderAddress1 = senderAddress1;
        this.senderAddress2 = senderAddress2;
        this.senderIdentifyNumber = senderIdentifyNumber;
        this.sendCountry = sendCountry;
        this.sendCurrency = sendCurrency;
        this.sendRate = sendRate;
        this.sendMoney = sendMoney;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverContactNumber = receiverContactNumber;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.receiverIdentifyNumber = receiverIdentifyNumber;
        this.receiveCountry = receiveCountry;
        this.receiveCurrency = receiveCurrency;
        this.receiveRate = receiveRate;
        this.receiveMoney = receiveMoney;
    }

    public FxTransferHistory create() {
        return FxTransferHistory.builder()
                .senderName(getSenderName())
                .senderEmail(getSenderEmail())
                .senderContactNumber(getSenderContactNumber())
                .senderAddress1(getSenderAddress1())
                .senderAddress2(getSenderAddress2())
                .senderIdentifyNumber(getSenderIdentifyNumber())
                .sendCountry(getSendCountry())
                .sendCurrency(getSendCurrency())
                .sendFxRate(getSendRate())
                .sendMoney(getSendMoney())
                .receiverName(getReceiverName())
                .receiverEmail(getReceiverEmail())
                .receiverContactNumber(getReceiverContactNumber())
                .receiverAddress1(getReceiverAddress1())
                .receiverAddress2(getReceiverAddress2())
                .receiverIdentifyNumber(getReceiverIdentifyNumber())
                .receiveCountry(getReceiveCountry())
                .receiveCurrency(getReceiveCurrency())
                .receiveFxRate(getReceiveRate())
                .receiveMoney(getReceiveMoney())
                .build();
    }
}
