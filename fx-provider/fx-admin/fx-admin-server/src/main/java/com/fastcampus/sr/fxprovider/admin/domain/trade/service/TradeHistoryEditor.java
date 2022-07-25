package com.fastcampus.sr.fxprovider.admin.domain.trade.service;

import com.fastcampus.sr.fxprovider.core.domain.trade.dto.TradeHistoryDto;

public interface TradeHistoryEditor {
    TradeHistoryDto cancel(String tradeNumber, String cancelReason);
}
