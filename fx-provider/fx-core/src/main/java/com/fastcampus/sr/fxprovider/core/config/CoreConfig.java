package com.fastcampus.sr.fxprovider.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreJpaConfig.class})
public class CoreConfig {

}
