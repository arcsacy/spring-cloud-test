package com.tyr.srv.resolvers;


import com.tyr.base.annotation.LoginUser;
import com.tyr.base.bean.user.SysUser;
import com.tyr.base.utils.Constants;
import com.tyr.feign.IAuthService;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Class : LoginUserArgumentResolver
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/15 18:59
 * @Version : 1.0
 */
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private IAuthService authService;

    public LoginUserArgumentResolver(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class) && parameter.getParameterType().equals(SysUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LoginUser annotation = parameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = annotation.isFull();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String userId = request.getHeader(Constants.USER_ID_HEADER);
        String account = request.getHeader(Constants.ACCOUNT_HEADER);
        if (!StringUtils.hasText(userId) || !StringUtils.hasText(account))
            return null;
        SysUser user;
        if (!isFull) {
            user = new SysUser();
            user.setId(Long.parseLong(userId));
            user.setUsername(account);
        } else {
            user = authService.findUserByUsername(account).getData();
        }
        return user;
    }
}
