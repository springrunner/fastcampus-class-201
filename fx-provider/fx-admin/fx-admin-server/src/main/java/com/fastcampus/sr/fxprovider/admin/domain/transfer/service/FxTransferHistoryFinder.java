package com.fastcampus.sr.fxprovider.admin.domain.transfer.service;

import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FxTransferHistoryFinder {
    Page<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable);
}
