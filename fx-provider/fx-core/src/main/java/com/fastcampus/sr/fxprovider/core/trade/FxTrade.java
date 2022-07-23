package com.fastcampus.sr.fxprovider.core.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;

import java.math.BigDecimal;

public class FxTrade {
    private Currency sendCurrency;
    private Double sendRate;
    private Currency receiveCurrency;
    private Double receiveRate;
    private Double receiveMoney;

    public FxTrade(Currency sendCurrency, Double sendRate, Currency receiveCurrency, Double receiveRate, Double receiveMoney) {
        this.sendCurrency = sendCurrency;
        this.sendRate = sendRate;
        this.receiveCurrency = receiveCurrency;
        this.receiveRate = receiveRate;
        this.receiveMoney = receiveMoney;
    }

    public static FxTrade of(Currency sendCurrency, Double sendRate, Currency receiveCurrency, Double receiveRate, Double receiveMoney) {
        return new FxTrade(sendCurrency, sendRate, receiveCurrency, receiveRate, receiveMoney);
    }

    public Currency getSendCurrency() {
        return sendCurrency;
    }

    public Double getSendRate() {
        return sendRate;
    }

    public Currency getReceiveCurrency() {
        return receiveCurrency;
    }

    public Double getReceiveRate() {
        return receiveRate;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }
}
