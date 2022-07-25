package com.fastcampus.sr.fxprovider.common.enums;

/**
 * @see <a href="https://ko.wikipedia.org/wiki/ISO_4217">통화코드(ISO 4217)</a>
 */
public enum Currency {
    USD("US Dallar"),
    KRW("Korean Won"),
    JPY("Japan Yen"),
    CNY("China Yuan"),
    EUR("Euro");

    private String description;

    Currency(String description) {
        this.description = description;
    }

    public static Currency of(String currency) {
        for(Currency el: values()) {
            if(el.name().equals(currency)) {
                return el;
            }
        }

        throw new IllegalArgumentException(String.format("지원하지 않는 통화(%s)입니다.", currency));
    }

    public String getDescription() {
        return description;
    }
}
