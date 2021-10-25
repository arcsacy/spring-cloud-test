package com.tyr.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyr.base.bean.client.OauthClientDetails;

/**
 * @Class : OauthClientDetailService
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 13:57
 * @Version : 1.0
 */
public interface OauthClientDetailService extends IService<OauthClientDetails> {

    boolean updateClientSecret(String clientId, String secret);

    boolean updateClientDetails(OauthClientDetails details);
}
