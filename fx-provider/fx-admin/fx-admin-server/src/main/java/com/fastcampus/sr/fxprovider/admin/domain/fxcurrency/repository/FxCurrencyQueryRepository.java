package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.repository;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.currency.QFxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.fastcampus.sr.fxprovider.core.domain.currency.QFxCurrency.fxCurrency;

@Repository
public class FxCurrencyQueryRepository extends QuerydslRepositorySupport {

    public FxCurrencyQueryRepository() {
        super(FxCurrency.class);
    }

    public QueryResults<FxCurrencyDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        return getQuerydsl().createQuery()
                .select(
                        Projections.constructor(
                                FxCurrencyDto.class,
                                fxCurrency.currency,
                                fxCurrency.rate
                        )
                )
                .from(fxCurrency)
                .where(hasCurrency(searchOption))
                .fetchResults();
    }

    private BooleanExpression hasCurrency(FxCurrencySearchOption searchOption) {
        return Objects.nonNull(searchOption.getCurrency()) ? fxCurrency.currency.eq(searchOption.getCurrency()) : null;
    }
}
