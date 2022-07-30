package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import com.fastcampus.sr.fxprovider.core.exception.NotSupportCurrencyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 환전은 달러로 전환하여 계산하는 것을 원칙으로 한다.
 */
@Slf4j
public class FxMoneyCalculator {
    private static final BigDecimal DEFAULT_USD_RATE = BigDecimal.valueOf(1.00); // 미국통화를 기준으로 계산

    private FxMoneyCalculator() {
        throw new UnsupportedOperationException("Utility class.");
    }


    /**
     * 송금정보 계산
     *
     * @param fxCurrencies    통화환율
     * @param fxMargins       마진
     * @param sendCurrency    송금통화
     * @param sendMoney       송금액
     * @param receiveCurrency 수취통화
     * @return 수취금액을 포함한 송금정보
     */
    public static FxMoneyDto calculate(List<FxCurrencyRate> fxCurrencies, List<FxMargin> fxMargins, Currency sendCurrency, BigDecimal sendMoney, Currency receiveCurrency) {
        Assert.notEmpty(fxCurrencies, "환율정보는 필수입력값입니다.");
        Assert.notEmpty(fxMargins, "마진정보는 필수입력값입니다.");
        Assert.notNull(sendCurrency, "송금통화는 필수입력값입니다.");
        Assert.notNull(sendMoney, "송금액은 필수입력값입니다.");
        Assert.notNull(receiveCurrency, "수취통화는 필수입력값입니다.");

        Map<Currency, BigDecimal> currencyMap = getCurrencyMap(fxCurrencies);

        BigDecimal sendRate = getFxRate(sendCurrency, currencyMap, "지원하지 않는 송금통화(%s)입니다.");
        BigDecimal receiveRate = getFxRate(receiveCurrency, currencyMap, "지원하지 않는 수취통화(%s)입니다.");

        BigDecimal sendDollarMoney = sendMoney.divide(sendRate, 2, RoundingMode.DOWN);
        log.debug("SendRate: {}, ReceiveRate: {}, sendDollarMoney: {}", sendRate, receiveRate, sendDollarMoney);

        for (FxMargin fxMargin : fxMargins) {
            if (fxMargin.isTarget(sendDollarMoney)) {
                sendDollarMoney = sendDollarMoney.subtract(fxMargin.calculateMargin(sendDollarMoney));
                log.debug("Margin: {}", fxMargin);
                break;
            }
        }

        BigDecimal receiveMoney = sendDollarMoney.multiply(receiveRate)
                .setScale(2, RoundingMode.DOWN);

        log.debug("ReceiveMoney(SendCurrency: {}, SendMoney: {}, ReceiveCurrency: {}, ReceiveMoney: {})",
                sendCurrency, sendMoney, receiveCurrency, receiveMoney);
        return FxMoneyDto.of(sendCurrency, sendRate, sendMoney, receiveCurrency, receiveRate, receiveMoney);
    }

    private static BigDecimal getFxRate(Currency sendCurrency, Map<Currency, BigDecimal> currencyMap, String format) {
        BigDecimal sendRate = Currency.USD == sendCurrency ? DEFAULT_USD_RATE : currencyMap.get(sendCurrency);
        if (Objects.isNull(sendRate)) {
            throw new NotSupportCurrencyException(String.format(format, sendCurrency));
        }
        return sendRate;
    }

    private static Map<Currency, BigDecimal> getCurrencyMap(List<FxCurrencyRate> fxCurrencies) {
        Map<Currency, BigDecimal> currencyQuotes = new LinkedHashMap<>();

        for (FxCurrencyRate el : fxCurrencies) {
            currencyQuotes.putIfAbsent(el.getCurrency(), el.getRate());
        }

        return currencyQuotes;
    }
}
