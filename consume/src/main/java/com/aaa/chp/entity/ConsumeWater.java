package com.aaa.chp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Created by zts on 2020/3/17.
 */
@Data
@Component
public class ConsumeWater {

    private int id;
    private int userid;
    private Date createDate;
    private double money;
    private String explain;

}
