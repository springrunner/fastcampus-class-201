package com.fastcampus.sr.fxprovider.collector.config;

import com.fastcampus.sr.fxprovider.clients.currency.layer.config.CurrencyLayerClientConfig;
import com.fastcampus.sr.fxprovider.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfig.class,
        CurrencyLayerClientConfig.class
})
public class CollectorAppConfig {
}
