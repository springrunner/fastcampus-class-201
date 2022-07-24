package com.fastcampus.sr.fxprovider.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AdminWebSecurityConfig {
    @Bean
    @ConditionalOnProperty(name = "admin.security.mode", havingValue = "disable", matchIfMissing = true)
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/**").csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/health", "/actuator/helath").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .headers().frameOptions().sameOrigin();
        return httpSecurity.build();
    }
}
