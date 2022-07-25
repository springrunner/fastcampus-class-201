package com.fastcampus.sr.fxprovider.core.domain.trade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
    Optional<TradeHistory> findByMemberNumberAndTradeNumber(String memberNumber, String tradeNumber);

    Optional<TradeHistory> findByTradeNumber(String tradeNumber);
}
