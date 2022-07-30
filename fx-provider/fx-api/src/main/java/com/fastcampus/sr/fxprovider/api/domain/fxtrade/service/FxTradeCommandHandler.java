package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRate;
import com.fastcampus.sr.fxprovider.core.domain.currency.FxCurrencyRateRepository;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMargin;
import com.fastcampus.sr.fxprovider.core.domain.margin.FxMarginRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxMoneyCalculator;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistoryCreator;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferTradeHistoryRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxMoneyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FxTradeCommandHandler {
    private final FxCurrencyRateRepository fxCurrencyRateRepository;
    private final FxMarginRepository fxMarginRepository;
    private final FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository;

    public FxTradeCommandHandler(FxCurrencyRateRepository fxCurrencyRateRepository,
                                 FxMarginRepository fxMarginRepository,
                                 FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository) {
        this.fxCurrencyRateRepository = fxCurrencyRateRepository;
        this.fxMarginRepository = fxMarginRepository;
        this.fxTransferTradeHistoryRepository = fxTransferTradeHistoryRepository;
    }

    public FxTransferHistory sendMoney(String memberNumber, FxTradeSendCommand command) {
        List<FxCurrencyRate> fxCurrencies = fxCurrencyRateRepository.findAll();
        List<FxMargin> fxMargins = fxMarginRepository.findAll();

        FxMoneyDto fxMoneyDto = FxMoneyCalculator.calculate(fxCurrencies, fxMargins, command.getSendCurrency(), command.getSendMoney(), command.getReceiveCurrency());
        TradeHistoryCreator tradeHistoryCreator = command.createCreator(memberNumber, fxMoneyDto);
        return fxTransferTradeHistoryRepository.save(tradeHistoryCreator.create());
    }
}
