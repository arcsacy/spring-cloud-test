package com.tyr.auth.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.tyr.base.bean.client.OauthClientDetails;
import com.tyr.base.bean.response.ResponseCode;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.feign.IAuthService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Class : AuthClientDetailService
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 14:03
 * @Version : 1.0
 */
public class AuthClientDetailService implements ClientDetailsService, ClientRegistrationService {

    private static final Log logger = LogFactory.getLog(AuthClientDetailService.class);

    private IAuthService authService;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthClientDetailService(IAuthService authService, BCryptPasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ResponseData<OauthClientDetails> res = authService.findClientDetailsById(clientId);
        if (res != null && ResponseCode.SUCCESS.code() == res.getCode())
            return toClientDetails(res.getData());
        return null;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        authService.addClientDetails(JSON.toJSONString(clientDetails));
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        authService.updateClientDetails(JSON.toJSONString(clientDetails));
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        authService.updateClientSecret(clientId, passwordEncoder.encode(secret));
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        authService.deleteClientDetails(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        ResponseData<List<OauthClientDetails>> res = authService.findAllClientDetails();
        if (res != null && ResponseCode.SUCCESS.code() == res.getCode()) {
            List<ClientDetails> details = new ArrayList<>();
            for (OauthClientDetails authDetail : res.getData()) {
                details.add(toClientDetails(authDetail));
            }
            return details;
        }
        return null;
    }

    private ClientDetails toClientDetails(OauthClientDetails authDetails) {
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(authDetails.getClientId());
        details.setClientSecret(authDetails.getClientSecret());
        details.setAccessTokenValiditySeconds(authDetails.getAccessTokenValidity());
        details.setRefreshTokenValiditySeconds(authDetails.getRefreshTokenValidity());
        if (StringUtils.hasText(authDetails.getScope()))
            details.setScope(Arrays.asList(authDetails.getScope().split(",")));
        if (StringUtils.hasText(authDetails.getAdditionalInformation()))
            details.setAdditionalInformation(JSON.parseObject(authDetails.getAdditionalInformation(), Map.class));
        if (StringUtils.hasText(authDetails.getAuthorizedGrantTypes()))
            details.setAuthorizedGrantTypes(Arrays.asList(authDetails.getAuthorizedGrantTypes().split(",")));
        if(StringUtils.hasText(authDetails.getResourceIds()))
            details.setResourceIds(Arrays.asList(authDetails.getResourceIds().split(",")));
        if(StringUtils.hasText(authDetails.getWebServerRedirectUri()))
            details.setRegisteredRedirectUri(Sets.newHashSet(authDetails.getWebServerRedirectUri().split(",")));
        if (StringUtils.hasText(authDetails.getAuthorities())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String auth : authDetails.getAuthorities().split(",")) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
            details.setAuthorities(authorities);
        }
        return details;
    }
}
