package com.fastcampus.sr.fxprovider.admin.domain.transfer.service;

import com.fastcampus.sr.fxprovider.admin.domain.transfer.repository.FxTransferTradeHistoryQueryRepository;
import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.common.exception.NotFoundTradeHistoryException;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferTradeHistoryRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FxTransferHistoryManager implements FxTransferHistoryFinder, FxTransferHistoryEditor {
    private final FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository;
    private final FxTransferTradeHistoryQueryRepository fxTransferTradeHistoryQueryRepository;

    public FxTransferHistoryManager(FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository, FxTransferTradeHistoryQueryRepository fxTransferTradeHistoryQueryRepository) {
        this.fxTransferTradeHistoryRepository = fxTransferTradeHistoryRepository;
        this.fxTransferTradeHistoryQueryRepository = fxTransferTradeHistoryQueryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable) {
        QueryResults<FxTransferHistoryDto> result = fxTransferTradeHistoryQueryRepository.search(searchOption, pageable);
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Transactional
    @Override
    public FxTransferHistoryDto cancel(String tradeNumber, String cancelReason) {
        FxTransferHistory fxTransferHistory = fxTransferTradeHistoryRepository.findByTradeNumber(tradeNumber)
                .orElseThrow(() -> new NotFoundTradeHistoryException(tradeNumber));

        fxTransferHistory.cancel(cancelReason);

        return FxTransferHistoryDto.of(fxTransferHistory);
    }
}
