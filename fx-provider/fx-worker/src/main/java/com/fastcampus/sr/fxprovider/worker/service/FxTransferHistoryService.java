package com.fastcampus.sr.fxprovider.worker.service;

import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferTradeHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FxTransferHistoryService {
    private final FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository;

    public FxTransferHistoryService(FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository) {
        this.fxTransferTradeHistoryRepository = fxTransferTradeHistoryRepository;
    }

    @Transactional
    public void startFxTrade(String tradeNumber) {
        FxTransferHistory fxTransferHistory = fxTransferTradeHistoryRepository.findByTradeNumber(tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));

        log.debug("TradeHistory(tradeNumber: {}) status change to IN_PROGRESS.", tradeNumber);
        fxTransferHistory.inProgress();
    }
}
