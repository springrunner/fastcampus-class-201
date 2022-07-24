package com.fastcampus.sr.fxprovider.worker.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 송금거래이벤트 리스너
 */
@Component
public class FxTradeEventListener {

    @KafkaListener(topics = {"startFxTrade"})
    public void receiveStartFxTrade(String content) {

    }
}
