package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.controller;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.FxCurrencyRateEditor;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.FxCurrencyRateFinder;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyRateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FxCurrencyRateRestController {
    private final FxCurrencyRateFinder finder;
    private final FxCurrencyRateEditor editor;

    public FxCurrencyRateRestController(FxCurrencyRateFinder finder, FxCurrencyRateEditor editor) {
        this.finder = finder;
        this.editor = editor;
    }

    @GetMapping("/api/v1/fx-currency")
    public Page<FxCurrencyRateDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        return finder.search(searchOption, pageable);
    }
}
