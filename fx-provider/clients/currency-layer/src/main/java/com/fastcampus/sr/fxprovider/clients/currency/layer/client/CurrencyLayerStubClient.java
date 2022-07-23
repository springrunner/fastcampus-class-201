package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.common.currency.Currency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class CurrencyLayerStubClient implements CurrencyLayerClient {
    @Override
    public CurrencyLayerResponse getLiveCurrency() {
        CurrencyLayerResponse currencyLayerResponse = new CurrencyLayerResponse();
        currencyLayerResponse.setSuccess(true);
        currencyLayerResponse.setTimestamp(new Date().getTime());
        currencyLayerResponse.setSource(Currency.USD);
        currencyLayerResponse.setQuotes(Map.of(
                "USDEUR", 0.987085,
                "USDJPY", 138.346008,
                "USDCNY", 6.751304,
                "USDKRW", 1316.309794));

        return currencyLayerResponse;
    }
}
