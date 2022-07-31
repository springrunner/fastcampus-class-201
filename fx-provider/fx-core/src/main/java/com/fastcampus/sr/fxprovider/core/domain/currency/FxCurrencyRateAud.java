package com.fastcampus.sr.fxprovider.core.domain.currency;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxCurrencyRateAud extends BaseEntity {

    @Column(name = "rev", columnDefinition = "NUMBER COMMENT 'envers revision'")
    private Long rev;

    @Column(name = "revtype", columnDefinition = "tinyint COMMENT '0: Insert, 1: Update, 2: Delete'")
    private Integer revtype;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", columnDefinition = "VARCHAR(5) COMMENT '통화'")
    private Currency currency;

    @Column(name = "fx_rate", columnDefinition = "NUMERIC(10,6) COMMENT '환율'")
    private BigDecimal rate;
}
