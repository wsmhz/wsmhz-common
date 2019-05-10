package com.wsmhz.common.business.exception;

import lombok.Getter;

public class ForbiddenException extends RuntimeException {
    @Getter
    private Integer errCode = 403;

    private ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Integer errCode) {
        super(message);
        this.errCode = errCode;
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message, Integer errCode, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
