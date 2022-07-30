package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Country;
import com.fastcampus.sr.fxprovider.common.enums.Currency;
import com.fastcampus.sr.fxprovider.common.type.trade.TransferStatus;
import com.fastcampus.sr.fxprovider.core.BaseEntity;
import com.mysema.commons.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

import static com.fastcampus.sr.fxprovider.common.type.trade.TransferStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FxTransferHistory extends BaseEntity {
    @Enumerated
    @Column(name = "transfer_status", columnDefinition = "VARCHAR(50) COMMENT '송금상태'")
    private TransferStatus transferStatus;

    @Column(name = "request_date_time", columnDefinition = "TIMESTAMP COMMENT '요청일시'")
    private ZonedDateTime requestDateTime; // 거래일시
    @Column(name = "in_progress_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '진행일시'")
    private ZonedDateTime inProgressDateTime; // 진행일시(시스템에서 수취인에게 진행알림시간)
    @Column(name = "canceled_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '취소일시'")
    private ZonedDateTime canceledDateTime; // 취소일시

    @Column(name = "cancel_reason", columnDefinition = "VARCHAR(100) COMMENT '취소사유'")
    private String cancelReason; // 취소사유
    @Column(name = "completed_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '완료일시'")
    private ZonedDateTime completedDateTime; // 완료일시

    @Column(name = "member_number", columnDefinition = "VARCHAR(100) COMMENT '회원번호'")
    private String memberNumber;

    @Column(name = "trade_number", columnDefinition = "VARCHAR(100) COMMENT '거래번호'")
    private String tradeNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "send_country", columnDefinition = "VARCHAR(10) COMMENT '송금국가'")
    private Country sendCountry;
    @Enumerated(EnumType.STRING)
    @Column(name = "send_currency", columnDefinition = "VARCHAR(10) COMMENT '송금통화'")
    private Currency sendCurrency;
    @Column(name = "send_fx_rate", columnDefinition = "NUMERIC(10,6) COMMENT '송금환율'")
    private BigDecimal sendFxRate;
    @Column(name = "send_money", columnDefinition = "NUMERIC(10,2) COMMENT '송금액'")
    private BigDecimal sendMoney;

    @Column(name = "sender_name", columnDefinition = "VARCHAR(50) COMMENT '송금자 이름'")
    private String senderName;
    @Column(name = "sender_email", columnDefinition = "VARCHAR(100) COMMENT '송금자 이메일'")
    private String senderEmail;
    @Column(name = "sender_contact_number", columnDefinition = "VARCHAR(100) COMMENT '송금자 연락처'")
    private String senderContactNumber;
    @Column(name = "sender_address_1", columnDefinition = "VARCHAR(100) COMMENT '송금자 주소1'")
    private String senderAddress1;
    @Column(name = "sender_address_2", columnDefinition = "VARCHAR(100) COMMENT '송금자 주소2'")
    private String senderAddress2;
    @Column(name = "sender_identify_number", columnDefinition = "VARCHAR(30) COMMENT '송금자 식별번호(여권,외국인번호 등)'")
    private String senderIdentifyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "receive_country", columnDefinition = "VARCHAR(10) COMMENT '수취국가'")
    private Country receiveCountry;
    @Enumerated(EnumType.STRING)
    @Column(name = "receive_currency", columnDefinition = "VARCHAR(10) COMMENT '수취통화'")
    private Currency receiveCurrency;
    @Column(name = "receive_fx_rate", columnDefinition = "NUMERIC(10,6) COMMENT '수취환율'")
    private BigDecimal receiveFxRate;
    @Column(name = "receive_money", columnDefinition = "NUMERIC(10,2) COMMENT '수취금액'")
    private BigDecimal receiveMoney;

    @Column(name = "receiver_name", columnDefinition = "VARCHAR(50) COMMENT '수취자 이름'")
    private String receiverName;
    @Column(name = "receiver_email", columnDefinition = "VARCHAR(100) COMMENT '수취자 이름'")
    private String receiverEmail;
    @Column(name = "receiver_contact_number", columnDefinition = "VARCHAR(100) COMMENT '수취자 연락처'")
    private String receiverContactNumber;
    @Column(name = "receiver_address_1", columnDefinition = "VARCHAR(100) COMMENT '수취자 주소1'")
    private String receiverAddress1;
    @Column(name = "receiver_address_2", columnDefinition = "VARCHAR(100) COMMENT '수취자 주소2'")
    private String receiverAddress2;
    @Column(name = "receiver_identify_number", columnDefinition = "VARCHAR(30) COMMENT '수취자 식별번호(여권,외국인번호 등)'")
    private String receiverIdentifyNumber;

    @Builder
    public FxTransferHistory(
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
        this.transferStatus = REQUEST;
        this.requestDateTime = ZonedDateTime.now();
        this.memberNumber = memberNumber;
        this.tradeNumber = UUID.randomUUID().toString();

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

    public void inProgress() {
        Assert.isTrue(
                this.transferStatus == REQUEST,
                String.format("'송금요청' 상태여야 합니다(상태: %s).", this.transferStatus.getDescription()));

        this.transferStatus = IN_PROGRESS;
        this.inProgressDateTime = ZonedDateTime.now();
    }

    public void cancel(String reason) {
        Assert.isTrue(
                Arrays.asList(REQUEST, IN_PROGRESS).contains(this.transferStatus),
                String.format("취소가능한 상태가 아닙니다(상태: %s).", this.transferStatus.getDescription()));

        this.transferStatus = CANCELED;
        this.canceledDateTime = ZonedDateTime.now();
        this.cancelReason = reason;
    }

    public void complete() {
        Assert.isTrue(
                this.transferStatus == IN_PROGRESS,
                String.format("송금진행중 상태여야 합니다(상태: %s).", this.transferStatus.getDescription()));

        this.transferStatus = COMPLETED;
        this.completedDateTime = ZonedDateTime.now();
    }
}
