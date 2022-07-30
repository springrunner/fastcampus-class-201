package com.fastcampus.sr.fxprovider.core.domain.trade.dto;

import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.type.trade.TransferStatus;
import com.fastcampus.sr.fxprovider.core.domain.trade.FxTransferHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FxTransferHistoryDto {
    private String tradeNumber;
    private TransferStatus transferStatus;
    private ZonedDateTime requestDateTime; // 거래일시
    private ZonedDateTime inProgressDateTime; // 진행일시(시스템에서 수취인에게 진행알림시간)
    private ZonedDateTime canceledDateTime; // 취소일시
    private String cancelReason;
    private ZonedDateTime completedDateTime; // 완료일시
    private String memberNumber;

    private Country sendCountry;
    private Currency sendCurrency;
    private BigDecimal sendFxRate;
    private BigDecimal sendMoney;

    private String senderName;
    private String senderEmail;
    private String senderContactNumber;
    private String senderAddress1;
    private String senderAddress2;
    private String senderIdentifyNumber;

    private Country receiveCountry;
    private Currency receiveCurrency;
    private BigDecimal receiveFxRate;
    private BigDecimal receiveMoney;

    private String receiverName;
    private String receiverEmail;
    private String receiverContactNumber;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverIdentifyNumber;

    public FxTransferHistoryDto(String tradeNumber,
                                TransferStatus transferStatus,
                                ZonedDateTime requestDateTime,
                                ZonedDateTime inProgressDateTime,
                                ZonedDateTime canceledDateTime,
                                String cancelReason,
                                ZonedDateTime completedDateTime,
                                String memberNumber,
                                Country sendCountry,
                                Currency sendCurrency,
                                BigDecimal sendFxRate,
                                BigDecimal sendMoney,
                                String senderName,
                                String senderEmail,
                                String senderContactNumber,
                                String senderAddress1,
                                String senderAddress2,
                                String senderIdentifyNumber,
                                Country receiveCountry,
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
        this.transferStatus = transferStatus;
        this.requestDateTime = requestDateTime;
        this.inProgressDateTime = inProgressDateTime;
        this.canceledDateTime = canceledDateTime;
        this.cancelReason = cancelReason;
        this.completedDateTime = completedDateTime;
        this.memberNumber = memberNumber;
        this.sendCountry = sendCountry;
        this.sendCurrency = sendCurrency;
        this.sendFxRate = sendFxRate;
        this.sendMoney = sendMoney;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderContactNumber = senderContactNumber;
        this.senderAddress1 = senderAddress1;
        this.senderAddress2 = senderAddress2;
        this.senderIdentifyNumber = senderIdentifyNumber;
        this.receiveCountry = receiveCountry;
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

    public static FxTransferHistoryDto of(FxTransferHistory source) {
        FxTransferHistoryDto destination = new FxTransferHistoryDto();
        destination.transferStatus = source.getTransferStatus();
        destination.tradeNumber = source.getTradeNumber();
        destination.memberNumber = source.getMemberNumber();

        destination.requestDateTime = source.getRequestDateTime();
        destination.inProgressDateTime = source.getInProgressDateTime();
        destination.completedDateTime = source.getCompletedDateTime();
        destination.canceledDateTime = source.getCanceledDateTime();
        destination.cancelReason = source.getCancelReason();

        destination.sendCountry = source.getSendCountry();
        destination.sendCurrency = source.getSendCurrency();
        destination.sendMoney = source.getSendMoney();
        destination.sendFxRate = source.getSendFxRate();

        destination.senderName = source.getSenderName();
        destination.senderEmail = source.getSenderEmail();
        destination.senderAddress1 = source.getSenderAddress1();
        destination.senderAddress2 = source.getSenderAddress2();
        destination.senderContactNumber = source.getSenderContactNumber();
        destination.senderIdentifyNumber = source.getSenderIdentifyNumber();

        destination.receiveCountry = source.getReceiveCountry();
        destination.receiveCurrency = source.getReceiveCurrency();
        destination.receiveFxRate = source.getReceiveFxRate();
        destination.receiveMoney = source.getReceiveMoney();

        destination.receiverName = source.getReceiverName();
        destination.receiverEmail = source.getReceiverEmail();
        destination.receiverAddress1 = source.getReceiverAddress1();
        destination.receiverAddress2 = source.getReceiverAddress2();
        destination.receiverContactNumber = source.getReceiverContactNumber();
        destination.receiverIdentifyNumber = source.getReceiverIdentifyNumber();

        return destination;
    }

    public String getTransferStatusDesc() {
        return getTransferStatus().getDescription();
    }
}
