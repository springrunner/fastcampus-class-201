package com.fastcampus.sr.fxprovider.core.domain.trade.dto;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.type.trade.TradeStatus;
import com.fastcampus.sr.fxprovider.core.domain.trade.TradeHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TradeHistoryDto {
    private String tradeNumber;
    private TradeStatus tradeStatus;
    private ZonedDateTime requestDateTime; // 거래일시
    private ZonedDateTime inProgressDateTime; // 진행일시(시스템에서 수취인에게 진행알림시간)
    private ZonedDateTime canceledDateTime; // 취소일시
    private String cancelReason;
    private ZonedDateTime completedDateTime; // 완료일시
    private String memberNumber;
    private Currency sendCurrency;
    private BigDecimal sendFxRate;
    private BigDecimal sendMoney;

    private String senderName;
    private String senderEmail;
    private String senderContactNumber;
    private String senderAddress1;
    private String senderAddress2;
    private String senderIdentifyNumber;

    private Currency receiveCurrency;
    private BigDecimal receiveFxRate;
    private BigDecimal receiveMoney;

    private String receiverName;
    private String receiverEmail;
    private String receiverContactNumber;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverIdentifyNumber;

    public TradeHistoryDto(String tradeNumber,
                           TradeStatus tradeStatus,
                           ZonedDateTime requestDateTime,
                           ZonedDateTime inProgressDateTime,
                           ZonedDateTime canceledDateTime,
                           String cancelReason,
                           ZonedDateTime completedDateTime,
                           String memberNumber,
                           Currency sendCurrency,
                           BigDecimal sendFxRate,
                           BigDecimal sendMoney,
                           String senderName,
                           String senderEmail,
                           String senderContactNumber,
                           String senderAddress1,
                           String senderAddress2,
                           String senderIdentifyNumber,
                           Currency receiveCurrency,
                           BigDecimal receiveFxRate,
                           BigDecimal receiveMoney,
                           String receiverName,
                           String receiverEmail,
                           String receiverContactNumber,
                           String receiverAddress1,
                           String receiverAddress2,
                           String receiverIdentifyNumber) {
        this.tradeNumber = tradeNumber;
        this.tradeStatus = tradeStatus;
        this.requestDateTime = requestDateTime;
        this.inProgressDateTime = inProgressDateTime;
        this.canceledDateTime = canceledDateTime;
        this.cancelReason = cancelReason;
        this.completedDateTime = completedDateTime;
        this.memberNumber = memberNumber;
        this.sendCurrency = sendCurrency;
        this.sendFxRate = sendFxRate;
        this.sendMoney = sendMoney;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContactNumber = senderContactNumber;
        this.senderAddress1 = senderAddress1;
        this.senderAddress2 = senderAddress2;
        this.senderIdentifyNumber = senderIdentifyNumber;
        this.receiveCurrency = receiveCurrency;
        this.receiveFxRate = receiveFxRate;
        this.receiveMoney = receiveMoney;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.receiverContactNumber = receiverContactNumber;
        this.receiverAddress1 = receiverAddress1;
        this.receiverAddress2 = receiverAddress2;
        this.receiverIdentifyNumber = receiverIdentifyNumber;
    }

    public static TradeHistoryDto of(TradeHistory tradeHistory) {
        TradeHistoryDto tradeHistoryDto = new TradeHistoryDto();
        tradeHistoryDto.tradeStatus = tradeHistory.getTradeStatus();
        tradeHistoryDto.tradeNumber = tradeHistory.getTradeNumber();
        tradeHistoryDto.memberNumber = tradeHistory.getMemberNumber();

        tradeHistoryDto.requestDateTime = tradeHistory.getRequestDateTime();
        tradeHistoryDto.inProgressDateTime = tradeHistory.getInProgressDateTime();
        tradeHistoryDto.completedDateTime = tradeHistory.getCompletedDateTime();
        tradeHistoryDto.canceledDateTime = tradeHistory.getCanceledDateTime();
        tradeHistoryDto.cancelReason = tradeHistory.getCancelReason();

        tradeHistoryDto.sendCurrency = tradeHistory.getSendCurrency();
        tradeHistoryDto.sendMoney = tradeHistory.getSendMoney();
        tradeHistoryDto.sendFxRate = tradeHistory.getSendFxRate();

        tradeHistoryDto.senderName = tradeHistory.getSenderName();
        tradeHistoryDto.senderEmail = tradeHistory.getSenderEmail();
        tradeHistoryDto.senderAddress1 = tradeHistory.getSenderAddress1();
        tradeHistoryDto.senderAddress2 = tradeHistory.getSenderAddress2();
        tradeHistoryDto.senderContactNumber = tradeHistory.getSenderContactNumber();
        tradeHistoryDto.senderIdentifyNumber = tradeHistory.getSenderIdentifyNumber();

        tradeHistoryDto.receiveCurrency = tradeHistory.getReceiveCurrency();
        tradeHistoryDto.receiveFxRate = tradeHistory.getReceiveFxRate();
        tradeHistoryDto.receiveMoney = tradeHistory.getReceiveMoney();

        tradeHistoryDto.receiverName = tradeHistory.getReceiverName();
        tradeHistoryDto.receiverEmail = tradeHistory.getReceiverEmail();
        tradeHistoryDto.receiverAddress1 = tradeHistory.getReceiverAddress1();
        tradeHistoryDto.receiverAddress2 = tradeHistory.getReceiverAddress2();
        tradeHistoryDto.receiverContactNumber = tradeHistory.getReceiverContactNumber();
        tradeHistoryDto.receiverIdentifyNumber = tradeHistory.getReceiverIdentifyNumber();

        return tradeHistoryDto;
    }

    public String getTradeStatusDesc() {
        return getTradeStatus().getDescription();
    }
}
