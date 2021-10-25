package com.tyr.base.utils;


import com.tyr.base.bean.response.ResponseCode;
import com.tyr.base.bean.response.ResponseData;

public class ResponseUtil {

    public static <T> ResponseData<T> result(int code) {
        return result(ResponseCode.ordinalCode(code));
    }

    public static <T> ResponseData<T> result(T data, int code) {
        return result(data, ResponseCode.ordinalCode(code));
    }

    public static <T> ResponseData<T> result(ResponseCode code) {
        return new ResponseData<>(code);
    }

    public static <T> ResponseData<T> result(ResponseCode code, String msg) {
        return new ResponseData<>(null, code, msg);
    }

    public static <T> ResponseData<T> result(T data, ResponseCode code, String msg) {
        return new ResponseData<>(data, code, msg);
    }

    public static <T> ResponseData<T> result(T data, ResponseCode code) {
        return new ResponseData<>(data, code);
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<>(ResponseCode.SUCCESS);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(data, ResponseCode.SUCCESS);
    }

    public static <T> ResponseData<T> successWithMsg(String msg) {
        return new ResponseData<>(null, ResponseCode.SUCCESS, msg);
    }

    public static <T> ResponseData<T> failed() {
        return new ResponseData<>(ResponseCode.FAILED);
    }

    public static <T> ResponseData<T> hystrix() {
        return new ResponseData<>(ResponseCode.HYSTRIX_ERROR);
    }

    public static <T> ResponseData<T> timeout() {
        return new ResponseData<>(ResponseCode.SERVICE_TIMEOUT);
    }

    public static <T> ResponseData<T> shutdown() {
        return new ResponseData<>(ResponseCode.SERVICE_DOWN);
    }

    public static <T> ResponseData<T> failed(T data) {
        return new ResponseData<>(data, ResponseCode.FAILED);
    }

    public static <T> ResponseData<T> failed(String msg) {
        return new ResponseData<>(null, ResponseCode.FAILED, msg);
    }


    public static <T> ResponseData<T> unknow() {
        return new ResponseData<>(ResponseCode.UNKNOWN);
    }

    public static <T> ResponseData<T> unknow(T data) {
        return new ResponseData<>(data, ResponseCode.UNKNOWN);
    }
}
