package com.tyr.auth.controller;

import com.tyr.base.bean.exception.ApiUnauthException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Class : GlobalErrorController
 * @Description : 解决全局异常处理无法捕获filter异常情况
 * @Author : Tyrance
 * @Date : 2021/9/10 10:12
 * @Version : 1.0
 */
@Controller
public class GlobalErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void handleError(HttpServletRequest request) throws Throwable {
        int status = Integer.valueOf(request.getAttribute("javax.servlet.error.status_code").toString());
        if (status == 401)
            throw new ApiUnauthException("没有访问权限！");
        if (status == 404)
            throw new ApiUnauthException("API接口不存在！");
        if (request.getAttribute("javax.servlet.error.exception") != null)
            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
