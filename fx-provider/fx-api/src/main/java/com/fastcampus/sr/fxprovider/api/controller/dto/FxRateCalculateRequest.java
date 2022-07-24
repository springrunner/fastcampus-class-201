package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRateCalculateRequest {
    @NotNull
    private Currency sendCurrency;
    @NotNull
    private Double sendMoney;
    @NotNull
    private Currency receiveCurrency;

    @Builder
    public FxRateCalculateRequest(Currency sendCurrency, Double sendMoney, Currency receiveCurrency) {
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
