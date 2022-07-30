package com.fastcampus.sr.fxprovider.api.domain.fxtrade.service;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.infrastructure.message.QueueMessageSender;
import com.fastcampus.sr.fxprovider.clients.aws.config.AwsSqsQueueProperties;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class FxTransferFacade {
    private final QueueMessageSender queueMessageSender;
    private final FxTradeCommandHandler fxTradeCommandHandler;
    private final FxTransferTradeHistoryQueryService fxTransferTradeHistoryQueryService;
    private final AwsSqsQueueProperties awsSqsQueueProperties;

    public FxTransferFacade(QueueMessageSender queueMessageSender,
                            FxTradeCommandHandler fxTradeCommandHandler,
                            FxTransferTradeHistoryQueryService fxTransferTradeHistoryQueryService,
                            AwsSqsQueueProperties awsSqsQueueProperties) {
        this.queueMessageSender = queueMessageSender;
        this.fxTradeCommandHandler = fxTradeCommandHandler;
        this.fxTransferTradeHistoryQueryService = fxTransferTradeHistoryQueryService;
        this.awsSqsQueueProperties = awsSqsQueueProperties;
    }

    public FxTransferHistoryDto sendMoney(String memberNumber, FxTradeSendCommand sendCommand) {
        FxTransferHistory fxTransferHistory = fxTradeCommandHandler.sendMoney(memberNumber, sendCommand);
        queueMessageSender.sendMessage(awsSqsQueueProperties.getFxSend(), sendCommand);
        return FxTransferHistoryDto.of(fxTransferHistory);
    }

    public FxTransferHistoryDto findByMemberNumberAndTradeNumber(String memberNumber, String tradeNumber) {
        FxTransferHistory fxTransferHistory = fxTransferTradeHistoryQueryService.findByMemberNumberAndTradeNumber(memberNumber, tradeNumber);
        return FxTransferHistoryDto.of(fxTransferHistory);
    }
}
