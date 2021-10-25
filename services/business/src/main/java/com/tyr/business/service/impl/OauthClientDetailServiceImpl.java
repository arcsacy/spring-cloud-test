package com.tyr.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyr.business.mapper.OauthClientDetailMapper;
import com.tyr.business.service.OauthClientDetailService;
import com.tyr.base.bean.client.OauthClientDetails;
import org.springframework.stereotype.Service;

/**
 * @Class : OauthClientDetailServiceImpl
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 13:58
 * @Version : 1.0
 */
@Service
public class OauthClientDetailServiceImpl extends ServiceImpl<OauthClientDetailMapper, OauthClientDetails>
        implements OauthClientDetailService {
    @Override
    public boolean updateClientSecret(String clientId, String secret) {
        return getBaseMapper().update(null, new LambdaUpdateWrapper<OauthClientDetails>()
                .set(OauthClientDetails::getClientSecret, secret).eq(OauthClientDetails::getClientId, clientId)) > 0;
    }

    @Override
    public boolean updateClientDetails(OauthClientDetails details) {
        return getBaseMapper().update(null, new LambdaUpdateWrapper<OauthClientDetails>()
                .set(OauthClientDetails::getResourceIds, details.getResourceIds())
                .set(OauthClientDetails::getAuthorizedGrantTypes, details.getAuthorizedGrantTypes())
                .set(OauthClientDetails::getWebServerRedirectUri, details.getWebServerRedirectUri())
                .set(OauthClientDetails::getAuthorities, details.getAuthorities())
                .set(OauthClientDetails::getAccessTokenValidity, details.getAccessTokenValidity())
                .set(OauthClientDetails::getRefreshTokenValidity, details.getRefreshTokenValidity())
                .set(OauthClientDetails::getAdditionalInformation, details.getAdditionalInformation())
                .set(OauthClientDetails::getAutoapprove, details.getAutoapprove())
                .set(OauthClientDetails::getScope, details.getScope()).eq(OauthClientDetails::getClientId, details.getClientId())) > 0;
    }
}
