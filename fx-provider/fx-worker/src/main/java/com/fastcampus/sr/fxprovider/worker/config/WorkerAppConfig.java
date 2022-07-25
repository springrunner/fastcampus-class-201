package com.fastcampus.sr.fxprovider.worker.config;

import com.fastcampus.sr.fxprovider.clients.aws.config.AwsResourceConfig;
import com.fastcampus.sr.fxprovider.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreConfig.class,
        AwsResourceConfig.class
})
public class WorkerAppConfig {
}
