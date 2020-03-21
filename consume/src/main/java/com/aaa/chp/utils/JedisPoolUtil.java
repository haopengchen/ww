package com.aaa.chp.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisPoolUtil {

    private JedisPool jedisPool;

    private JedisPoolUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 从连接池中获取一个 Jedis 实例（连接）
     *
     * @return Jedis 实例
     */
    public Jedis getJedisInstance() {
        return jedisPool.getResource();
    }

    /**
     * 将Jedis对象（连接）归还连接池
     *
     * @param jedis 连接对象
     */
    public void release(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
