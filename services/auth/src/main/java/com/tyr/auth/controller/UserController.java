package com.tyr.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Class : UserController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/11 17:19
 * @Version : 1.0
 */
@RestController
@EnableResourceServer
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.debug(principal.toString());
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

}
