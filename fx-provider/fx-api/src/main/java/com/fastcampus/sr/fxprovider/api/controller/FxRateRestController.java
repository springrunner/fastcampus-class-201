package com.fastcampus.sr.fxprovider.api.controller;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxCurrencyDto;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateCalculateRequest;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateCalculatedResponse;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateResponse;
import com.fastcampus.sr.fxprovider.api.service.FxRateQueryService;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FxRateRestController {
    private final FxRateQueryService fxRateQueryService;

    public FxRateRestController(FxRateQueryService fxRateQueryService) {
        this.fxRateQueryService = fxRateQueryService;
    }

    //환율정보제공
    @GetMapping("/api/v1/fx-rates")
    public FxRateResponse getFxRates(@RequestParam(name = "targetCurrency", required = false) Currency targetCurrency) {
        return new FxRateResponse(fxRateQueryService.getFxRate(targetCurrency));
    }

    //환율계산
    @PostMapping("/api/v1/fx-calculate")
    public FxRateCalculatedResponse calculateEx(@RequestBody FxRateCalculateRequest request) {
        return fxRateQueryService.calculate(request);
    }
}
