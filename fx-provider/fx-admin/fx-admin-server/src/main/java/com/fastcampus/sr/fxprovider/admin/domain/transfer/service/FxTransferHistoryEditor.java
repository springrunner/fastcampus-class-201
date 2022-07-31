package com.fastcampus.sr.fxprovider.admin.domain.transfer.service;

import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;

public interface FxTransferHistoryEditor {
    FxTransferHistoryDto cancel(String tradeNumber, String cancelReason);
}
