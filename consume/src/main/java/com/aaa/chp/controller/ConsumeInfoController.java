package com.aaa.chp.controller;

import com.aaa.chp.common.CommonDTO;
import com.aaa.chp.entity.ConsumeWater;
import com.aaa.chp.service.ConsumeService;
import com.aaa.chp.utils.DistributedLockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Created by zts on 2020/3/17.
 */
@Component
@RestController
public class ConsumeInfoController {

    @Autowired
    private ConsumeService consumeService;

    @Autowired
    private DistributedLockUtils redisUtils;

    @RequestMapping("/list")
    public CommonDTO test(){
        List<ConsumeWater> vo = consumeService.findListById("7042");
        return new CommonDTO<>("0", "查询成功",  vo);
    }

    @RequestMapping("/countMoneyByDay")
    public Double countMoney(){
        return consumeService.countMoneyByDay("7042","","");
    }





}
