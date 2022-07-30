package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferTradeHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FxTransferTradeHistoryQueryService {
    private final FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository;

    public FxTransferTradeHistoryQueryService(FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository) {
        this.fxTransferTradeHistoryRepository = fxTransferTradeHistoryRepository;
    }

    public FxTransferHistory findByMemberNumberAndTradeNumber(String memberNumber, String tradeNumber) {
        return fxTransferTradeHistoryRepository.findByMemberNumberAndTradeNumber(memberNumber, tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));
    }
}
