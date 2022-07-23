package com.fastcampus.sr.fxprovider.core.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.exception.NotSupportCurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class FxRateCalculator {
    private static final Double DEFAULT_USD_RATE = 1.00d; // 미국통화를 기준으로 계산

    private FxRateCalculator() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static FxTrade calculate(List<FxCurrency> fxCurrencies, Currency sendCurrency, Double sendMoney, Currency receiveCurrency) {
        Assert.notEmpty(fxCurrencies, "환율정보는 필수입력값입니다.");
        Assert.notNull(sendCurrency, "송금통화는 필수입력값입니다.");
        Assert.notNull(sendMoney, "송금액은 필수입력값입니다.");
        Assert.notNull(receiveCurrency, "수취통화는 필수입력값입니다.");

        Map<Currency, Double> currencyMap = getCurrencyMap(fxCurrencies);

        Double sendRate = getFxRate(sendCurrency, currencyMap, "지원하지 않는 송금통화(%s)입니다.");
        Double receiveRate = getFxRate(receiveCurrency, currencyMap, "지원하지 않는 수취통화(%s)입니다.");

        BigDecimal sendDollarMoney = BigDecimal.valueOf(sendMoney)
                .divide(BigDecimal.valueOf(sendRate), 2, RoundingMode.DOWN);

        BigDecimal receiveMoney = sendDollarMoney
                .multiply(BigDecimal.valueOf(receiveRate))
                .setScale(0, RoundingMode.DOWN) // 소수점이하는 절삭하는 것으로 마진을 대신함
                .setScale(2, RoundingMode.DOWN);

        //TODO 환전에 대한 마진(Margin)을 계산하이 필요할 겁니다. ㅎㅎ.

        log.debug("ReceiveMoney(SendCurrency: {}, SendMoney: {}, ReceiveCurrency: {}, ReceiveMoney: {})",
                sendCurrency, sendMoney, receiveCurrency, receiveMoney.doubleValue());
        return FxTrade.of(sendCurrency, sendRate, receiveCurrency, receiveRate, receiveMoney.doubleValue());
    }

    private static Double getFxRate(Currency sendCurrency, Map<Currency, Double> currencyMap, String format) {
        Double sendRate = Currency.USD == sendCurrency ? DEFAULT_USD_RATE : currencyMap.get(sendCurrency);
        if (Objects.isNull(sendRate)) {
            throw new NotSupportCurrencyException(String.format(format, sendCurrency));
        }
        return sendRate;
    }

    private static Map<Currency, Double> getCurrencyMap(List<FxCurrency> fxCurrencies) {
        Map<Currency, Double> currencyQuotes = new LinkedHashMap<>();

        for (FxCurrency el : fxCurrencies) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }
}
