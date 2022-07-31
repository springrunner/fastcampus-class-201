package com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.repository;

import com.fastcampus.sr.fxprovider.admin.domain.fxcurrency.service.dto.FxCurrencySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxCurrencyRateDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.fastcampus.sr.fxprovider.core.domain.currency.QFxCurrencyRate.fxCurrencyRate;


@Repository
public class FxCurrencyRateQueryRepository extends QuerydslRepositorySupport {

    public FxCurrencyRateQueryRepository() {
        super(FxCurrencyRate.class);
    }

    public QueryResults<FxCurrencyRateDto> search(FxCurrencySearchOption searchOption, Pageable pageable) {
        return getQuerydsl().createQuery()
                .select(
                        Projections.constructor(
                                FxCurrencyRateDto.class,
                                fxCurrencyRate.currency,
                                fxCurrencyRate.rate
                        )
                )
                .from(fxCurrencyRate)
                .where(hasCurrency(searchOption))
                .fetchResults();
    }

    private BooleanExpression hasCurrency(FxCurrencySearchOption searchOption) {
        return Objects.nonNull(searchOption.getCurrency()) ? fxCurrencyRate.currency.eq(searchOption.getCurrency()) : null;
    }
}
