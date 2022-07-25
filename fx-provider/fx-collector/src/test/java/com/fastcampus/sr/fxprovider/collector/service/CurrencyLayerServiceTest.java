package com.fastcampus.sr.fxprovider.collector.service;

import com.fastcampus.sr.fx.provider.core.annotation.IntegrationTest;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("기능테스트 완료")
@IntegrationTest
class CurrencyLayerServiceTest {
    @Autowired
    FxCurrencyRateRepository fxCurrencyRateRepository;
    @Autowired
    CurrencyLayerService currencyLayerService;

    @Test
    void testUpdateFxCurrency() {
        //when
        currencyLayerService.updateFxCurrencies();

        //expect
        List<FxCurrencyRate> fxCurrencies = fxCurrencyRateRepository.findAll();
        assertThat(fxCurrencies).hasSize(4);
    }
}