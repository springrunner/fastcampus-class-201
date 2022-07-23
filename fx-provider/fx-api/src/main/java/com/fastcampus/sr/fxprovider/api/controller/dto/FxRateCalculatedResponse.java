package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.trade.FxTrade;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRateCalculatedResponse {
    private Currency sendCurrency;
    private Double sendMoney;
    private Currency receiveCurrency;
    private Double expectReceiveMoney;

    public FxRateCalculatedResponse(Currency sendCurrency,
                                    Double sendMoney,
                                    Currency receiveCurrency,
                                    Double expectReceiveMoney) {
        this.sendCurrency = sendCurrency;
        this.sendMoney = sendMoney;
        this.receiveCurrency = receiveCurrency;
        this.expectReceiveMoney = expectReceiveMoney;
    }

    public static FxRateCalculatedResponse of(FxTrade fxTrade) {
        FxRateCalculatedResponse response = new FxRateCalculatedResponse();
        response.sendCurrency = fxTrade.getSendCurrency();
        response.receiveCurrency = fxTrade.getReceiveCurrency();
        response.expectReceiveMoney = fxTrade.getReceiveMoney().doubleValue();
        return response;
    }

    @Override
    public String toString() {
        return "FxRateCalculatedResponse{" +
                "sendCurrency=" + sendCurrency +
                ", sendMoney=" + sendMoney +
                ", receiveCurrency=" + receiveCurrency +
                ", expectReceiveMoney=" + expectReceiveMoney +
                '}';
    }
}
