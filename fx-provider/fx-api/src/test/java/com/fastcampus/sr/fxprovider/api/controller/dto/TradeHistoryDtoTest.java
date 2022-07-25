package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import org.junit.jupiter.api.Test;

class TradeHistoryDtoTest {
    @Test
    void testSerialize() {
        TradeHistory tradeHistory = TradeHistory.builder()
                .build();
        TradeHistoryDto sendRespontradeHistoryDtoe = TradeHistoryDto.of(tradeHistory);

        String json = ObjectMapperUtils.toPrettyJson(sendRespontradeHistoryDtoe);

        System.out.println(json);
    }
}