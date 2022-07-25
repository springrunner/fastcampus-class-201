package com.fastcampus.sr.fxprovider.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = {"com.fastcampus.sr.fxprovider.core.domain"})
@EnableJpaRepositories(basePackages = {"com.fastcampus.sr.fxprovider.core.domain"})
public class CoreJpaConfig {
}
