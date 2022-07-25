package com.fastcampus.sr.fxprovider.clients.aws.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ConfigurationProperties("aws.sqs.queue")
public class AwsSqsQueueProperties {
    private String fxSend;
}
