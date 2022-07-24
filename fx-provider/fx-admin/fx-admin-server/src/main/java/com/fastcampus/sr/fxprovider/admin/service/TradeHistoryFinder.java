package com.fastcampus.sr.fxprovider.admin.service;

import com.fastcampus.sr.fxprovider.admin.service.dto.TradeHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.trade.dto.TradeHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TradeHistoryFinder {
    Page<TradeHistoryDto> search(TradeHistorySearchOption searchOption, Pageable pageable);
}
