package com.tyr.auth.service;

import com.tyr.auth.bean.AuthUser;
import com.tyr.base.bean.response.ResponseCode;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.bean.user.SysUser;
import com.tyr.feign.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Class : AuthUserDetailService
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/13 11:55
 * @Version : 1.0
 */
@Component("securityUserDetailService")
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private IAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }

    public AuthUser findUserByUsername(String username) {
        ResponseData<SysUser> data = authService.findUserByUsername(username);
        if (data != null && data.getCode() == ResponseCode.SUCCESS.code())
            return new AuthUser(data.getData());
        return null;
    }
}
