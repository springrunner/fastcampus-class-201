package com.fastcampus.sr.fxprovider.api.config;

import com.fastcampus.sr.fxprovider.api.config.authentication.HeaderAuthorizationCheckFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@EnableConfigurationProperties({AuthenticationConfigurationProperties.class})
@ConditionalOnProperty(prefix = "authentication", name = "enable", havingValue = "true")
public class HeaderAuthorizationCheckFilterConfiguration {
    private final AuthenticationConfigurationProperties authenticationConfigurationProperties;

    public HeaderAuthorizationCheckFilterConfiguration(AuthenticationConfigurationProperties authenticationConfigurationProperties) {
        this.authenticationConfigurationProperties = authenticationConfigurationProperties;
    }

    @Bean
    public FilterRegistrationBean headerAuthorizationFilter() {
        HeaderAuthorizationCheckFilter headerAuthorizationCheckFilter = new HeaderAuthorizationCheckFilter(
                authenticationConfigurationProperties.getPaths(),
                authenticationConfigurationProperties.getAuthKeys()
        );

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(headerAuthorizationCheckFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);

        return filterRegistrationBean;
    }
}
