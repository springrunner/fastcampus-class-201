package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.exception.NotSupportCurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 환전은 달러로 전환하여 계산하는 것을 원칙으로 한다.
 */
@Slf4j
public class FxRateCalculator {
    private static final BigDecimal DEFAULT_USD_RATE = BigDecimal.valueOf(1.00); // 미국통화를 기준으로 계산

    private FxRateCalculator() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static FxTrade calculate(List<FxCurrency> fxCurrencies, Currency sendCurrency, BigDecimal sendMoney, Currency receiveCurrency) {
        Assert.notEmpty(fxCurrencies, "환율정보는 필수입력값입니다.");
        Assert.notNull(sendCurrency, "송금통화는 필수입력값입니다.");
        Assert.notNull(sendMoney, "송금액은 필수입력값입니다.");
        Assert.notNull(receiveCurrency, "수취통화는 필수입력값입니다.");

        Map<Currency, BigDecimal> currencyMap = getCurrencyMap(fxCurrencies);

        BigDecimal sendRate = getFxRate(sendCurrency, currencyMap, "지원하지 않는 송금통화(%s)입니다.");
        BigDecimal receiveRate = getFxRate(receiveCurrency, currencyMap, "지원하지 않는 수취통화(%s)입니다.");

        BigDecimal sendDollarMoney = sendMoney.divide(sendRate, 2, RoundingMode.DOWN);
        log.debug("SendRate: {}, ReceiveRate: {}, sendDollarMoney: {}", sendRate, receiveRate, sendDollarMoney);

        //TODO 환전에 대한 마진(Margin)을 계산하이 필요할 겁니다. ㅎㅎ.

        BigDecimal receiveMoney = sendDollarMoney
                .multiply(receiveRate)
                .setScale(0, RoundingMode.DOWN) // 소수점이하는 절삭하는 것으로 마진을 대신함
                .setScale(2, RoundingMode.DOWN);

        log.debug("ReceiveMoney(SendCurrency: {}, SendMoney: {}, ReceiveCurrency: {}, ReceiveMoney: {})",
                sendCurrency, sendMoney, receiveCurrency, receiveMoney);
        return FxTrade.of(sendCurrency, sendRate, sendMoney, receiveCurrency, receiveRate, receiveMoney);
    }

    private static BigDecimal getFxRate(Currency sendCurrency, Map<Currency, BigDecimal> currencyMap, String format) {
        BigDecimal sendRate = Currency.USD == sendCurrency ? DEFAULT_USD_RATE : currencyMap.get(sendCurrency);
        if (Objects.isNull(sendRate)) {
            throw new NotSupportCurrencyException(String.format(format, sendCurrency));
        }
        return sendRate;
    }

    private static Map<Currency, BigDecimal> getCurrencyMap(List<FxCurrency> fxCurrencies) {
        Map<Currency, BigDecimal> currencyQuotes = new LinkedHashMap<>();

        for (FxCurrency el : fxCurrencies) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }
}
