package com.fastcampus.sr.fxprovider.clients.aws.config;

import com.fastcampus.sr.fx.provider.core.annotation.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@ActiveProfiles({"aws-resource", "aws-resource-localstack", "test"})
@IntegrationTest(classes = {AwsResourceConfig.class})
class AwsSqsQueuePropertiesTest {
    @Autowired
    private AwsSqsQueueProperties sqsQueueProperties;

    @Test
    void test() {
        assertThat(sqsQueueProperties.getFxSend()).isEqualTo("fx-send-localstack");
    }
}