package com.fastcampus.sr.fxprovider.api.service;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxTradeSendResponse;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrency;
import com.fastcampus.sr.fxprovider.core.currency.FxCurrencyRepository;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistoryCreator;
import com.fastcampus.sr.fxprovider.core.trade.TradeHistoryRepository;
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

    public FxTradeSendResponse sendMoney(FxTradeSendCommand command) {
        List<FxCurrency> fxCurrencies = fxCurrencyRepository.findAll();

        TradeHistoryCreator tradeHistoryCreator = command.createCreator();
        TradeHistory tradeHistory = tradeHistoryRepository.save(tradeHistoryCreator.create(fxCurrencies));

        return FxTradeSendResponse.of(tradeHistory);
    }
}
