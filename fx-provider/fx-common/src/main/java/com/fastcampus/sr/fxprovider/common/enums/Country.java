package com.fastcampus.sr.fxprovider.common.enums;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * ISO Code
 * @see <a href="https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes">List of ISO 3166 country codes</a>
 * @see <a href="https://countrycode.org/"></a>
 */
public enum Country {
    KOR("KOR", "KR","South Korea", Arrays.asList(Currency.KRW, Currency.USD), Locale.KOREA),
    USA("USA", "US", "United State America", Arrays.asList(Currency.USD), Locale.US),
    CHN("CHN", "CN", "China", Arrays.asList(Currency.CNY), Locale.CHINA),
    JPN("JPN", "JP", "Japan", Arrays.asList(Currency.JPY), Locale.JAPAN),
    FRA("FRA", "FR", "The French Republic", Arrays.asList(Currency.EUR), Locale.FRANCE),
    ;

    private String alpha2code;
    private String alpha3code;
    private String description;
    private List<Currency> availableCurrencies;
    private Locale locale;

    Country(String alpha2code, String alpha3code, String description, List<Currency> availableCurrencies, Locale locale) {
        this.alpha2code = alpha2code;
        this.alpha3code = alpha3code;
        this.description = description;
        this.availableCurrencies = availableCurrencies;
        this.locale = locale;
    }
}
