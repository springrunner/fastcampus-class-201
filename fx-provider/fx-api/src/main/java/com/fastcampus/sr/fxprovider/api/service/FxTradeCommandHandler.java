package com.fastcampus.sr.fxprovider.api.service;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryCreator;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FxTradeCommandHandler {
    private final FxCurrencyRepository fxCurrencyRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public FxTradeCommandHandler(FxCurrencyRepository fxCurrencyRepository, TradeHistoryRepository tradeHistoryRepository) {
        this.fxCurrencyRepository = fxCurrencyRepository;
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    public TradeHistory sendMoney(String memberNumber, FxTradeSendCommand command) {
        List<FxCurrency> fxCurrencies = fxCurrencyRepository.findAll();

        TradeHistoryCreator tradeHistoryCreator = command.createCreator(memberNumber);
        return tradeHistoryRepository.save(tradeHistoryCreator.create(fxCurrencies));
    }
}
