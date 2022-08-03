package com.fastcampus.sr.fxprovider.api;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.enums.MarginType;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMarginRepository;
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
    private final FxMarginRepository fxMarginRepository;

    public LocalCommandRunner(FxCurrencyRateRepository fxCurrencyRateRepository, FxMarginRepository fxMarginRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
        this.fxMarginRepository = fxMarginRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initFxCurrencyRates();
        initFxMargins();
    }

    private void initFxMargins() {
        List<FxMargin> margins = new ArrayList<>();
        margins.add(FxMargin.builder().thresholdMin(BigDecimal.valueOf(1)).thresholdMax(BigDecimal.valueOf(100))
                .marginType(MarginType.FIX).marginValue(BigDecimal.valueOf(1)).build());
        fxMarginRepository.saveAll(margins);
    }

    private void initFxCurrencyRates() {
        List<FxCurrencyRate> fxCurrencyRates = new ArrayList<>();
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.CNY, BigDecimal.valueOf(6.751304)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.EUR, BigDecimal.valueOf(0.987085)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1316.309794)));
        fxCurrencyRates.add(FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(138.346008)));

        fxCurrencyRateRepository.saveAll(fxCurrencyRates);
    }
}
