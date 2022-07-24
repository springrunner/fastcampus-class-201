package com.fastcampus.sr.fxprovider.common.exception;

import static com.fastcampus.sr.fxprovider.common.exception.ApplicationErrorCode.*;

public class NotFoundTradeHistoryException extends ApplicationException {
    public NotFoundTradeHistoryException(String tradeNumber) {
        super(NOT_FOUND_TRADE_HISTORY.getCode(), String.format("거래내역을 찾을 수 없습니다(거래번호: %s)", tradeNumber));
    }

    @Override
    public ApplicationErrorCode getErrorCode() {
        return NOT_FOUND_TRADE_HISTORY;
    }
}
