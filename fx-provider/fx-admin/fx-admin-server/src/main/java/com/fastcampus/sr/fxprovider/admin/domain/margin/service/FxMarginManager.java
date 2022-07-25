package com.fastcampus.sr.fxprovider.admin.domain.margin.service;

import com.fastcampus.sr.fxprovider.admin.domain.margin.repository.FxMarginQueryRepository;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMarginRepository;
import org.springframework.stereotype.Service;

@Service
public class FxMarginManager implements FxMarginFinder, FxMarginEditor{
    private final FxMarginRepository fxMarginRepository;
    private final FxMarginQueryRepository fxMarginQueryRepository;

    public FxMarginManager(FxMarginRepository fxMarginRepository, FxMarginQueryRepository fxMarginQueryRepository) {
        this.fxMarginRepository = fxMarginRepository;
        this.fxMarginQueryRepository = fxMarginQueryRepository;
    }
}
