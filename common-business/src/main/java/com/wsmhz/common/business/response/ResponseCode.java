package com.wsmhz.common.business.response;

/**
 * create by tangbj on 2018/3/26
 * 服务响应状态码
 */
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    ILLEGAL_ARGUMENT(-2,"ILLEGAL_ARGUMENT"),
    SESSION_INVALID(-10,"SESSION_INVALID"),
    NEED_LOGIN(-10,"NEED_LOGIN");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
