package com.fastcampus.sr.fxprovider.api;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Component
public class LocalCommandRunner implements CommandLineRunner {
    private final FxCurrencyRateRepository fxCurrencyRateRepository;

    public LocalCommandRunner(FxCurrencyRateRepository fxCurrencyRateRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<FxCurrencyRate> fxCurrencyRates = new ArrayList<>();
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.CNY, BigDecimal.valueOf(6.751304)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.EUR, BigDecimal.valueOf(0.987085)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1316.309794)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(138.346008)));

        fxCurrencyRateRepository.saveAll(fxCurrencyRates);
    }
}
