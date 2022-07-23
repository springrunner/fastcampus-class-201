package com.fastcampus.sr.fxprovider.api.controller;

import com.fastcampus.sr.fxprovider.api.controller.dto.FxTradeSendCommand;
import com.fastcampus.sr.fxprovider.api.controller.dto.FxTradeSendResponse;
import com.fastcampus.sr.fxprovider.api.service.FxTradeCommandHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FxTradeRestController {
    private final FxTradeCommandHandler fxTradeCommandHandler;

    public FxTradeRestController(FxTradeCommandHandler fxTradeCommandHandler) {
        this.fxTradeCommandHandler = fxTradeCommandHandler;
    }

    @PostMapping("/api/v1/send-money")
    public FxTradeSendResponse sendMoney(@RequestBody FxTradeSendCommand command) {
        return fxTradeCommandHandler.sendMoney(command);
    }
}
