package com.tyr.gateway.controller;

import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class : ConfigController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/26 17:45
 * @Version : 1.0
 */
@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public ResponseData<String> port() {
        return ResponseUtil.success("SERVER PORT -> " + port);
    }


}
