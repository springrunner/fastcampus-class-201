package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;

/**
 * @see <a href="https://apilayer.com/marketplace/currency_data-api#documentation-tab">Currency Data API</a>
 */
public interface CurrencyLayerClient {
    /**
     * 현재 환율정보 조회
     *
     * @return 환율정보 조회
     */
    CurrencyLayerResponse getLiveCurrency();
}
