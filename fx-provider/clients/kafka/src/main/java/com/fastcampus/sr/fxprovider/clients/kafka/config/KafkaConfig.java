package com.fastcampus.sr.fxprovider.clients.kafka.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    private final ProducerFactory producerFactory;
    private final KafkaProperties kafkaProperties;

    public KafkaConfig(ProducerFactory producerFactory, KafkaProperties kafkaProperties) {
        this.producerFactory = producerFactory;
        this.kafkaProperties = kafkaProperties;
    }
}
