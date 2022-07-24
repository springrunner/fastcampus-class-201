package com.fastcampus.sr.fxprovider.admin.service;

import com.fastcampus.sr.fxprovider.core.trade.dto.TradeHistoryDto;

public interface TradeHistoryEditor {
    TradeHistoryDto cancel(String tradeNumber);
}
