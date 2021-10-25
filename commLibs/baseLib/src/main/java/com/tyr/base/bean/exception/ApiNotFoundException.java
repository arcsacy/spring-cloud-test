package com.tyr.base.bean.exception;

/**
 * @Class : ApiNotFoundException
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/9/11 12:03
 * @Version : 1.0
 */
public class ApiNotFoundException extends RuntimeException {

    public ApiNotFoundException() {
    }

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }

    public ApiNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
