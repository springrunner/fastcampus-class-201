package com.fastcampus.sr.fxprovider.admin.config;

import com.fastcampus.sr.fxprovider.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfig.class
})
public class AppConfig {
}
