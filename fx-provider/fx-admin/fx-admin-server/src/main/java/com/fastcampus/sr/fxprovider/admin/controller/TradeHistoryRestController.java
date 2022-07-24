package com.fastcampus.sr.fxprovider.admin.controller;

import com.fastcampus.sr.fxprovider.admin.service.TradeHistoryEditor;
import com.fastcampus.sr.fxprovider.admin.service.TradeHistoryFinder;
import com.fastcampus.sr.fxprovider.admin.service.dto.TradeHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.trade.dto.TradeHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeHistoryRestController {
    private final TradeHistoryFinder finder;
    private final TradeHistoryEditor editor;

    public TradeHistoryRestController(TradeHistoryFinder finder, TradeHistoryEditor editor) {
        this.finder = finder;
        this.editor = editor;
    }

    @GetMapping("/api/v1/trade-history")
    public Page<TradeHistoryDto> search(TradeHistorySearchOption searchOption, Pageable pageable) {
        return finder.search(searchOption, pageable);
    }

    @PutMapping("/api/v1/trade-history/{tradeNumber}/cancel")
    public TradeHistoryDto cancel(@PathVariable("tradeHistory") String tradeNumber) {
        return editor.cancel(tradeNumber);
    }
}
