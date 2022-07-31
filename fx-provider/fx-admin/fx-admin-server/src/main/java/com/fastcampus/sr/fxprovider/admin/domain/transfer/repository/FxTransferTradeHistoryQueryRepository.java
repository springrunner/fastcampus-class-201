package com.fastcampus.sr.fxprovider.admin.domain.transfer.repository;

import com.fastcampus.sr.fxprovider.admin.domain.transfer.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.fastcampus.sr.fxprovider.core.domain.trade.QFxTransferHistory.fxTransferHistory;


@Repository
public class FxTransferTradeHistoryQueryRepository extends QuerydslRepositorySupport {

    public FxTransferTradeHistoryQueryRepository() {
        super(FxTransferHistory.class);
    }

    public QueryResults<FxTransferHistoryDto> search(FxTransferHistorySearchOption searchOption, Pageable pageable) {
        return getQuerydsl().createQuery()
                .select(
                        Projections.constructor(
                                FxTransferHistoryDto.class,
                                fxTransferHistory.tradeNumber,
                                fxTransferHistory.transferStatus,
                                fxTransferHistory.requestDateTime,
                                fxTransferHistory.inProgressDateTime,
                                fxTransferHistory.canceledDateTime,
                                fxTransferHistory.cancelReason,
                                fxTransferHistory.completedDateTime,
                                fxTransferHistory.memberNumber,
                                fxTransferHistory.sendCountry,
                                fxTransferHistory.sendCurrency,
                                fxTransferHistory.sendFxRate,
                                fxTransferHistory.sendMoney,
                                fxTransferHistory.senderName,
                                fxTransferHistory.senderEmail,
                                fxTransferHistory.senderContactNumber,
                                fxTransferHistory.senderAddress1,
                                fxTransferHistory.senderAddress2,
                                fxTransferHistory.senderIdentifyNumber,
                                fxTransferHistory.receiveCountry,
                                fxTransferHistory.receiveCurrency,
                                fxTransferHistory.receiveFxRate,
                                fxTransferHistory.receiveMoney,
                                fxTransferHistory.receiverName,
                                fxTransferHistory.receiverEmail,
                                fxTransferHistory.receiverContactNumber,
                                fxTransferHistory.receiverAddress1,
                                fxTransferHistory.receiverAddress2,
                                fxTransferHistory.receiverIdentifyNumber
                        )
                ).from(fxTransferHistory)
                .where(
                        hasTradeNumber(searchOption),
                        hasTradeStatus(searchOption),
                        hasMemberNumber(searchOption))
                .orderBy(fxTransferHistory.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    private BooleanExpression hasTradeNumber(FxTransferHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getTradeNumber()) ? fxTransferHistory.tradeNumber.eq(searchOption.getTradeNumber()) : null;
    }

    private BooleanExpression hasTradeStatus(FxTransferHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getTransferStatus()) ? fxTransferHistory.transferStatus.eq(searchOption.getTransferStatus()) : null;
    }

    private BooleanExpression hasMemberNumber(FxTransferHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getMemberNumber()) ? fxTransferHistory.memberNumber.eq(searchOption.getMemberNumber()) : null;
    }
}
