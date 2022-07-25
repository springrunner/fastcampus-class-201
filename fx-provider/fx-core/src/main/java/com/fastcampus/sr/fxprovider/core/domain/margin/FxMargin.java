package com.fastcampus.sr.fxprovider.core.domain.margin;

import com.fastcampus.sr.fxprovider.core.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxMargin extends BaseEntity {
    @Column(name = "margin_min", columnDefinition = "NUMERIC(10,6) COMMENT '환율'")
    private BigDecimal marginMin;
    @Column(name = "margin_max", columnDefinition = "NUMERIC(10,6) COMMENT '환율'")
    private BigDecimal marginMax;
    @Column(name = "margin_value", columnDefinition = "NUMERIC(10,6) COMMENT '환율'")
    private BigDecimal marginValue;

    public FxMargin(BigDecimal marginMin, BigDecimal marginMax, BigDecimal marginValue) {
        this.marginMin = marginMin;
        this.marginMax = marginMax;
        this.marginValue = marginValue;
    }

    public boolean isTarget(BigDecimal source) {
        System.out.println("min: " + marginMin.compareTo(source));
        System.out.println("max: " + source.compareTo(marginMax));
        return marginMin.compareTo(source) <= 0 && source.compareTo(marginMax) <= 0;
    }
}
