package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.controller;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.FxCurrencyEditor;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.FxCurrencyFinder;
import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FxCurrencyRestController {
    private final FxCurrencyFinder finder;
    private final FxCurrencyEditor editor;

    public FxCurrencyRestController(FxCurrencyFinder finder, FxCurrencyEditor editor) {
        this.finder = finder;
        this.editor = editor;
    }

    @GetMapping("/api/v1/fx-currency")
    public Page<FxCurrencyDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        return finder.search(searchOption, pageable);
    }
}
