package com.example.shiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * JedisPool 配置
 * @className: JedisPoolConfiguration
 * @description
 * @author: luffy
 * @date: 2020/3/31 15:46
 * @version:V1.0
 */
@Configuration
public class JedisPoolConfiguration {

    @Value("${spring.redis.host}")
    private String IP;

    @Value("${spring.redis.port}")
    private int PORT;

    @Value("${spring.redis.timeout}")
    private int TIMEOUT;

    @Value("${spring.redis.password}")
    private String PASSWORD;


    @Bean
    public JedisPool instance(){
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config,IP, PORT,TIMEOUT,PASSWORD);
        return jedisPool;
    }
}
