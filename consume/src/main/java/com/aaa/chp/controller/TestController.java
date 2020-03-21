package com.aaa.chp.controller;

import com.aaa.chp.common.CommonDTO;
import com.aaa.chp.service.ConsumeService;
import com.aaa.chp.utils.DistributedLockUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zts on 2020/3/20.
 */
@Component
@RestController
public class TestController {

    @Autowired
    private ConsumeService consumeService;

    @Autowired
    private DistributedLockUtils redisUtils;


    /**
     * 测试Redis分布式锁的例子和公用返回对象的例子
     * @return
     */
    @RequestMapping("/testCommon")
    public CommonDTO testRetRedisThread(){


        return  new CommonDTO<>("0", "查询成功", consumeService.findListById("7042"));
    }


    /**
     * 测试Redis分布式锁的例子
     * @return
     */
    @RequestMapping("/testRedisThread")
    public String testRedisThread(){

        //redis加锁
        String lockName = "resource";
        String indentifier = redisUtils.lockWithTimeout(lockName,5000,1000);
        if(!StringUtils.isBlank(indentifier)){

            System.out.println("======锁住了=====");
            /**
             * 业务处理
             */
            redisUtils.releaseLock(lockName,indentifier);//释放锁
        }

        return "success!";
    }








}
