package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.common.enums.Currency;

public class CurrencyLayerRestClient implements CurrencyLayerClient {
    private final CurrencyLayerFeignClient feignClient;

    public CurrencyLayerRestClient(CurrencyLayerFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public CurrencyLayerResponse getLiveCurrency() {
        return this.feignClient.getLiveCurrency(
        		Currency.USD, 
        		new Currency[]{Currency.KRW, Currency.CNY, Currency.EUR, Currency.JPY});
    }
}
