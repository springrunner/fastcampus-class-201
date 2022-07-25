package com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto;

import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto.FxCurrencyRateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxRateResponse {
    private List<FxCurrencyRateDto> fxCurrencies;

    public FxRateResponse(List<FxCurrencyRateDto> fxCurrencies) {
        this.fxCurrencies = fxCurrencies;
    }
}
