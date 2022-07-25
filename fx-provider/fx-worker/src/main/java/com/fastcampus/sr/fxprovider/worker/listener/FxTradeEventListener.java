package com.fastcampus.sr.fxprovider.worker.listener;

import com.fastcampus.sr.fxprovider.clients.aws.config.AwsSqsQueueProperties;
import com.fastcampus.sr.fxprovider.worker.service.FxTradeService;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;

/**
 * 송금거래이벤트 리스너
 */
@Component
public class FxTradeEventListener extends LatchListener {
    private final AwsSqsQueueProperties awsSqsQueueProperties;
    private final FxTradeService fxTradeService;

    public FxTradeEventListener(AwsSqsQueueProperties awsSqsQueueProperties, FxTradeService fxTradeService) {
        this.awsSqsQueueProperties = awsSqsQueueProperties;
        this.fxTradeService = fxTradeService;
    }

    @SqsListener(value = "${aws.sqs.queue.fx-send}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receiveSendMoney(String tradeNumber, Acknowledgment ack) {
        try {
            fxTradeService.startFxTrade(tradeNumber);
            ack.acknowledge();
        } catch(RejectedExecutionException e) {

        } finally {
            super.getCountDownLatch().countDown();
        }
    }
}
