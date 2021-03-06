package com.fastcampus.sr.fxprovider.collector.scheduler;

import com.fastcampus.sr.fxprovider.collector.service.CurrencyLayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.fastcampus.sr.fxprovider.collector.config.CollectorSchedulerConfig.SHED_LOCK_DEFAULT_ZONE;

@Slf4j
@Component
public class FxCurrencyRefreshScheduler {
    private final CurrencyLayerService currencyLayerService;

    public FxCurrencyRefreshScheduler(CurrencyLayerService currencyLayerService) {
        this.currencyLayerService = currencyLayerService;
    }

    /**
     * 15분에 한번씩 환율정보 조회
     */
    @Scheduled(cron = "* */15 * * * *", zone = SHED_LOCK_DEFAULT_ZONE)
    public void updateFxCurrencies() {
        log.info("Execute updateFxCurrencies.");
        currencyLayerService.updateFxCurrencies();
    }
}
