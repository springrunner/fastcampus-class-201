package com.fastcampus.sr.fxprovider.clients.currency.layer.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static com.fastcampus.sr.fxprovider.common.currency.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyLayerResponseTest {

    private CurrencyLayerResponse currencyLayerResponse;

    @BeforeEach
    void setUp() {
        currencyLayerResponse = new CurrencyLayerResponse();
        currencyLayerResponse.setSuccess(true);
        currencyLayerResponse.setTimestamp(new Date().getTime());
        currencyLayerResponse.setSource(Currency.USD);
        currencyLayerResponse.setQuotes(Map.of(
                "USDEUR", 0.987085,
                "USDJPY", 138.346008,
                "USDKRW", 138.346008));
    }

    @Test
    void testGetBase() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(currencyLayerResponse.getCurrencyQuotes().get(EUR)).isEqualTo(0.987085);
            softAssertions.assertThat(currencyLayerResponse.getCurrencyQuotes().get(JPY)).isEqualTo(138.346008);
            softAssertions.assertThat(currencyLayerResponse.getCurrencyQuotes().get(KRW)).isEqualTo(138.346008);
        });
    }

    @Test
    @DisplayName("Currency 에 등록되지 않은 통화인 경우 오류 발생")
    void testNotSupportCurrencyOccurException() {
        currencyLayerResponse.setQuotes(Map.of(
                "USD_UNKNOWN", 1316.309794));

        assertThatThrownBy(() -> currencyLayerResponse.getCurrencyQuotes())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 통화");
    }
}