package com.aaa.chp.common;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zts on 2020/3/23.
 */

public class Result<T> extends BaseDomain implements Serializable {
    private String code;
    private String msg;
    private T data;

    public Result() {
        this.code = UnifyStrErrorCode.SUCCESS.getCode();
        this.msg = UnifyStrErrorCode.SUCCESS.getMsg();
    }

    public Result(T data) {
        this.code = UnifyStrErrorCode.SUCCESS.getCode();
        this.msg = UnifyStrErrorCode.SUCCESS.getMsg();
        this.setData(data);
    }

    public Result(String code, String msg) {
        this.code = UnifyStrErrorCode.SUCCESS.getCode();
        this.msg = UnifyStrErrorCode.SUCCESS.getMsg();
        this.setCode(code);
        this.setMsg(msg);
    }

    public Result(String code, String msg, T data) {
        this.code = UnifyStrErrorCode.SUCCESS.getCode();
        this.msg = UnifyStrErrorCode.SUCCESS.getMsg();
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public Result<T> setError(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public Result<T> setError(UnifyStrErrorCode errorCode) {
        this.setCode(errorCode.getCode());
        this.setMsg(errorCode.getMsg());
        return this;
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.getCode(), UnifyStrErrorCode.SUCCESS.getCode());
    }

    public static Result instance() {
        return new Result();
    }

    public static <T> Result instance(T data) {
        return new Result(data);
    }

    public static <T> Result instance(String code, String msg) {
        return new Result(code, msg);
    }

    public static <T> Result instance(String code, String msg, T data) {
        return new Result(code, msg, data);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> toJsonMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", this.data);
        map.put("msg", this.msg);
        map.put("code", this.code);
        return map;
    }

}