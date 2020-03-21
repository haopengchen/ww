package com.aaa.chp.redisThread;

/**
 * @author By chp
 * @Date 2020/3/19 0019 10:33
 */
public class ThreadTest extends Thread{

    private RedisConfig service;

    public ThreadTest(RedisConfig service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }

    public static void main(String[] args) {

        RedisConfig service = new RedisConfig();

        for (int i = 0; i < 50; i++) {
            ThreadTest threadA = new ThreadTest(service);
            threadA.start();
        }

    }


}
