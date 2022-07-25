package com.fastcampus.sr.fxprovider.core.domain.currency;

import com.fastcampus.sr.fx.provider.core.annotation.InMemoryDBJpaTest;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.CoreTestConfiguration;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

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
        FxCurrency fxCurrency = FxCurrency.create(Currency.KRW, new BigDecimal("1300.55"));

        //when
        fxCurrencyRepository.save(fxCurrency);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(fxCurrency.getId()).isNotZero();
            softly.assertThat(fxCurrency.getCurrency()).isEqualTo(Currency.KRW);
            softly.assertThat(fxCurrency.getRate()).isEqualTo(new BigDecimal("1300.55"));
        });
    }
}