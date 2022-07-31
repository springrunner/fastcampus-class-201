package com.fastcampus.sr.fxprovider.collector.service;

import com.fastcampus.sr.fxprovider.clients.currency.layer.dto.CurrencyLayerResponse;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FxCurrencyRateService {
    private final FxCurrencyRateRepository repository;

    public FxCurrencyRateService(FxCurrencyRateRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public List<FxCurrencyRate> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void saveAll(List<FxCurrencyRate> fxCurrencyRates) {
        repository.saveAll(fxCurrencyRates);
    }

    @Transactional
    public void updateFxCurrencyRates(CurrencyLayerResponse getFxCurrencies) {
        var fxCurrencies = repository.findAll();

        for (FxCurrencyRate el : fxCurrencies) {
            if (getFxCurrencies.getCurrencyQuotes().containsKey(el.getCurrency())) {
                el.update(getFxCurrencies.getCurrencyQuotes().get(el.getCurrency()));
            }
        }

        repository.saveAll(fxCurrencies);
    }
}
