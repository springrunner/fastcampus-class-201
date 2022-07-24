package com.fastcampus.sr.fxprovider.admin.service;

import com.fastcampus.sr.fxprovider.admin.repository.TradeHistoryQueryRepository;
import com.fastcampus.sr.fxprovider.admin.service.dto.TradeHistorySearchOption;
import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistoryRepository;
import com.fastcampus.sr.fxprovider.core.trade.dto.TradeHistoryDto;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeHistoryManager implements TradeHistoryFinder, TradeHistoryEditor {
    private final TradeHistoryRepository tradeHistoryRepository;
    private final TradeHistoryQueryRepository tradeHistoryQueryRepository;

    public TradeHistoryManager(TradeHistoryRepository tradeHistoryRepository, TradeHistoryQueryRepository tradeHistoryQueryRepository) {
        this.tradeHistoryRepository = tradeHistoryRepository;
        this.tradeHistoryQueryRepository = tradeHistoryQueryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TradeHistoryDto> search(TradeHistorySearchOption searchOption, Pageable pageable) {
        QueryResults<TradeHistoryDto> result = tradeHistoryQueryRepository.search(searchOption, pageable);
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Transactional
    @Override
    public TradeHistoryDto cancel(String tradeNumber, String cancelReason) {
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeNumber(tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));

        tradeHistory.cancel(cancelReason);

        return TradeHistoryDto.of(tradeHistory);
    }
}
