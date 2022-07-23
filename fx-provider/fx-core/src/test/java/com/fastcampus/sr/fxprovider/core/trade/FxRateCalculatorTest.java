package com.fastcampus.sr.fxprovider.core.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class FxRateCalculatorTest {

    public static List<FxCurrency> getFxCurrency() {
        return Arrays.asList(
                FxCurrency.create(Currency.CNY, 6.751304),
                FxCurrency.create(Currency.EUR, 0.987085),
                FxCurrency.create(Currency.KRW, 1316.309794),
                FxCurrency.create(Currency.JPY, 138.346008)
        );
    }

    public static Map<Currency, Double> getFxCurrencyMap() {
        Map<Currency, Double> currencyQuotes = new LinkedHashMap<>();
        currencyQuotes.putIfAbsent(Currency.USD, 1.00d);

        for (FxCurrency el : getFxCurrency()) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }


    @ParameterizedTest
    @MethodSource("getSource")
    void testCalculate(Currency sendCurrency, Double sendMoney, Currency receiveCurrency, Double expectedReceiveMoney) {
        FxTrade result = FxRateCalculator.calculate(getFxCurrency(), sendCurrency, sendMoney, receiveCurrency);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(result.getSendCurrency()).isEqualTo(sendCurrency);
            softly.assertThat(result.getSendRate()).isEqualTo(getFxCurrencyMap().get(sendCurrency));
            softly.assertThat(result.getReceiveCurrency()).isEqualTo(receiveCurrency);
            softly.assertThat(result.getReceiveRate()).isEqualTo(getFxCurrencyMap().get(receiveCurrency));
            softly.assertThat(result.getReceiveMoney().doubleValue()).isEqualTo(expectedReceiveMoney);
        });
    }

    private static Stream<Arguments> getSource() {
        return Stream.of(
                Arguments.arguments(Currency.KRW, 1_000_000d, Currency.USD, 759.00d),
                Arguments.arguments(Currency.KRW, 13_163_097d, Currency.USD, 9999.00d),
                Arguments.arguments(Currency.USD, 9_900.00d, Currency.KRW, 13031466.00d),
                Arguments.arguments(Currency.USD, 1_000.00d, Currency.CNY, 6751.00d),
                Arguments.arguments(Currency.USD, 1_000.00d, Currency.JPY, 138346.00d),
                Arguments.arguments(Currency.USD, 1_000.00d, Currency.EUR, 987.00d),
                Arguments.arguments(Currency.KRW, 1_000_000d, Currency.CNY, 5128.00d),
                Arguments.arguments(Currency.KRW, 1_000_000d, Currency.JPY, 105100.00d),
                Arguments.arguments(Currency.KRW, 1_000_000d, Currency.EUR, 749.00d)
        );
    }
}