package com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxCurrencyRateDto {
    private Currency currency;
    private BigDecimal rate;

    public FxCurrencyRateDto(Currency currency, BigDecimal rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public static FxCurrencyRateDto of(FxCurrencyRate source) {
        return new FxCurrencyRateDto(source.getCurrency(), source.getRate());
    }

    @Override
    public String toString() {
        return "FxCurrencyDto{" +
                "currency=" + currency +
                ", rate=" + rate +
                '}';
    }
}
