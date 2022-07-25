package com.fastcampus.sr.fxprovider.admin.domain.trade.service;

import com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto.TradeHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.TradeHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TradeHistoryFinder {
    Page<TradeHistoryDto> search(TradeHistorySearchOption searchOption, Pageable pageable);
}
