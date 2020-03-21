package com.aaa.chp.dao;

import com.aaa.chp.entity.ConsumeWater;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by zts on 2020/3/17.
 */
@Component
public interface ConsumeMapper {

    //查询消费流水
    List<ConsumeWater> consumeListByUserId(String userId);

    //统计当天的消费总金额
    Double countMoneyByDay(@Param("userId") String userId,@Param("startTime") String startTime,@Param("endTime") String endTime);


}
