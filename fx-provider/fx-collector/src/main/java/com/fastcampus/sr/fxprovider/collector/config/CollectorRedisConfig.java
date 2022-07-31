package com.fastcampus.sr.fxprovider.collector.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Profile("!local&&!test")
@Configuration
@EnableRedisRepositories
public class CollectorRedisConfig {
    private final RedisProperties redisProperties;

    public CollectorRedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        var clusterConfiguration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        return new LettuceConnectionFactory(clusterConfiguration);
    }
}
