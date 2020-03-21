package com.aaa.chp.service;

import com.aaa.chp.entity.ConsumeWater;
import java.util.List;

/**
 * Created by zts on 2020/3/17.
 */

public interface ConsumeService {

    List<ConsumeWater> findListById(String userId);

    Double countMoneyByDay(String userId, String startTime,String endTime);

}
