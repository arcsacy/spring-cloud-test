package com.tyr.business.controller;

import com.tyr.srv.controller.BaseConfigController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class : ConfigController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/26 17:46
 * @Version : 1.0
 */
@RefreshScope
@RestController
public class ConfigController extends BaseConfigController {
}
