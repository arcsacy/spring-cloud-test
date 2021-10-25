package com.tyr.business.controller;

import com.alibaba.fastjson.JSON;
import com.tyr.base.bean.client.OauthClientDetails;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.bean.user.SysUser;
import com.tyr.base.utils.ResponseUtil;
import com.tyr.business.bean.ClientDetails;
import com.tyr.business.bean.GrantedAuthority;
import com.tyr.business.service.OauthClientDetailService;
import com.tyr.business.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Class : UserController
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 14:07
 * @Version : 1.0
 */
@Api("Oauth2认证API接口")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final SysUserService userService;
    private final OauthClientDetailService clientDetailService;

    @Autowired
    public AuthController(SysUserService userService, OauthClientDetailService clientDetailService) {
        this.userService = userService;
        this.clientDetailService = clientDetailService;
    }

    @GetMapping("/findUserByUsername/{username}")
    @ApiOperation(value = "SECURITY 根据用户名查询用户信息")
    public ResponseData<SysUser> findUserByUsername(@ApiParam(value = "用户名", required = true) @PathVariable("username") String username) {
        SysUser user = userService.findByUsername(username);
        return user != null ? ResponseUtil.success(user) : ResponseUtil.failed();
    }

    @GetMapping("/findClientDetailsById/{clientId}")
    @ApiOperation(value = "OAUTH2 根据客户端ID查询客户端信息")
    public ResponseData<OauthClientDetails> findClientDetailsById(@ApiParam(value = "客户端ID", required = true) @PathVariable("clientId") String clientId) {
        OauthClientDetails details = clientDetailService.getById(clientId);
        if (details != null) {
            return ResponseUtil.success(details);
        }
        return ResponseUtil.failed();
    }

    @GetMapping("/findAllClientDetails")
    @ApiOperation(value = "OAUTH2 查询所有客户端信息")
    public ResponseData<List<OauthClientDetails>> findAllClientDetails() {
        List<OauthClientDetails> list = clientDetailService.list();
        if (list != null && !list.isEmpty()) {
            return ResponseUtil.success(list);
        }
        return ResponseUtil.failed();
    }

    @PostMapping("/addClientDetails")
    @ApiOperation(value = "OAUTH2 新增客户端信息")
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> addClientDetails(@ApiParam(value = "客户端信息(JSON数据)", required = true) @RequestParam("data") String data) {
        ClientDetails details = JSON.parseObject(data, ClientDetails.class);
        OauthClientDetails authDetails = toOauthClientDetails(details);
        return details != null && StringUtils.hasText(details.getClientId()) && clientDetailService.save(authDetails) ? ResponseUtil.success() : ResponseUtil.failed();
    }

    @PutMapping("/updateClientDetails")
    @ApiOperation(value = "OAUTH2 更新客户端信息")
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> updateClientDetails(@ApiParam(value = "客户端信息(JSON数据)", required = true) @RequestParam("data") String data) {
        ClientDetails details = JSON.parseObject(data, ClientDetails.class);
        OauthClientDetails authDetails = toOauthClientDetails(details);
        return authDetails != null && StringUtils.hasText(authDetails.getClientId()) && clientDetailService.updateClientDetails(authDetails) ? ResponseUtil.success() : ResponseUtil.failed();
    }

    private OauthClientDetails toOauthClientDetails(ClientDetails details) {
        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : details.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }

        return OauthClientDetails.builder()
                .clientId(details.getClientId())
                .clientSecret(details.getClientSecret())
                .accessTokenValidity(details.getAccessTokenValiditySeconds())
                .refreshTokenValidity(details.getRefreshTokenValiditySeconds())
                .scope(details.getScope().isEmpty() ? "" : (details.getScope().stream().collect(Collectors.joining(","))))
                .authorities(authorities.isEmpty() ? "" : (authorities.stream().collect(Collectors.joining(","))))
                .authorizedGrantTypes(details.getAuthorizedGrantTypes().isEmpty() ? "" : (details.getAuthorizedGrantTypes().stream().collect(Collectors.joining(","))))
                .additionalInformation(JSON.toJSONString(details.getAdditionalInformation()))
                .autoapprove("read")
                .resourceIds(details.getResourceIds().isEmpty() ? "" : (details.getResourceIds().stream().collect(Collectors.joining(","))))
                .webServerRedirectUri(details.getRegisteredRedirectUri().isEmpty() ? "" : (details.getRegisteredRedirectUri().stream().collect(Collectors.joining(",")))).build();
    }

    @PutMapping("/updateClientSecret/clientId/{clientId}/secret/{secret}")
    @ApiOperation(value = "OAUTH2 更新客户端登录密码")
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> updateClientSecret(@ApiParam(value = "客户端ID", required = true) @PathVariable("clientId") String clientId, @ApiParam(value = "客户端密码", required = true) @PathVariable("secret") String secret) {
        return clientDetailService.updateClientSecret(clientId, secret) ? ResponseUtil.success() : ResponseUtil.failed();
    }

    @DeleteMapping("/deleteClientDetails/{clientId}")
    @ApiOperation(value = "OAUTH2 删除客户端信息")
    @Transactional(rollbackFor = Exception.class)
    ResponseData<Void> deleteClientDetails(@ApiParam(value = "客户端ID", required = true) @PathVariable("clientId") String clientId) {
        return clientDetailService.removeById(clientId) ? ResponseUtil.success() : ResponseUtil.failed();
    }
}
