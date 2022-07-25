package com.fastcampus.sr.fxprovider.clients.currency.layer.client;

import com.fastcampus.sr.fxprovider.clients.currency.layer.config.CurrencyLayerClientConfig;
import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled("테스트 완료")
@ActiveProfiles({"currency-layer", "currency-layer-dev"})
@SpringBootTest(classes = {HttpMessageConvertersAutoConfiguration.class, CurrencyLayerClientConfig.class})
class DefaultCurrencyLayerClientTest {
    @Autowired
    private CurrencyLayerClient currencyLayerClient;

    @Test
    void testGetLiveCurrency() {
        CurrencyLayerResponse response = currencyLayerClient.getLiveCurrency();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getTimestamp()).isNotZero();
            softly.assertThat(response.getSource()).isEqualTo(Currency.USD);
            softly.assertThat(response.getQuotes()).hasSize(4);
        });
    }
}