package com.fastcampus.sr.fxprovider.core.trade;

import com.fastcampus.sr.fxprovider.common.currency.Currency;
import com.fastcampus.sr.fxprovider.common.type.trade.TradeStatus;
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
import java.util.Date;

import static com.fastcampus.sr.fxprovider.common.type.trade.TradeStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TradeHistory extends BaseEntity {
    @Enumerated
    @Column(name = "trade_status", columnDefinition = "VARCHAR(50) COMMENT '거래상태'")
    private TradeStatus tradeStatus;

    @Column(name = "request_date_time", columnDefinition = "TIMESTAMP COMMENT '요청일시'")
    private ZonedDateTime requestDateTime; // 거래일시
    @Column(name = "in_progress_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '진행일시'")
    private ZonedDateTime inProgressDateTime; // 진행일시(시스템에서 수취인에게 진행알림시간)
    @Column(name = "canceled_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '취소일시'")
    private ZonedDateTime canceledDateTime; // 취소일시
    @Column(name = "completed_date_time", nullable = true, columnDefinition = "TIMESTAMP COMMENT '완료일시'")
    private ZonedDateTime completedDateTime; // 완료일시

    @Enumerated(EnumType.STRING)
    @Column(name = "send_currency", columnDefinition = "VARCHAR(10) COMMENT '송금통화'")
    private Currency sendCurrency;
    @Column(name = "send_fx_rate", columnDefinition = "DOUBLE COMMENT '송금환율'")
    private Double sendFxRate;
    @Column(name = "send_money", columnDefinition = "NUMERIC(10,2) COMMENT '송금환율'")
    private Double sendMoney;

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
    @Column(name = "receive_currency", columnDefinition = "VARCHAR(10) COMMENT '수취통화'")
    private Currency receiveCurrency;
    @Column(name = "receive_fx_rate", columnDefinition = "DOUBLE COMMENT '수취환율'")
    private Double receiveFxRate;
    @Column(name = "receive_money", columnDefinition = "NUMERIC(10,2) COMMENT '수취환율'")
    private Double receiveMoney;

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
    public TradeHistory(Currency sendCurrency,
                        Double sendFxRate,
                        Double sendMoney,
                        String senderName,
                        String senderEmail,
                        String senderContactNumber,
                        String senderAddress1,
                        String senderAddress2,
                        String senderIdentifyNumber,
                        Currency receiveCurrency,
                        Double receiveFxRate,
                        Double receiveMoney,
                        String receiverName,
                        String receiverEmail,
                        String receiverContactNumber,
                        String receiverAddress1,
                        String receiverAddress2,
                        String receiverIdentifyNumber) {
        this.tradeStatus = REQUEST;
        this.requestDateTime = ZonedDateTime.now();

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

    public void inProgress() {
        Assert.isTrue(
                this.tradeStatus == REQUEST,
                String.format("'송금요청' 상태여야 합니다(상태: %s).", this.tradeStatus.getDescription()));

        this.tradeStatus = IN_PROGRESS;
        this.inProgressDateTime = ZonedDateTime.now();
    }

    public void cancel() {
        Assert.isTrue(
                Arrays.asList(REQUEST, IN_PROGRESS).contains(this.tradeStatus),
                String.format("취소가능한 상태가 아닙니다(상태: %s).", this.tradeStatus.getDescription()));

        this.tradeStatus = CANCELED;
        this.canceledDateTime = ZonedDateTime.now();
    }

    public void complete() {
        Assert.isTrue(
                this.tradeStatus == IN_PROGRESS,
                String.format("송금진행중 상태여야 합니다(상태: %s).", this.tradeStatus.getDescription()));

        this.tradeStatus = COMPLETED;
        this.completedDateTime = ZonedDateTime.now();
    }
}
