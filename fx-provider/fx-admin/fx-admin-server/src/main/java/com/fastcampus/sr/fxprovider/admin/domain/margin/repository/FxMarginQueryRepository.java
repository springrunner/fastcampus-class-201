package com.fastcampus.sr.fxprovider.admin.domain.margin.repository;

import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class FxMarginQueryRepository extends QuerydslRepositorySupport {

    public FxMarginQueryRepository() {
        super(FxMargin.class);
    }
}
