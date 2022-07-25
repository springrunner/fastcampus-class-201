package com.fastcampus.sr.fxprovider.collector.service;

import com.fastcampus.sr.fxprovider.clients.currency.layer.client.CurrencyLayerClient;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRepository;
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
    private final FxCurrencyRepository fxCurrencyRepository;

    public CurrencyLayerService(CurrencyLayerClient currencyLayerClient, FxCurrencyRepository fxCurrencyRepository) {
        this.currencyLayerClient = currencyLayerClient;
        this.fxCurrencyRepository = fxCurrencyRepository;
    }

    @PostConstruct
    @Transactional
    public void setUp() {
        var currencyLayerResponse = currencyLayerClient.getLiveCurrency();
        var fxCurrencies = fxCurrencyRepository.findAll();

        Map<Currency, BigDecimal> fxCurrenciesMap = fxCurrencies.stream()
                .collect(Collectors.toMap(FxCurrency::getCurrency, FxCurrency::getRate));

        List<FxCurrency> newFxCurrencies = new ArrayList<>();
        for (Currency currency : currencyLayerResponse.getCurrencyQuotes().keySet()) {
            if (!fxCurrenciesMap.containsKey(currency)) {
                newFxCurrencies.add(FxCurrency.create(currency, currencyLayerResponse.getCurrencyQuotes().get(currency)));
            }
        }

        if (!newFxCurrencies.isEmpty()) {
            log.debug("Save new FxCurrencies: {}", newFxCurrencies.size());
            fxCurrencyRepository.saveAll(newFxCurrencies);
        }
    }

    @Transactional
    public void updateFxCurrencies() {
        var response = currencyLayerClient.getLiveCurrency();
        var fxCurrencies = fxCurrencyRepository.findAll();

        for (FxCurrency el : fxCurrencies) {
            if (response.getCurrencyQuotes().containsKey(el.getCurrency())) {
                el.update(response.getCurrencyQuotes().get(el.getCurrency()));
            }
        }
    }
}
