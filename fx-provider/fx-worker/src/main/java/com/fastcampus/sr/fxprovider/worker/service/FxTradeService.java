package com.fastcampus.sr.fxprovider.worker.service;

import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FxTradeService {
    private final TradeHistoryRepository tradeHistoryRepository;

    public FxTradeService(TradeHistoryRepository tradeHistoryRepository) {
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    @Transactional
    public void startFxTrade(String tradeNumber) {
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeNumber(tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));

        log.debug("TradeHistory(tradeNumber: {}) status change to IN_PROGRESS.", tradeNumber);
        tradeHistory.inProgress();
    }
}
