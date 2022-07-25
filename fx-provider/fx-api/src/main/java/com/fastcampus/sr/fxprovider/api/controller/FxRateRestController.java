package com.fastcampus.sr.fxprovider.api.controller;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxMoneyCalculateRequest;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateCalculatedResponse;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateResponse;
import com.fastcampus.sr.fxprovider.api.service.FxRateQueryService;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import org.springframework.web.bind.annotation.*;

@RestController
public class FxRateRestController {
    private final FxRateQueryService fxRateQueryService;

    public FxRateRestController(FxRateQueryService fxRateQueryService) {
        this.fxRateQueryService = fxRateQueryService;
    }


    /**
     * 환율정보 제공
     *
     * @param targetCurrency 환율탐색 통화
     * @return 통화별 환율(달러기준)
     */
    @GetMapping("/api/v1/fx-rates")
    public FxRateResponse getFxRates(@RequestParam(name = "targetCurrency", required = false) Currency targetCurrency) {
        return new FxRateResponse(fxRateQueryService.getFxRate(targetCurrency));
    }

    /**
     * 환율계산
     *
     * @param request 송금통화 및 송금액
     * @return 예상 수취금액
     */
    @PostMapping("/api/v1/fx-calculate")
    public FxRateCalculatedResponse calculateFx(@RequestBody FxMoneyCalculateRequest request) {
        return FxRateCalculatedResponse.of(fxRateQueryService.calculateFxMoney(request));
    }
}
