package com.aaa.chp.service.serviceImpl;

import com.aaa.chp.dao.ConsumeMapper;
import com.aaa.chp.entity.ConsumeWater;
import com.aaa.chp.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Created by zts on 2020/3/17.
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {

    @Autowired
    ConsumeMapper consumeMapper;

    //根据userId查询消费流水
    @Override
    public List<ConsumeWater> findListById(String userId) {
        List<ConsumeWater> ret = consumeMapper.consumeListByUserId("7042");
        return ret;
    }

    //统计某个人当天的消费总金额
    @Override
    public Double countMoneyByDay(String userId, String startTime, String endTime) {

        Double countMoney = consumeMapper.countMoneyByDay("7042","2020-03-17 00:00:00","2020-03-17 23:59:59");

        return countMoney;
    }


}
