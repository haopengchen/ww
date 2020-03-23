package com.aaa.chp.common;

/**
 * Created by zts on 2020/3/23.
 */
public enum UnifyStrErrorCode {


    SUCCESS("200","查询成功"),
    ERROR("500","查询失败");

    private String code;
    private String msg;

    UnifyStrErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }




}
