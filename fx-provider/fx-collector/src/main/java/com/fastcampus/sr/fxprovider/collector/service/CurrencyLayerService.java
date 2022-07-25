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
public class CurrencyLayerService {
    private final CurrencyLayerClient currencyLayerClient;
    private final FxCurrencyRateRepository fxCurrencyRateRepository;

    public CurrencyLayerService(CurrencyLayerClient currencyLayerClient, FxCurrencyRateRepository fxCurrencyRateRepository) {
        this.currencyLayerClient = currencyLayerClient;
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
    }

    @PostConstruct
    @Transactional
    public void setUp() {
        var currencyLayerResponse = currencyLayerClient.getLiveCurrency();
        var fxCurrencies = fxCurrencyRateRepository.findAll();

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
            fxCurrencyRateRepository.saveAll(newFxCurrencies);
        }
    }

    @Transactional
    public void updateFxCurrencies() {
        var response = currencyLayerClient.getLiveCurrency();
        var fxCurrencies = fxCurrencyRateRepository.findAll();

        for (FxCurrencyRate el : fxCurrencies) {
            if (response.getCurrencyQuotes().containsKey(el.getCurrency())) {
                el.update(response.getCurrencyQuotes().get(el.getCurrency()));
            }
        }
    }
}
