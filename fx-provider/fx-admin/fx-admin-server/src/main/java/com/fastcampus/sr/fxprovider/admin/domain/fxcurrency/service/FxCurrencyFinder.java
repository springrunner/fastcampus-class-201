package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyRateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FxCurrencyFinder {
    Page<FxCurrencyRateDto> search(FxCurrencySearchOption searchOption, Pageable pageable);
}
