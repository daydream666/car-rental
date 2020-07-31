package com.qf;

import com.qf.entity.Car;
import com.qf.mapper.CarMapper;
import com.qf.service.BackCarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@SpringBootTest
class SpringbootCsdApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedis(){
        // 0、创建池子的配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);// 最大闲置个数
        poolConfig.setMinIdle(10);// 最小闲置个数
        poolConfig.setMaxTotal(50);// 最大连接数

        // 1、创建一个redis的连接池
        JedisPool pool = new JedisPool(poolConfig, "192.168.29.128", 6379);

        // 2、从池子中获取redis的连接资源
        Jedis jedis = pool.getResource();

        // 3、操作数据库
        jedis.set("age", "18");
        System.out.println(jedis.get("age"));

        // 4、关闭资源
        jedis.close();
        pool.close();

    }

    @Autowired
    private CarMapper carMapper;
    @Test
    public void testselectAll(){
        List<Car> cars = carMapper.selectAll(0);
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
