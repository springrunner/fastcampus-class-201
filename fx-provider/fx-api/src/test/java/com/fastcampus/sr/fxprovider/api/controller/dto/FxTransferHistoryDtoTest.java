package com.fastcampus.sr.fxprovider.api.controller.dto;

import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.junit.jupiter.api.Test;

class FxTransferHistoryDtoTest {
    @Test
    void testSerialize() {
        FxTransferHistory fxTransferHistory = FxTransferHistory.builder()
                .build();
        FxTransferHistoryDto sendRespontradeHistoryDtoe = FxTransferHistoryDto.of(fxTransferHistory);

        String json = ObjectMapperUtils.toPrettyJson(sendRespontradeHistoryDtoe);

        System.out.println(json);
    }
}