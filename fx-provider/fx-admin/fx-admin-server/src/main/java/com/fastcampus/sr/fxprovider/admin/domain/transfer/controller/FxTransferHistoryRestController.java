package com.fastcampus.sr.fxprovider.admin.domain.transfer.controller;

import com.fastcampus.sr.fxprovider.admin.domain.transfer.controller.dto.CancelRequest;
import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.TransferHistoryFacade;
import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class FxTransferHistoryRestController {
    private final TransferHistoryFacade transferHistoryFacade;

    public FxTransferHistoryRestController(TransferHistoryFacade transferHistoryFacade) {
        this.transferHistoryFacade = transferHistoryFacade;
    }

    @GetMapping("/api/v1/trade-history")
    public Page<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable) {
        return transferHistoryFacade.search(searchOption, pageable);
    }

    @PutMapping("/api/v1/trade-history/{tradeNumber}/cancel")
    public FxTransferHistoryDto cancel(@PathVariable("tradeNumber") String tradeNumber, @RequestBody CancelRequest request) {
        return transferHistoryFacade.cancel(tradeNumber, request.getCancelReason());
    }
}
