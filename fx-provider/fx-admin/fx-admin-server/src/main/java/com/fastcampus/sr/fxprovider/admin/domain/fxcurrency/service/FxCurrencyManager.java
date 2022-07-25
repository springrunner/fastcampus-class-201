package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.repository.FxCurrencyQueryRepository;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyRateDto;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FxCurrencyManager implements FxCurrencyFinder, FxCurrencyEditor {
    private final FxCurrencyRateRepository fxCurrencyRateRepository;
    private final FxCurrencyQueryRepository fxCurrencyQueryRepository;

    public FxCurrencyManager(FxCurrencyRateRepository fxCurrencyRateRepository, FxCurrencyQueryRepository fxCurrencyQueryRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
        this.fxCurrencyQueryRepository = fxCurrencyQueryRepository;
    }

    @Override
    public Page<FxCurrencyRateDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        QueryResults<FxCurrencyRateDto> queryResults = fxCurrencyQueryRepository.search(searchOption, pageable);
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
