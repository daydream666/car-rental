package com.qf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Autowired
    private JedisPoolConfig poolConfig;

    //创建池子的配置对象
    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);// 最大闲置个数
        poolConfig.setMinIdle(10);// 最小闲置个数
        poolConfig.setMaxTotal(50);// 最大连接数
        return poolConfig;
    }

    //创建一个redis的连接池
    @Bean
    public JedisPool getJedisPool(){
        return new JedisPool(poolConfig, "192.168.29.128", 6379);
    }

}
