package com.fastcampus.sr.fxprovider.core.domain.trade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FxTransferTradeHistoryRepository extends JpaRepository<FxTransferHistory, Long> {
    Optional<FxTransferHistory> findByMemberNumberAndTradeNumber(String memberNumber, String tradeNumber);

    Optional<FxTransferHistory> findByTradeNumber(String tradeNumber);
}
