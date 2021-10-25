package com.tyr.base.bean.response;

import java.io.Serializable;

/**
 * 错误码
 */
public enum ResponseCode implements Serializable {

    /**
     * 未知异常
     */
    UNKNOWN(-1, "未知异常，请联系管理员"),

    /**
     * 异常
     */
    FAILED(0, "操作执行失败"),

    /**
     * 成功
     */
    SUCCESS(1, "操作执行成功"),

    /**
     * 服务熔断提示
     */
    HYSTRIX_ERROR(2,"非常抱歉，服务出现异常，无法执行当前操作"),

    SERVICE_TIMEOUT(3,"服务器繁忙，请稍后重试"),

    SERVICE_DOWN(4,"服务器已关闭，请联系管理员处理"),

    ;


    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }


    public String msg() {
        return msg;
    }

    public static ResponseCode ordinalCode(int t) {
        for (ResponseCode code : ResponseCode.values()) {
            if (code.code == t)
                return code;
        }
        return UNKNOWN;
    }
}
