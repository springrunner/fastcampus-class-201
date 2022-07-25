package com.fastcampus.sr.fxprovider.api.domain.fxrate.service;

import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto.FxCurrencyRateDto;
import com.fastcampus.sr.fxprovider.api.domain.fxrate.controller.dto.FxMoneyCalculateRequest;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.exception.NotFoundFxCurrencyException;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxRateCalculator;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
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
    private final FxCurrencyRateRepository fxCurrencyRateRepository;

    public FxRateQueryService(FxCurrencyRateRepository fxCurrencyRateRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
    }

    public List<FxCurrencyRateDto> getFxRate(@Nullable Currency targetCurrency) {
        List<FxCurrencyRate> fxCurrencies = fxCurrencyRateRepository.findAll();

        if (Objects.nonNull(targetCurrency)) {
            return Arrays.asList(fxCurrencies.stream()
                    .filter(el -> el.getCurrency() == targetCurrency)
                    .findAny()
                    .map(FxCurrencyRateDto::of)
                    .orElseThrow(NotFoundFxCurrencyException::new));
        }

        return fxCurrencies.stream()
                .map(FxCurrencyRateDto::of)
                .collect(Collectors.toList());
    }

    public FxMoneyDto calculateFxMoney(FxMoneyCalculateRequest request) {
        List<FxCurrencyRate> fxCurrencies = fxCurrencyRateRepository.findAll();

        FxMoneyDto result = FxRateCalculator.calculate(
                fxCurrencies,
                request.getSendCurrency(),
                request.getSendMoney(),
                request.getReceiveCurrency());

        return result;
    }
}
