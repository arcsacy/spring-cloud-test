package com.tyr.srv.controller;

import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Class : BaseConfigController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/26 17:39
 * @Version : 1.0
 */
@RequestMapping("/config-info")
public abstract class BaseConfigController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public ResponseData<String> port() {
        return ResponseUtil.success("SERVER PORT -> " + port);
    }

}
