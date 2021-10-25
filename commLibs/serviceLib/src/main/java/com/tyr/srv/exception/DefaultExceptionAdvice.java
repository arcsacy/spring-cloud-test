package com.tyr.srv.exception;

import com.tyr.base.bean.exception.ApiNotFoundException;
import com.tyr.base.bean.exception.ApiUnauthException;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * @Class : DefaultExceptionAdvice
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/19 13:49
 * @Version : 1.0
 */
@Slf4j
@ResponseBody
public class DefaultExceptionAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ApiUnauthException.class})
    public ResponseData<Void> handleUnAuthException(ApiUnauthException e) {
        return defHandler("请求异常", e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ApiNotFoundException.class})
    public ResponseData<Void> handleNotFoundException(ApiNotFoundException e) {
        return defHandler("请求异常", e);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseData<Void> handleSQLException(SQLException e) {
        return defHandler("数据执行异常，原因为：", e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BusinessException.class,})
    public ResponseData<Void> handleBusinessException(Exception e) {
        return defHandler("业务执行异常，原因为：", e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseData<Void> handleException(Exception e) {
        return defHandler("未知异常，原因为：", e);
    }

    private ResponseData<Void> defHandler(String msg, Exception e) {
        return ResponseUtil.failed(msg + (e != null ? (e.getMessage()) : ""));
    }


}
