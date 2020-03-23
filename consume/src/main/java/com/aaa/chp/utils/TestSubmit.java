package com.aaa.chp.utils;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by zts on 2020/3/18.
 */
public class TestSubmit {

    public static void main(String[] args) {

        HttpServletRequest request = null;

        String ip = CacheUtils.getIpAddr(request);
//        log.info("--请求太频繁--ip={}",ip);
        if (!"".equals(ip)) {
            StringBuffer stringBuffer = new StringBuffer(ip);
            StringBuffer condition = stringBuffer.append("用户id");
            boolean isExist = CacheUtils.custSecondRequestTooSoon(condition.toString());
            if (isExist) {
//                log.info("--重复频繁请求-");
            }else{
//                log.info("-------业务处理----");
            }
        }


    }

}
