package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TradeHistoryQueryService {
    private final TradeHistoryRepository tradeHistoryRepository;

    public TradeHistoryQueryService(TradeHistoryRepository tradeHistoryRepository) {
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    public TradeHistory findByMemberNumberAndTradeNumber(String memberNumber, String tradeNumber) {
        return tradeHistoryRepository.findByMemberNumberAndTradeNumber(memberNumber, tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));
    }
}
