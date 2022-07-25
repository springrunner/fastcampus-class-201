package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxMoneyCalculateRequest {
    @NotNull
    private Currency sendCurrency;
    @NotNull
    private BigDecimal sendMoney;
    @NotNull
    private Currency receiveCurrency;

    @Builder
    public FxMoneyCalculateRequest(Currency sendCurrency, BigDecimal sendMoney, Currency receiveCurrency) {
        this.sendCurrency = sendCurrency;
        this.sendMoney = sendMoney;
        this.receiveCurrency = receiveCurrency;
    }

    @Override
    public String toString() {
        return "FxRateCalculateRequest{" +
                "sendCurrency=" + sendCurrency +
                ", sendMoney=" + sendMoney +
                ", receiveCurrency=" + receiveCurrency +
                '}';
    }
}
