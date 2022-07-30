package com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller;

import com.fastcampus.sr.fxprovider.api.domain.fxtrade.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTransferTradeHistoryQueryService;
import com.fastcampus.sr.fxprovider.api.domain.fxtrade.service.FxTransferFacade;
import com.fastcampus.sr.fxprovider.core.domain.trade.dto.FxTransferHistoryDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.fastcampus.sr.fxprovider.common.Constant.HEADER_MEMBER_NUMBER;


@RestController
public class FxTransferRestController {
    private final FxTransferFacade fxTransferFacade;

    public FxTransferRestController(FxTransferFacade fxTransferFacade) {
        this.fxTransferFacade = fxTransferFacade;
    }

    /**
     * 송금신청
     *
     * @param memberNumber
     * @param command
     * @return 송금신청내역 FxTradeSendResponse
     */
    @PostMapping("/api/v1/trade/send")
    public FxTransferHistoryDto sendMoney(@RequestHeader(HEADER_MEMBER_NUMBER) String memberNumber, @RequestBody @Valid FxTradeSendCommand command) {
        return fxTransferFacade.sendMoney(memberNumber, command);
    }

    /**
     * 송금내역조회
     *
     * @param memberNumber 회원번호(Header)
     * @param tradeNumber  거래번호
     * @return 송금내역 TradeHistoryDto
     */
    @GetMapping("/api/v1/trade/{tradeNumber}") // TODO 로그인한 사용자의 거래만 조회할 수 있도록 조취해야합니다.
    public FxTransferHistoryDto getTradeHistory(@RequestHeader(HEADER_MEMBER_NUMBER) String memberNumber, @PathVariable("tradeNumber") String tradeNumber) {
        return fxTransferFacade.findByMemberNumberAndTradeNumber(memberNumber, tradeNumber);
    }
}
