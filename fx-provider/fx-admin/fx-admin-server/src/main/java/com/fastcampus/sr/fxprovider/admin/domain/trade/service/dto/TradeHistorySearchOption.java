package com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto;

import com.fastcampus.sr.fxprovider.common.type.trade.TradeStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TradeHistorySearchOption {
    private String tradeNumber;
    private String memberNumber;
    private TradeStatus tradeStatus;


    @Builder
    public TradeHistorySearchOption(String tradeNumber, String memberNumber, TradeStatus tradeStatus) {
        this.tradeNumber = tradeNumber;
        this.memberNumber = memberNumber;
        this.tradeStatus = tradeStatus;
    }
}
