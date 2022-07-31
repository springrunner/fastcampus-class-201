package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @see <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/">Spring Cloud OpenFeign</a>
 */
public interface CurrencyLayerFeignClient {
    @GetMapping("/currency_data/live")
    public CurrencyLayerResponse getLiveCurrency(@RequestParam("source") Currency base, @RequestParam("currencies") Currency[] symbols);
}
