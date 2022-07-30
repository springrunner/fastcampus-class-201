package com.fastcampus.sr.fxprovider.admin.domain.trade.controller;

import com.fastcampus.sr.fxprovider.admin.domain.trade.controller.dto.CancelRequest;
import com.fastcampus.sr.fxprovider.admin.domain.trade.service.TradeHistoryFacade;
import com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeHistoryRestController {
    private final TradeHistoryFacade tradeHistoryFacade;

    public TradeHistoryRestController(TradeHistoryFacade tradeHistoryFacade) {
        this.tradeHistoryFacade = tradeHistoryFacade;
    }

    @GetMapping("/api/v1/trade-history")
    public Page<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable) {
        return tradeHistoryFacade.search(searchOption, pageable);
    }

    @PutMapping("/api/v1/trade-history/{tradeNumber}/cancel")
    public FxTransferHistoryDto cancel(@PathVariable("tradeNumber") String tradeNumber, @RequestBody CancelRequest request) {
        return tradeHistoryFacade.cancel(tradeNumber, request.getCancelReason());
    }
}
