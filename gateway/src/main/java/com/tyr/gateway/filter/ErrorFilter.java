package com.tyr.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Class : ErrorFilter
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/19 11:51
 * @Version : 1.0
 */
@Slf4j
@Component
public class ErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        log.debug("出现异常 {}", ctx.getThrowable().getCause().getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        try {
            ResponseData<Void> res = ResponseUtil.failed("请求网关路由失败，原因为：" + ctx.getThrowable().getCause() != null ?
                    ctx.getThrowable().getCause().getMessage() : ctx.getThrowable().getClass().getName());
            response.getWriter().write(JSON.toJSONString(res));
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.run();
    }

}
