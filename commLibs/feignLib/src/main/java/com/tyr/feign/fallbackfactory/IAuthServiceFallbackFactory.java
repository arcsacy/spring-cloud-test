package com.tyr.feign.fallbackfactory;

import com.tyr.base.bean.client.OauthClientDetails;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.bean.user.SysUser;
import com.tyr.base.utils.ResponseUtil;
import com.tyr.feign.IAuthService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Class : IAuthServiceFallbackFactory
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 12:36
 * @Version : 1.0
 */
@Component
public class IAuthServiceFallbackFactory implements FallbackFactory<IAuthService> {
    @Override
    public IAuthService create(Throwable cause) {
        return new IAuthService() {

            @Override
            public ResponseData<SysUser> findUserByUsername(String username) {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<OauthClientDetails> findClientDetailsById(String clientId) {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<List<OauthClientDetails>> findAllClientDetails() {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<Void> addClientDetails(String data) {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<Void> updateClientDetails(String data) {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<Void> updateClientSecret(String clientId, String secret) {
                return ResponseUtil.hystrix();
            }

            @Override
            public ResponseData<Void> deleteClientDetails(String clientId) {
                return ResponseUtil.hystrix();
            }
        };
    }
}
