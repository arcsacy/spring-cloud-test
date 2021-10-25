package com.tyr.base.bean.exception;

/**
 * @Class : ApiNotFoundException
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/9/11 12:03
 * @Version : 1.0
 */
public class ApiUnauthException extends RuntimeException {

    public ApiUnauthException() {
    }

    public ApiUnauthException(String message) {
        super(message);
    }

    public ApiUnauthException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiUnauthException(Throwable cause) {
        super(cause);
    }

    public ApiUnauthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
