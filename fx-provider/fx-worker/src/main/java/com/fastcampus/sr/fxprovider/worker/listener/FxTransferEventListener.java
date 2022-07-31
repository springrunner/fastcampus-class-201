package com.fastcampus.sr.fxprovider.worker.listener;

import com.fastcampus.sr.fxprovider.clients.aws.config.AwsSqsQueueProperties;
import com.fastcampus.sr.fxprovider.worker.service.FxTransferHistoryService;
import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;

/**
 * 송금거래이벤트 리스너
 */
@Slf4j
@Component
public class FxTransferEventListener extends LatchListener {
    private final AwsSqsQueueProperties awsSqsQueueProperties;
    private final FxTransferHistoryService fxTransferHistoryService;

    public FxTransferEventListener(AwsSqsQueueProperties awsSqsQueueProperties, FxTransferHistoryService fxTransferHistoryService) {
        this.awsSqsQueueProperties = awsSqsQueueProperties;
        this.fxTransferHistoryService = fxTransferHistoryService;
    }

    @SqsListener(value = "${aws.sqs.queue.fx-send}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receiveSendMoney(String tradeNumber, Acknowledgment ack) {
        try {
            fxTransferHistoryService.startFxTrade(tradeNumber);
            ack.acknowledge();
        } catch(RejectedExecutionException e) {
            log.error("[LISTENER][FX-SEND] 실패:{} 큐 가득참 request={}", awsSqsQueueProperties.getFxSend(), tradeNumber, e);
        } finally {
            super.getCountDownLatch().countDown();
        }
    }
}
