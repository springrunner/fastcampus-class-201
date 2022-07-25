package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CurrencyLayerFeignClient {
    @GetMapping("/currency_data/live")
    public CurrencyLayerResponse getLiveCurrency(@RequestParam("source") Currency base, @RequestParam("currencies") Currency[] symbols);
}
