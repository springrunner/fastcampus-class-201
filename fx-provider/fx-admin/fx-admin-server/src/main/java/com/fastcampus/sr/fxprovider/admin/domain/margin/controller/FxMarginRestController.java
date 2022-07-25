package com.fastcampus.sr.fxprovider.admin.domain.margin.controller;

import com.fastcampus.sr.fxprovider.admin.domain.margin.service.FxMarginEditor;
import com.fastcampus.sr.fxprovider.admin.domain.margin.service.FxMarginFinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FxMarginRestController {
    private final FxMarginFinder finder;
    private final FxMarginEditor editor;

    public FxMarginRestController(FxMarginFinder finder, FxMarginEditor editor) {
        this.finder = finder;
        this.editor = editor;
    }
}
