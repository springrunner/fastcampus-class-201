package com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.TradeHistoryDto;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTradeFacade;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.TradeHistoryQueryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.fastcampus.sr.fxprovider.common.Constant.HEADER_MEMBER_NUMBER;


@RestController
public class FxTradeRestController {
    private final FxTradeFacade fxTradeFacade;
    private final TradeHistoryQueryService tradeHistoryQueryService;

    public FxTradeRestController(FxTradeFacade fxTradeFacade, TradeHistoryQueryService tradeHistoryQueryService) {
        this.fxTradeFacade = fxTradeFacade;
        this.tradeHistoryQueryService = tradeHistoryQueryService;
    }

    /**
     * 송금신청
     *
     * @param memberNumber
     * @param command
     * @return 송금신청내역 FxTradeSendResponse
     */
    @PostMapping("/api/v1/trade/send")
    public TradeHistoryDto sendMoney(@RequestHeader(HEADER_MEMBER_NUMBER) String memberNumber, @RequestBody @Valid FxTradeSendCommand command) {
        return TradeHistoryDto.of(fxTradeFacade.sendMoney(memberNumber, command));
    }

    /**
     * 송금내역조회
     *
     * @param memberNumber 회원번호(Header)
     * @param tradeNumber  거래번호
     * @return 송금내역 TradeHistoryDto
     */
    @GetMapping("/api/v1/trade/{tradeNumber}") // TODO 로그인한 사용자의 거래만 조회할 수 있도록 조취해야합니다.
    public TradeHistoryDto getTradeHistory(@RequestHeader(HEADER_MEMBER_NUMBER) String memberNumber, @PathVariable("tradeNumber") String tradeNumber) {
        return TradeHistoryDto.of(tradeHistoryQueryService.findByMemberNumberAndTradeNumber(memberNumber, tradeNumber));
    }
}
