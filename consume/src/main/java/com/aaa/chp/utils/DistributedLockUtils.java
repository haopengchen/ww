package com.aaa.chp.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;
import java.util.List;
import java.util.UUID;


/**
 * https://blog.csdn.net/xlgen157387/article/details/79036337
 * https://www.jianshu.com/p/0e4daecc8122  jmeter教程
 * redis 实现分布式锁的简单实现代码
 * Created by zts on 2020/3/18.
 */
@Component
public class DistributedLockUtils {

    private final Logger log = LoggerFactory.getLogger(DistributedLockUtils.class);

    private JedisPoolUtil jedisPoolUtil;

    private DistributedLockUtils(JedisPoolUtil jedisPoolUtil) {
        this.jedisPoolUtil = jedisPoolUtil;
    }

    @Autowired
    private Gson gson;

    /**
     * 基本思路：
     * （1）获取锁的时候，使用setnx加锁，并使用expire命令为锁添加一个超时时间，
     *         超过该时间则自动释放锁，锁的value值为一个随机生成的UUID，通过此在释放锁的时候进行判断。
     * （2）获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁。
     * （3）释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放。
     */

    /**
     * 加锁
     * @param lockName       锁的key key规则根据个人喜好自定
     * @param acquireTimeout 获取超时时间 ms
     * @param timeout        锁的超时时间 ms
     * @return 锁标识
     */
    public String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        Jedis conn = null;
        String retIdentifier = null;
        try {
            // 获取连接
            conn = jedisPoolUtil.getJedisInstance();


            // 锁名，即key值
            String lockKey = "lock:" + lockName;
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();

            // 超时时间，上锁后超过此时间则自动释放锁
            int lockExpire = (int) (timeout / 1000);
            log.info("超时时间：lockExpire="+lockExpire);
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            log.info("end="+end);
            long current=System.currentTimeMillis();
            while (current < end) {
                //conn.setnx(lockKey, identifier) : 当且仅当key不存在时，set一个key为val的字符串，返回1；若key存在，则什么都不做，返回0
                if (conn.setnx(lockKey, identifier) == 1) {
                    //为key设置一个超时时间，单位为second，超过这个时间锁会自动释放，避免死锁。
                    conn.expire(lockKey, lockExpire);
                    // 返回value值，用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }
                // conn.ttl(lockKey) ：以秒为单位返回 key 的剩余过期时间
                log.info("conn.ttl(lockKey)="+conn.ttl(lockKey));
                if (conn.ttl(lockKey) == -1) {
                    // 返回-1代表key没有设置超时时间，为key设置一个超时时间
                    conn.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.info("线程异常==线程中断状态已被设置");
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            log.info("redis加锁异常，异常信息={}",e);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retIdentifier;
    }

    /**
     * 释放锁
     * @param lockName   锁的key
     * @param identifier 释放锁的标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            conn = jedisPoolUtil.getJedisInstance();
            while (true) {
                // 监视lock，准备开始事务
                conn.watch(lockKey);
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                if (identifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    log.info("redis解锁成功，key={},释放锁的标识={}",lockKey,identifier);
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (JedisException e) {
            log.info("redis释放锁异常，异常信息={}",e);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retFlag;
    }


}
