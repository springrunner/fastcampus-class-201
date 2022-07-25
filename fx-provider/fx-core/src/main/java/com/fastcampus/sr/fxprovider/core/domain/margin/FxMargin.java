package com.fastcampus.sr.fxprovider.core.domain.margin;

import com.fastcampus.sr.fxprovider.common.enums.MarginType;
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
public class FxMargin extends BaseEntity {
    @Column(name = "margin_min", columnDefinition = "NUMERIC(10,6) COMMENT '마진적용 최소금액'")
    private BigDecimal marginMin;
    @Column(name = "margin_max", columnDefinition = "NUMERIC(10,6) COMMENT '마진적용 최대금액'")
    private BigDecimal marginMax;
    @Enumerated(EnumType.STRING)
    @Column(name = "margin_type", columnDefinition = "VARCHAR(20) COMMENT '마진유형(정액/정률)'")
    private MarginType marginType;
    @Column(name = "margin_value", columnDefinition = "NUMERIC(10,6) COMMENT '마진값'")
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

    /**
     * 마진계산
     *
     * @param source 송금액
     * @return 송금액에 대한 마진
     */
    public BigDecimal calculateMargin(BigDecimal source) {
        switch (getMarginType()) {
            case FIX:
                return source.add(getMarginValue());
            case RATE:
                return source.multiply(getMarginValue());
            default:
                throw new IllegalArgumentException(String.format("지원하지 않는 유형(%s)입니다.", getMarginType()));
        }
    }
}
