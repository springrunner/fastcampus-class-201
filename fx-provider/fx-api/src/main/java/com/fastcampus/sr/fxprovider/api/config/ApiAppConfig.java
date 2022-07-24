package com.fastcampus.sr.fxprovider.api.config;

import com.fastcampus.sr.fxprovider.clients.toast.config.ToastConfig;
import com.fastcampus.sr.fxprovider.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfig.class,
        ToastConfig.class
})
public class ApiAppConfig {
}
