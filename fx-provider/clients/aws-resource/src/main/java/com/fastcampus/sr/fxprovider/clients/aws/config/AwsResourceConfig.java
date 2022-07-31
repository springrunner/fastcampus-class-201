package com.fastcampus.sr.fxprovider.clients.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;
import com.amazonaws.services.sqs.buffered.QueueBufferConfig;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fastcampus.sr.fxprovider.common.util.ObjectMapperUtils;
import io.awspring.cloud.core.env.ResourceIdResolver;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
@EnableConfigurationProperties({AwsSqsQueueProperties.class, AwsSnsTopicProperties.class})
public class AwsResourceConfig {

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(ObjectMapperUtils.getObjectMapper());
        messageConverter.setSerializedPayloadClass(String.class);
        return new QueueMessagingTemplate(amazonSQSAsync, (ResourceIdResolver) null, messageConverter);
    }

    @Profile("!local&!test")
    public class ProdAwsConfig {
        @Bean(destroyMethod = "shutdown")
        public AmazonSQSBufferedAsyncClient amazonSQS() {
            AmazonSQSAsync amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
            return new AmazonSQSBufferedAsyncClient(amazonSQSAsync);
        }

        @Primary
        @Bean(destroyMethod = "shutdown")
        public AmazonSNS amazonSNS() {
            return AmazonSNSClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
        }
    }

    @Configuration
    @EnableConfigurationProperties({AwsSqsQueueProperties.class, AwsSnsTopicProperties.class})
    @ConditionalOnProperty(value = "aws.sqs.mode", havingValue = "aws-resource-localstack")
    public class LocalAwsConfig {
        /**
         * LocalStackContainer 구동을 위해서는 docker 가 필요하네요. docker desktop 을 설치하세요.
         * @return
         */
        @Bean(initMethod = "start", destroyMethod = "close")
        public LocalStackContainer localStackContainer() {
            return new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
                    .withServices(
                            LocalStackContainer.Service.SQS,
                            LocalStackContainer.Service.SNS
                    );
        }

        @Primary
        @Bean(destroyMethod = "shutdown")
        public AmazonSQSBufferedAsyncClient amazonSQS(LocalStackContainer localStackContainer, AwsSqsQueueProperties properties) {
            QueueBufferConfig queueBufferConfig = new QueueBufferConfig();
            queueBufferConfig.setFlushOnShutdown(true);
            AmazonSQSBufferedAsyncClient client =  new AmazonSQSBufferedAsyncClient(
                    AmazonSQSAsyncClientBuilder.standard()
                            .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.SQS))
                            .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                            .build(), queueBufferConfig
            );
            client.createQueue(properties.getFxSend());
            return client;
        }

        @Primary
        @Bean(destroyMethod = "shutdown")
        public AmazonSNS amazonSNS(LocalStackContainer localStackContainer, AwsSnsTopicProperties snsTopicProperties,
                                   AmazonSQS amazonSQS, AwsSqsQueueProperties sqsQueueProperties) {
            AmazonSNS amazonSNS = AmazonSNSClientBuilder.standard()
                    .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.SNS))
                    .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                    .build();
            CreateTopicResult fxTradeEventTopic = amazonSNS.createTopic(snsTopicProperties.getFxTradeEvent());

            GetQueueUrlResult queueUrl = amazonSQS.getQueueUrl(sqsQueueProperties.getFxSend());
            SubscribeRequest subscribeRequest = new SubscribeRequest(fxTradeEventTopic.getTopicArn(), "sqs", queueUrl.getQueueUrl());
            subscribeRequest.addAttributesEntry("RawMessageDelivery", "true");
            amazonSNS.subscribe(subscribeRequest);
            return amazonSNS;
        }
    }
}
