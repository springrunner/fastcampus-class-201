package com.fastcampus.sr.fxprovider.core.domain.currency;

import com.fastcampus.sr.fx.provider.core.annotation.InMemoryDBJpaTest;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.CoreTestConfiguration;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@InMemoryDBJpaTest
@ContextConfiguration(classes = {CoreTestConfiguration.class})
class FxCurrencyRateRepositoryTest {
    @Autowired
    private FxCurrencyRateRepository fxCurrencyRateRepository;

    @AfterEach
    void tearDown() {
        fxCurrencyRateRepository.deleteAll();
    }

    @Test
    void testSave() {
        //given
        FxCurrencyRate fxCurrencyRate = FxCurrencyRate.create(Currency.KRW, new BigDecimal("1300.55"));

        //when
        fxCurrencyRateRepository.save(fxCurrencyRate);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(fxCurrencyRate.getId()).isNotZero();
            softly.assertThat(fxCurrencyRate.getCurrency()).isEqualTo(Currency.KRW);
            softly.assertThat(fxCurrencyRate.getRate()).isEqualTo(new BigDecimal("1300.55"));
        });
    }
}