package com.wsmhz.common.business.exception;

import lombok.Getter;

/**
 * create by tangbj on 2018/5/19
 */
public class BussinessException extends RuntimeException{

    @Getter
    private Integer errorCode = -100;

    private BussinessException() {
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
