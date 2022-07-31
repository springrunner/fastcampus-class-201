package com.fastcampus.sr.fxprovider.collector.service;

import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerClient;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FxCurrencyRateFacade {
    private final CurrencyLayerClient currencyLayerClient;
    private final FxCurrencyRateService fxCurrencyRateService;

    public FxCurrencyRateFacade(CurrencyLayerClient currencyLayerClient, FxCurrencyRateService fxCurrencyRateService) {
        this.currencyLayerClient = currencyLayerClient;
        this.fxCurrencyRateService = fxCurrencyRateService;
    }

    @PostConstruct
    public void setUp() {
        var currencyLayerResponse = currencyLayerClient.getLiveCurrency();
        var fxCurrencies = fxCurrencyRateService.findAll();

        Map<Currency, BigDecimal> fxCurrenciesMap = fxCurrencies.stream()
                .collect(Collectors.toMap(FxCurrencyRate::getCurrency, FxCurrencyRate::getRate));

        List<FxCurrencyRate> newFxCurrencies = new ArrayList<>();
        for (Currency currency : currencyLayerResponse.getCurrencyQuotes().keySet()) {
            if (!fxCurrenciesMap.containsKey(currency)) {
                newFxCurrencies.add(FxCurrencyRate.create(currency, currencyLayerResponse.getCurrencyQuotes().get(currency)));
            }
        }

        if (!newFxCurrencies.isEmpty()) {
            log.debug("Save new FxCurrencies: {}", newFxCurrencies.size());
            fxCurrencyRateService.saveAll(newFxCurrencies);
        }
    }

    public void updateFxCurrencyRates() {
        var getFxCurrencies = currencyLayerClient.getLiveCurrency();
        fxCurrencyRateService.updateFxCurrencyRates(getFxCurrencies);
    }
}
