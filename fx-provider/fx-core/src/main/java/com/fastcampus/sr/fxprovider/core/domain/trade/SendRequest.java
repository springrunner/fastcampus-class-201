package com.fastcampus.sr.fxprovider.core.domain.trade;

import com.fastcampus.sr.fxprovider.common.enums.Currency;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SendRequest {
    private Sender sender;
    private Currency sendCurrency;
    private Double sendFxRate;
    private BigDecimal sendMoney;

    private Receiver receiver;
    private Currency receiveCurrency;
    private Double receiveFxRate;
    private BigDecimal receiveMoney;

    @Builder
    public SendRequest(Sender sender,
                       Currency sendCurrency,
                       Double sendFxRate,
                       BigDecimal sendMoney,
                       Receiver receiver,
                       Currency receiveCurrency,
                       Double receiveFxRate,
                       BigDecimal receiveMoney) {
        this.sender = sender;
        this.sendCurrency = sendCurrency;
        this.sendFxRate = sendFxRate;
        this.sendMoney = sendMoney;
        this.receiver = receiver;
        this.receiveCurrency = receiveCurrency;
        this.receiveFxRate = receiveFxRate;
        this.receiveMoney = receiveMoney;
    }
}
