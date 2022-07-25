package com.fastcampus.sr.fxprovider.worker.listener;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CountDownLatch;

@Getter
@Setter
public abstract class LatchListener {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
}
