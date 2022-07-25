package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryCreator;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FxTradeCommandHandler {
    private final FxCurrencyRateRepository fxCurrencyRateRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public FxTradeCommandHandler(FxCurrencyRateRepository fxCurrencyRateRepository, TradeHistoryRepository tradeHistoryRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    public TradeHistory sendMoney(String memberNumber, FxTradeSendCommand command) {
        List<FxCurrencyRate> fxCurrencies = fxCurrencyRateRepository.findAll();

        TradeHistoryCreator tradeHistoryCreator = command.createCreator(memberNumber);
        return tradeHistoryRepository.save(tradeHistoryCreator.create(fxCurrencies));
    }
}
