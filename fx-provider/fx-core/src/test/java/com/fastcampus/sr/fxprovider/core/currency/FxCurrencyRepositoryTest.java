package com.fastcampus.sr.fxprovider.core.currency;

import com.fastcampus.sr.fx.provider.core.annotation.InMemoryDBJpaTest;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@InMemoryDBJpaTest
@ContextConfiguration(classes = {CoreTestConfiguration.class})
class FxCurrencyRepositoryTest {
    @Autowired
    private FxCurrencyRepository fxCurrencyRepository;

    @AfterEach
    void tearDown() {
        fxCurrencyRepository.deleteAll();
    }

    @Test
    void testSave() {
        //given
        FxCurrency fxCurrency = FxCurrency.create(Currency.KRW, 1300.55d);

        //when
        fxCurrencyRepository.save(fxCurrency);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(fxCurrency.getId()).isNotZero();
            softly.assertThat(fxCurrency.getCurrency()).isEqualTo(Currency.KRW);
            softly.assertThat(fxCurrency.getRate()).isEqualTo(1300.55d);
        });
    }
}