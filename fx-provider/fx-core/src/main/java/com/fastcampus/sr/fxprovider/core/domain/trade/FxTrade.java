package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;

import java.math.BigDecimal;

public class FxTrade {
    private Currency sendCurrency;
    private BigDecimal sendRate;
    private BigDecimal sendMoney;
    private Currency receiveCurrency;
    private BigDecimal receiveRate;
    private BigDecimal receiveMoney;

    public FxTrade(Currency sendCurrency,
                   BigDecimal sendRate,
                   BigDecimal sendMoney,
                   Currency receiveCurrency,
                   BigDecimal receiveRate,
                   BigDecimal receiveMoney) {
        this.sendCurrency = sendCurrency;
        this.sendRate = sendRate;
        this.sendMoney = sendMoney;
        this.receiveCurrency = receiveCurrency;
        this.receiveRate = receiveRate;
        this.receiveMoney = receiveMoney;
    }

    public static FxTrade of(Currency sendCurrency,
                             BigDecimal sendRate,
                             BigDecimal sendMoney,
                             Currency receiveCurrency,
                             BigDecimal receiveRate,
                             BigDecimal receiveMoney) {
        return new FxTrade(sendCurrency, sendRate, sendMoney, receiveCurrency, receiveRate, receiveMoney);
    }

    public Currency getSendCurrency() {
        return sendCurrency;
    }

    public BigDecimal getSendRate() {
        return sendRate;
    }

    public Currency getReceiveCurrency() {
        return receiveCurrency;
    }

    public BigDecimal getReceiveRate() {
        return receiveRate;
    }

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }
}
