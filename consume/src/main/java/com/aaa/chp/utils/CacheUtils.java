package com.aaa.chp.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by zts on 2020/3/18.
 */

@Component
public class CacheUtils {


    //得到请求方法的ip
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 客户请求缓存<br>
     * 同一客户5秒内只允许请求一次
     * 过期时间5秒<br>
     */
    private static Cache<String, Integer> CustSecondRequestCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS)	//当缓存项在指定的时间段内没有被读或写就会被回收
            .maximumSize(1000)		// 设置缓存个数
            .build();

    /**
     * 请求太快
     * 5000ms内不允许请求2次相同的请求
     *
     * @param req
     */
    public static boolean custSecondRequestTooSoon(String req) {
        Integer v = CustSecondRequestCache.getIfPresent(req);
        if(v  != null) {
            //5000ms内有该请求
            return true;
        }
        //缓存该请求
        CustSecondRequestCache.put(req, 0);
        return false;
    }



}
