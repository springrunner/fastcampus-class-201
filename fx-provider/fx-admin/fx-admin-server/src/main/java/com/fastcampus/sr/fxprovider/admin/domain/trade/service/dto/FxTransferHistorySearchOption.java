package com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto;

import com.fastcampus.sr.fxprovider.common.type.trade.TransferStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxTransferHistorySearchOption {
    private String tradeNumber;
    private String memberNumber;
    private TransferStatus transferStatus;


    @Builder
    public FxTransferHistorySearchOption(String tradeNumber, String memberNumber, TransferStatus transferStatus) {
        this.tradeNumber = tradeNumber;
        this.memberNumber = memberNumber;
        this.transferStatus = transferStatus;
    }
}
