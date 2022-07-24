package com.fastcampus.sr.fxprovider.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.fastcampus.sr.fxprovider.admin.domain"})
public class AdminJpaConfiguration {
}
