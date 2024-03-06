package com.lin.lojbackendcommon.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author L
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonClientConfig {

    private int database;

    private String host;

    private int port;

    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        final String redisAddress = "redis://" + host + ":" + port;
        config.useSingleServer()
                .setDatabase(database)
                .setAddress(redisAddress)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
