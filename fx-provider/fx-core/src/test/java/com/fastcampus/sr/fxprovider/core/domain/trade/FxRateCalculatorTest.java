package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class FxRateCalculatorTest {

    public static List<FxCurrencyRate> getFxCurrency() {
        return Arrays.asList(
                FxCurrencyRate.create(Currency.CNY, BigDecimal.valueOf(6.751304)),
                FxCurrencyRate.create(Currency.EUR, BigDecimal.valueOf(0.987085)),
                FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1316.309794)),
                FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(138.346008))
        );
    }

    public static Map<Currency, BigDecimal> getFxCurrencyMap() {
        Map<Currency, BigDecimal> currencyQuotes = new LinkedHashMap<>();
        currencyQuotes.put(Currency.USD, BigDecimal.valueOf(1.0));

        for (FxCurrencyRate el : getFxCurrency()) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }


    @ParameterizedTest
    @MethodSource("getSource")
    void testCalculate(Currency sendCurrency, BigDecimal sendMoney, Currency receiveCurrency, BigDecimal expectedReceiveMoney) {
        FxMoneyDto result = FxRateCalculator.calculate(getFxCurrency(), sendCurrency, sendMoney, receiveCurrency);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(result.getSendCurrency()).isEqualTo(sendCurrency);
            softly.assertThat(result.getSendRate()).isEqualTo(getFxCurrencyMap().get(sendCurrency));
            softly.assertThat(result.getReceiveCurrency()).isEqualTo(receiveCurrency);
            softly.assertThat(result.getReceiveRate()).isEqualTo(getFxCurrencyMap().get(receiveCurrency));
            softly.assertThat(result.getReceiveMoney()).isEqualTo(expectedReceiveMoney);
        });
    }

    private static Stream<Arguments> getSource() {
        return Stream.of(
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.USD, new BigDecimal("759.00")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(13_163_097), Currency.USD, new BigDecimal("9999.00")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(9_900.00), Currency.KRW, new BigDecimal("13031466.00")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.CNY, new BigDecimal("6751.00")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.JPY, new BigDecimal("138346.00")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.EUR, new BigDecimal("987.00")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.CNY, new BigDecimal("5128.00")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.JPY, new BigDecimal("105100.00")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.EUR, new BigDecimal("749.00"))
        );
    }
}