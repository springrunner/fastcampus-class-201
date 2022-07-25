package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
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
    private Currency sendCurrency;
    private BigDecimal sendMoney;
    private String receiverName;
    private String receiverEmail;
    private String receiverContactNumber;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverIdentifyNumber;
    private Currency receiveCurrency;

    @Builder
    public TradeHistoryCreator(
            String memberNumber,
            String senderName,
            String senderEmail,
            String senderContactNumber,
            String senderAddress1,
            String senderAddress2,
            String senderIdentifyNumber,
            Currency sendCurrency,
            BigDecimal sendMoney,
            String receiverName,
            String receiverEmail,
            String receiverContactNumber,
            String receiverAddress1,
            String receiverAddress2,
            String receiverIdentifyNumber,
            Currency receiveCurrency) {
        this.memberNumber = memberNumber;
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

    public TradeHistory create(List<FxCurrency> fxCurrencies) {
        FxTrade fxTrade = FxRateCalculator.calculate(fxCurrencies, sendCurrency, sendMoney, receiveCurrency);

        return TradeHistory.builder()
                .senderName(getSenderName())
                .senderEmail(getSenderEmail())
                .senderContactNumber(getSenderContactNumber())
                .senderAddress1(getSenderAddress1())
                .senderAddress2(getSenderAddress2())
                .senderIdentifyNumber(getSenderIdentifyNumber())
                .sendCurrency(getSendCurrency())
                .sendFxRate(fxTrade.getSendRate())
                .sendMoney(getSendMoney())
                .receiverName(getReceiverName())
                .receiverEmail(getReceiverEmail())
                .receiverContactNumber(getReceiverContactNumber())
                .receiverAddress1(getReceiverAddress1())
                .receiverAddress2(getReceiverAddress2())
                .receiverIdentifyNumber(getReceiverIdentifyNumber())
                .receiveCurrency(getReceiveCurrency())
                .receiveFxRate(fxTrade.getReceiveRate())
                .receiveMoney(fxTrade.getReceiveMoney())
                .build();
    }
}
