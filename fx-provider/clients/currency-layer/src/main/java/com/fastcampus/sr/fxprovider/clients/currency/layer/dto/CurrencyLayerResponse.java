package com.fastcampus.sr.fxprovider.clients.currency.layer.dto;

import com.fastcampus.sr.fxprovider.common.currency.Currency;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CurrencyLayerResponse {
    private long timestamp;
    private boolean success;
    private Currency source;
    private Map<String, Double> quotes;

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

    public Map<String, Double> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, Double> quotes) {
        this.quotes = quotes;
    }

    public Map<Currency, Double> getCurrencyQuotes() {
        Map<Currency, Double> currencyQuotes = new LinkedHashMap<>();
        for(Map.Entry entry: getQuotes().entrySet()) {
            currencyQuotes.put(Currency.of(((String)entry.getKey()).substring(3,6)), (Double) entry.getValue());
        }
        return currencyQuotes;
    }
}
