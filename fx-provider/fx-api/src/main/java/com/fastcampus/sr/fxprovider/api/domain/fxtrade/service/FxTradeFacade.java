package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.infrastructure.message.QueueMessageSender;
import com.fastcampus.sr.fxprovider.clients.aws.config.AwsSqsQueueProperties;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import org.springframework.stereotype.Component;

@Component
public class FxTradeFacade {
    private final QueueMessageSender queueMessageSender;
    private final FxTradeCommandHandler fxTradeCommandHandler;
    private final AwsSqsQueueProperties awsSqsQueueProperties;

    public FxTradeFacade(QueueMessageSender queueMessageSender, FxTradeCommandHandler fxTradeCommandHandler, AwsSqsQueueProperties awsSqsQueueProperties) {
        this.queueMessageSender = queueMessageSender;
        this.fxTradeCommandHandler = fxTradeCommandHandler;
        this.awsSqsQueueProperties = awsSqsQueueProperties;
    }

    public TradeHistory sendMoney(String memberNumber, FxTradeSendCommand sendCommand) {
        TradeHistory tradeHistory = fxTradeCommandHandler.sendMoney(memberNumber, sendCommand);
        queueMessageSender.sendMessage(awsSqsQueueProperties.getFxSend(), sendCommand);
        return tradeHistory;
    }
}
