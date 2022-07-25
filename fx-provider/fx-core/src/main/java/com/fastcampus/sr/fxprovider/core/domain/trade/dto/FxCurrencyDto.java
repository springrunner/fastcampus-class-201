package com.fastcampus.sr.fxprovider.core.domain.trade.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxCurrencyDto {
    private Currency currency;
    private BigDecimal rate;

    public FxCurrencyDto(Currency currency, BigDecimal rate) {
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
