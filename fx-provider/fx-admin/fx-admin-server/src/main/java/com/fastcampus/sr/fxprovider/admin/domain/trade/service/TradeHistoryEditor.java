package com.fastcampus.sr.fxprovider.admin.domain.trade.service;

import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;

public interface TradeHistoryEditor {
    FxTransferHistoryDto cancel(String tradeNumber, String cancelReason);
}
