package com.fastcampus.sr.fxprovider.common.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum MarginType {
    FIX("고정"),
    RATE("비율"),
    ;
    private String description;

    MarginType(String description) {
        this.description = description;
    }
}
