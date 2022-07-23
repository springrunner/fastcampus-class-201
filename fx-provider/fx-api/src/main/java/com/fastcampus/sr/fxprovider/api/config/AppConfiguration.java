package com.fastcampus.sr.fxprovider.api.config;

import com.fastcampus.sr.fxprovider.core.config.CoreConfig;
import com.fastcampus.sr.fxprovider.core.config.CoreJpaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfig.class
})
public class AppConfiguration {
}
