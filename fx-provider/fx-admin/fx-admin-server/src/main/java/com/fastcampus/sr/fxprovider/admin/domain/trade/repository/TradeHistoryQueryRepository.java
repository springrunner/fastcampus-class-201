package com.fastcampus.sr.fxprovider.admin.domain.trade.repository;

import com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto.TradeHistorySearchOption;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.TradeHistoryDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.fastcampus.sr.fxprovider.core.domain.trade.QTradeHistory.tradeHistory;

@Repository
public class TradeHistoryQueryRepository extends QuerydslRepositorySupport {

    public TradeHistoryQueryRepository() {
        super(TradeHistory.class);
    }

    public QueryResults<TradeHistoryDto> search(TradeHistorySearchOption searchOption, Pageable pageable) {
        return getQuerydsl().createQuery()
                .select(
                        Projections.constructor(
                                TradeHistoryDto.class,
                                tradeHistory.tradeNumber,
                                tradeHistory.tradeStatus,
                                tradeHistory.requestDateTime,
                                tradeHistory.inProgressDateTime,
                                tradeHistory.canceledDateTime,
                                tradeHistory.cancelReason,
                                tradeHistory.completedDateTime,
                                tradeHistory.memberNumber,
                                tradeHistory.sendCurrency,
                                tradeHistory.sendFxRate,
                                tradeHistory.sendMoney,
                                tradeHistory.senderName,
                                tradeHistory.senderEmail,
                                tradeHistory.senderContactNumber,
                                tradeHistory.senderAddress1,
                                tradeHistory.senderAddress2,
                                tradeHistory.senderIdentifyNumber,
                                tradeHistory.receiveCurrency,
                                tradeHistory.receiveFxRate,
                                tradeHistory.receiveMoney,
                                tradeHistory.receiverName,
                                tradeHistory.receiverEmail,
                                tradeHistory.receiverContactNumber,
                                tradeHistory.receiverAddress1,
                                tradeHistory.receiverAddress2,
                                tradeHistory.receiverIdentifyNumber
                        )
                ).from(tradeHistory)
                .where(
                        hasTradeNumber(searchOption),
                        hasTradeStatus(searchOption),
                        hasMemberNumber(searchOption))
                .orderBy(tradeHistory.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    private BooleanExpression hasTradeNumber(TradeHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getTradeNumber()) ? tradeHistory.tradeNumber.eq(searchOption.getTradeNumber()) : null;
    }

    private BooleanExpression hasTradeStatus(TradeHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getTradeStatus()) ? tradeHistory.tradeStatus.eq(searchOption.getTradeStatus()) : null;
    }

    private BooleanExpression hasMemberNumber(TradeHistorySearchOption searchOption) {
        return Objects.nonNull(searchOption.getMemberNumber()) ? tradeHistory.memberNumber.eq(searchOption.getMemberNumber()) : null;
    }
}
