package com.fastcampus.sr.fxprovider.core.currency;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.ZonedDateTime;


/**
 * 환율정보, Hibernate Enver 를 이용해서 이력 보관
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxCurrency extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", columnDefinition = "VARCHAR(5) COMMENT '통화'")
    private Currency currency;

    @Column(name = "fx_rate", columnDefinition = "DOUBLE COMMENT '환율'")
    private Double rate;

    public static FxCurrency create(Currency currency, Double rate) {
        FxCurrency fxCurrency = new FxCurrency();
        fxCurrency.currency = currency;
        fxCurrency.rate = rate;
        return fxCurrency;
    }

    public void update(Double rate) {
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }
}
