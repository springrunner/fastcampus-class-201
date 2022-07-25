package com.fastcampus.sr.fxprovider.api.infrastructure.message;

import com.fastcampus.sr.fxprovider.api.exception.QueueMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueMessageSender {
    private final QueueMessagingTemplate queueMessagingTemplate;

    public QueueMessageSender(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void sendMessage(String queueName, Object message) {
        try {
            queueMessagingTemplate.convertAndSend(queueName, message);
        } catch (Exception e) {
            log.error("[fx-api][queue-message]전송실패: {}", message, e);
            throw new QueueMessageException(e);
        }
    }
}
