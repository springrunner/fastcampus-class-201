package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.repository.FxCurrencyRateQueryRepository;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyRateDto;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FxCurrencyRateManager implements FxCurrencyRateFinder, FxCurrencyRateEditor {
    private final FxCurrencyRateRepository fxCurrencyRateRepository;
    private final FxCurrencyRateQueryRepository fxCurrencyRateQueryRepository;

    public FxCurrencyRateManager(FxCurrencyRateRepository fxCurrencyRateRepository, FxCurrencyRateQueryRepository fxCurrencyRateQueryRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
        this.fxCurrencyRateQueryRepository = fxCurrencyRateQueryRepository;
    }

    @Override
    public Page<FxCurrencyRateDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        QueryResults<FxCurrencyRateDto> queryResults = fxCurrencyRateQueryRepository.search(searchOption, pageable);
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
