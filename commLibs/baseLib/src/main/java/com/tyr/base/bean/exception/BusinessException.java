package com.tyr.base.bean.exception;

/**
 * @Class : BusinessException
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/19 11:40
 * @Version : 1.0
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
