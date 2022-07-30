package com.fastcampus.sr.fxprovider.admin.domain.trade.repository;

import com.fastcampus.sr.fx.provider.core.annotation.IntegrationTest;
import com.fastcampus.sr.fxprovider.admin.domain.trade.service.dto.FxTransferHistorySearchOption;
import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferTradeHistoryRepository;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import com.querydsl.core.QueryResults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class FxTransferHistoryQueryRepositoryTest {
    @Autowired
    FxTransferTradeHistoryRepository fxTransferTradeHistoryRepository;
    @Autowired
    FxTransferTradeHistoryQueryRepository fxTransferTradeHistoryQueryRepository;
    private String memberNumber = "20220724222101";

    @BeforeEach
    void setUp() {

        FxTransferHistory fxTransferHistory = FxTransferHistory.builder()
                .memberNumber(memberNumber)
                .sendCountry(Country.KOR)
                .sendCurrency(Currency.KRW)
                .sendMoney(BigDecimal.valueOf(1_000_000d))
                .sendFxRate(BigDecimal.valueOf(1321.55d))
                .senderName("송금자")
                .senderEmail("sender@fxprovider.com")
                .senderAddress1("강원도 동해시")
                .senderAddress2("어딘가")
                .senderContactNumber("+82-010-0000-0000")
                .senderIdentifyNumber("111111-1111111")
                .receiveCountry(Country.JPN)
                .receiveCurrency(Currency.JPY)
                .receiveMoney(BigDecimal.valueOf(999_999d))
                .receiverName("수취자")
                .receiverEmail("receiver@fxprovider.com")
                .receiverAddress1("오사카 어딘가")
                .receiverAddress2("어딘가")
                .receiverContactNumber("+81-0000-0000-0000")
                .receiverIdentifyNumber("1234-567-8910")
                .receiveFxRate(BigDecimal.valueOf(132.15d))
                .build();
        fxTransferTradeHistoryRepository.save(fxTransferHistory);
    }

    @AfterEach
    void tearDown() {
        fxTransferTradeHistoryRepository.deleteAll();
    }

    @Test
    void testSearch() {
        PageRequest pageable = PageRequest.of(0, 20);
        FxTransferHistorySearchOption searchOption = FxTransferHistorySearchOption.builder().build();

        QueryResults<FxTransferHistoryDto> queryResults = fxTransferTradeHistoryQueryRepository.search(searchOption, pageable);

        assertThat(queryResults.getResults()).hasSize(1);
    }

    @Test
    void testSearchNotMatchMemberNumber() {
        PageRequest pageable = PageRequest.of(0, 20);
        FxTransferHistorySearchOption searchOption = FxTransferHistorySearchOption.builder()
                .memberNumber("NOT-EXIST-MEMBER-NUMBER")
                .build();

        QueryResults<FxTransferHistoryDto> queryResults = fxTransferTradeHistoryQueryRepository.search(searchOption, pageable);

        assertThat(queryResults.getResults()).hasSize(0);
    }
}