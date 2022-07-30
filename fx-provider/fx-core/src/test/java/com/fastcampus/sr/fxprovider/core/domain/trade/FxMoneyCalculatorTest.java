package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.enums.MarginType;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
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

class FxMoneyCalculatorTest {

    public static List<FxCurrencyRate> getFxCurrencies() {
        return Arrays.asList(
                FxCurrencyRate.create(Currency.CNY, BigDecimal.valueOf(6.751304)),
                FxCurrencyRate.create(Currency.EUR, BigDecimal.valueOf(0.987085)),
                FxCurrencyRate.create(Currency.KRW, BigDecimal.valueOf(1316.309794)),
                FxCurrencyRate.create(Currency.JPY, BigDecimal.valueOf(138.346008))
        );
    }

    public static List<FxMargin> getFxMargins() {
        return Arrays.asList(
                FxMargin.builder().thresholdMin(BigDecimal.valueOf(100)).thresholdMax(BigDecimal.valueOf(200)).marginType(MarginType.FIX).marginValue(BigDecimal.valueOf(10)).build(),
                FxMargin.builder().thresholdMin(BigDecimal.valueOf(200)).thresholdMax(BigDecimal.valueOf(300)).marginType(MarginType.FIX).marginValue(BigDecimal.valueOf(10)).build(),
                FxMargin.builder().thresholdMin(BigDecimal.valueOf(300)).thresholdMax(BigDecimal.valueOf(1000)).marginType(MarginType.RATE).marginValue(BigDecimal.valueOf(0.005)).build()
        );
    }

    public static Map<Currency, BigDecimal> getFxCurrencyMap() {
        Map<Currency, BigDecimal> currencyQuotes = new LinkedHashMap<>();
        currencyQuotes.put(Currency.USD, BigDecimal.valueOf(1.0));

        for (FxCurrencyRate el : getFxCurrencies()) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }


    @ParameterizedTest
    @MethodSource("getSource")
    void testCalculate(Currency sendCurrency, BigDecimal sendMoney, Currency receiveCurrency, BigDecimal expectedReceiveMoney) {
        FxMoneyDto result = FxMoneyCalculator.calculate(getFxCurrencies(), getFxMargins(), sendCurrency, sendMoney, receiveCurrency);

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
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.USD, new BigDecimal("755.89")), // 759 - (759 * 0.005)
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(13_163_097), Currency.USD, new BigDecimal("9999.99")), // 9999.9992
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(9_900.00), Currency.KRW, new BigDecimal("13031466.96")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.CNY, new BigDecimal("6717.54")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.JPY, new BigDecimal("137654.27")),
                Arguments.arguments(Currency.USD, BigDecimal.valueOf(1_000.00), Currency.EUR, new BigDecimal("982.14")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.CNY, new BigDecimal("5103.25")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.JPY, new BigDecimal("104574.57")),
                Arguments.arguments(Currency.KRW, BigDecimal.valueOf(1_000_000), Currency.EUR, new BigDecimal("746.12"))
        );
    }
}