package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FxCurrencyFinder {
    Page<FxCurrencyDto> search(FxCurrencySearchOption searchOption, Pageable pageable);
}
