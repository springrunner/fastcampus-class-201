package com.fastcampus.sr.fxprovider.api.service;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxCurrencyDto;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateCalculateRequest;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxRateCalculatedResponse;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.common.exception.NotFoundFxCurrencyException;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrencyRepository;
import com.fastcampus.sr.fxprovider.core.trade.FxRateCalculator;
import com.fastcampus.sr.fxprovider.core.trade.FxTrade;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
public class FxRateQueryService {
    private final FxCurrencyRepository fxCurrencyRepository;

    public FxRateQueryService(FxCurrencyRepository fxCurrencyRepository) {
        this.fxCurrencyRepository = fxCurrencyRepository;
    }

    public List<FxCurrencyDto> getFxRate(@Nullable Currency targetCurrency) {
        List<FxCurrency> fxCurrencies = fxCurrencyRepository.findAll();

        if (Objects.nonNull(targetCurrency)) {
            return Arrays.asList(fxCurrencies.stream()
                    .filter(el -> el.getCurrency() == targetCurrency)
                    .findAny()
                    .map(FxCurrencyDto::of)
                    .orElseThrow(NotFoundFxCurrencyException::new));
        }

        return fxCurrencies.stream()
                .map(FxCurrencyDto::of)
                .collect(Collectors.toList());
    }

    public FxTrade calculateFx(FxRateCalculateRequest request) {
        List<FxCurrency> fxCurrencies = fxCurrencyRepository.findAll();

        FxTrade result = FxRateCalculator.calculate(fxCurrencies,
                request.getSendCurrency(),
                request.getSendMoney(),
                request.getReceiveCurrency());

        return result;
    }
}
