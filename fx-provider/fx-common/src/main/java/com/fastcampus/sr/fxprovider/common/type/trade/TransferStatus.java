package com.fastcampus.sr.fxprovider.common.type.trade;

import com.fastcampus.sr.fxprovider.common.type.TypeDescription;

public enum TransferStatus implements TypeDescription {
    REQUEST("요청"),
    IN_PROGRESS("진행중"),
    CANCELED("취소완료"),
    COMPLETED("처리완료"),
    ;
    private String description;

    TransferStatus(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
