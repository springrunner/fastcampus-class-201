package com.fastcampus.sr.fxprovider.clients.currency.layer.dto;

import com.fastcampus.sr.fxprovider.common.enums.Currency;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyLayerResponse {
    private long timestamp;
    private boolean success;
    private Currency source;
    private Map<String, BigDecimal> quotes;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Currency getSource() {
        return source;
    }

    public void setSource(Currency source) {
        this.source = source;
    }

    public Map<String, BigDecimal> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, BigDecimal> quotes) {
        this.quotes = quotes;
    }

    public Map<Currency, BigDecimal> getCurrencyQuotes() {
        Map<Currency, BigDecimal> currencyQuotes = new LinkedHashMap<>();
        for(Map.Entry entry: getQuotes().entrySet()) {
            currencyQuotes.put(Currency.of(((String)entry.getKey()).substring(3,6)), (BigDecimal) entry.getValue());
        }
        return currencyQuotes;
    }
}
