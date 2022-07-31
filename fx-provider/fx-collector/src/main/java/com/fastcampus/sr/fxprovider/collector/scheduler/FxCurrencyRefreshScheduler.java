package com.fastcampus.sr.fxprovider.collector.scheduler;

import com.fastcampus.sr.fxprovider.collector.service.FxCurrencyRateFacade;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.fastcampus.sr.fxprovider.collector.config.CollectorSchedulerConfig.SHED_LOCK_DEFAULT_ZONE;

@Slf4j
@Component
public class FxCurrencyRefreshScheduler {
    private final FxCurrencyRateFacade fxCurrencyRateFacade;

    public FxCurrencyRefreshScheduler(FxCurrencyRateFacade fxCurrencyRateFacade) {
        this.fxCurrencyRateFacade = fxCurrencyRateFacade;
    }

    /**
     * 15분에 한번씩 환율정보 조회
     */
    @Scheduled(cron = "0 */15 * * * *", zone = SHED_LOCK_DEFAULT_ZONE)
    @SchedulerLock(name= "FxCurrencyRefreshScheduler_updateFxCurrencies",
            lockAtLeastFor = "PT5M", // 최소 5분동안 잠금 유지
            lockAtMostFor = "PT14M") // 실행노드가 종료된 경우 잠금유지하는 기간
    public void updateFxCurrencies() {
        log.info("Execute updateFxCurrencies.");
        fxCurrencyRateFacade.updateFxCurrencyRates();
    }
}
