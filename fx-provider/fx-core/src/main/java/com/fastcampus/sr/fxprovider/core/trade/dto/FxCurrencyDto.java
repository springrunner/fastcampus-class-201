package com.fastcampus.sr.fxprovider.core.trade.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxCurrencyDto {
    private Currency currency;
    private Double rate;

    public FxCurrencyDto(Currency currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public static FxCurrencyDto of(FxCurrency source) {
        return new FxCurrencyDto(source.getCurrency(), source.getRate());
    }

    @Override
    public String toString() {
        return "FxCurrencyDto{" +
                "currency=" + currency +
                ", rate=" + rate +
                '}';
    }
}
