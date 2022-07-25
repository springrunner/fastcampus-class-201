package com.fastcampus.sr.fxprovider.clients.aws.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("aws.sns.topic")
public class AwsSnsTopicProperties {
    private String fxTradeEvent;
}
