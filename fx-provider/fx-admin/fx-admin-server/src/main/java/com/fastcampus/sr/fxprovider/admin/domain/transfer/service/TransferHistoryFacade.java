package com.fastcampus.sr.fxprovider.admin.domain.transfer.service;

import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 파사드 패턴은 트랜잭션의 영향범위를 제한하고 외부시스템 클라이언트를 사용하는 용도로 만듭니다.
 */
@Component
public class TransferHistoryFacade {
    private final FxTransferHistoryFinder finder;
    private final FxTransferHistoryEditor editor;

    public TransferHistoryFacade(FxTransferHistoryFinder finder, FxTransferHistoryEditor editor) {
        this.finder = finder;
        this.editor = editor;
    }

    public Page<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable) {
        return finder.search(searchOption, pageable);
    }

    public FxTransferHistoryDto cancel(String tradeNumber, String cancelReason) {
        //TODO 송금자와 수취인에게 송금취소처리 사유를 전송해야함
        return editor.cancel(tradeNumber, cancelReason);
    }
}
