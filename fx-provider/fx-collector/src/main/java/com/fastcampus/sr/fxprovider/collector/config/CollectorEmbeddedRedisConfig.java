package com.fastcampus.sr.fxprovider.collector.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.embedded.Redis;
import redis.embedded.cluster.RedisCluster;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Profile("local|test")
@Configuration
public class CollectorEmbeddedRedisConfig {
    private final RedisProperties redisProperties;

    public CollectorEmbeddedRedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    private Redis redisServer;

    @PreDestroy
    public void destroy(){
        log.debug("RedisServer destroy.");
        Optional.ofNullable(redisServer).ifPresent(Redis::stop);
    }

    @PostConstruct
    public void start() {
        try {
            if(Objects.nonNull(redisServer) && redisServer.isActive()){
                return;
            }

            redisServer = new RedisCluster.Builder()
                    .serverPorts(Arrays.asList(redisProperties.getPort(), 6380, 6381))
                    .build();

            redisServer.start();
        } catch (Exception e) {
            log.error("Redis start error.", e);
        }
        log.debug("RedisServer start");
    }

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    public RedisConnectionFactory redisConnectionFactory() {
        var clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.clusterNode(redisProperties.getHost(), redisProperties.getPort());
        return new LettuceConnectionFactory(clusterConfiguration);
    }
}
