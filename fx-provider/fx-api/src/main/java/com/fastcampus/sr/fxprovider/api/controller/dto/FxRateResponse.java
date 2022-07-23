package com.fastcampus.sr.fxprovider.api.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRateResponse {
    private List<FxCurrencyDto> fxCurrencies;

    public FxRateResponse(List<FxCurrencyDto> fxCurrencies) {
        this.fxCurrencies = fxCurrencies;
    }
}
