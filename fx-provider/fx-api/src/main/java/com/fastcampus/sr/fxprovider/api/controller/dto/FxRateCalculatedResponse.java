package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRateCalculatedResponse {
    private Currency sendCurrency;
    private BigDecimal sendMoney;
    private Currency receiveCurrency;
    private BigDecimal expectReceiveMoney;

    public FxRateCalculatedResponse(Currency sendCurrency,
                                    BigDecimal sendMoney,
                                    Currency receiveCurrency,
                                    BigDecimal expectReceiveMoney) {
        this.sendCurrency = sendCurrency;
        this.sendMoney = sendMoney;
        this.receiveCurrency = receiveCurrency;
        this.expectReceiveMoney = expectReceiveMoney;
    }

    public static FxRateCalculatedResponse of(FxMoneyDto fxMoneyDto) {
        FxRateCalculatedResponse response = new FxRateCalculatedResponse();
        response.sendCurrency = fxMoneyDto.getSendCurrency();
        response.sendMoney = fxMoneyDto.getSendMoney();
        response.receiveCurrency = fxMoneyDto.getReceiveCurrency();
        response.expectReceiveMoney = fxMoneyDto.getReceiveMoney();
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
