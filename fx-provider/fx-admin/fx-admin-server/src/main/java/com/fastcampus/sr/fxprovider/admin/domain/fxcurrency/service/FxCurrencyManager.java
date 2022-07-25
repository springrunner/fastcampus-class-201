package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.repository.FxCurrencyQueryRepository;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyDto;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FxCurrencyManager implements FxCurrencyFinder, FxCurrencyEditor {
    private final FxCurrencyRepository fxCurrencyRepository;
    private final FxCurrencyQueryRepository fxCurrencyQueryRepository;

    public FxCurrencyManager(FxCurrencyRepository fxCurrencyRepository, FxCurrencyQueryRepository fxCurrencyQueryRepository) {
        this.fxCurrencyRepository = fxCurrencyRepository;
        this.fxCurrencyQueryRepository = fxCurrencyQueryRepository;
    }

    @Override
    public Page<FxCurrencyDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        QueryResults<FxCurrencyDto> queryResults = fxCurrencyQueryRepository.search(searchOption, pageable);
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
