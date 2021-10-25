package com.tyr.business.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tyr.base.annotation.LoginUser;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.bean.user.SysUser;
import com.tyr.base.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Class : TestController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/12 15:33
 * @Version : 1.0
 */
@Slf4j
@Api("测试API接口")
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @HystrixCommand(fallbackMethod = "hystrixFallback")
    @ApiOperation(value = "测试产品接口方法")
    @GetMapping("/product/{id}")
    public ResponseData<String> getProduct(@ApiParam(value = "产品ID", required = true) @PathVariable String id, @ApiIgnore @LoginUser SysUser user, HttpServletRequest request) {
        return ResponseUtil.success("Get product id : " + id + " from " + request.getServerPort() + ", with uid is " + user.getId());
    }

    @HystrixCommand(fallbackMethod = "hystrixFallback")
    @ApiOperation(value = "测试订单接口方法")
    @GetMapping("/order/{id}")
    public ResponseData<String> getOrder(@ApiParam(value = "订单ID", required = true) @PathVariable String id, @ApiIgnore @LoginUser(isFull = true) SysUser user, HttpServletRequest request) {
        return ResponseUtil.success("Get order id : " + id + " from " + request.getServerPort() + ", with uid is " + user.getId());
    }

    /**
     * 如果直接访问服务测试接口将进入熔断机制，因为无法获取LoginUser的数据，异常为空指针
     *
     * @param id
     * @param user
     * @param request
     * @return
     */
    public ResponseData<String> hystrixFallback(String id, SysUser user, HttpServletRequest request) {
        try {
            log.error("Serial: {},User:{},ServerPort:{}", id, user != null ? user.toString() : null, request.getServerPort());
            return ResponseUtil.hystrix();
        } catch (Exception e) {
            throw new RuntimeException("hystrixFallback error : " + e.getMessage());
        }
    }
}
