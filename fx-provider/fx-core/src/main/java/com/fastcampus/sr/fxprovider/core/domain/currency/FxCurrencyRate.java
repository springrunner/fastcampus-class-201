package com.fastcampus.sr.fxprovider.core.domain.currency;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * 환율정보, Hibernate Enver 를 이용해서 이력 보관
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxCurrencyRate extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", columnDefinition = "VARCHAR(5) COMMENT '통화'")
    private Currency currency;

    @Column(name = "fx_rate", columnDefinition = "NUMERIC(10,6) COMMENT '환율'")
    private BigDecimal rate;

    public static FxCurrencyRate create(Currency currency, BigDecimal rate) {
        FxCurrencyRate fxCurrencyRate = new FxCurrencyRate();
        fxCurrencyRate.currency = currency;
        fxCurrencyRate.rate = rate;
        return fxCurrencyRate;
    }

    public void update(BigDecimal rate) {
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }
}
