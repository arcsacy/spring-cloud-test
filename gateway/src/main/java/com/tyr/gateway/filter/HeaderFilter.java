package com.tyr.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tyr.base.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.Aes128;
import sun.security.krb5.internal.crypto.dk.AesDkCrypto;

import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;

/**
 * @Class : HeaderFilter
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/15 15:29
 * @Version : 1.0
 */
@Slf4j
@Component
public class HeaderFilter extends ZuulFilter {

    private static final String USER_KEY = "userId";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Authentication instance = SecurityContextHolder.getContext().getAuthentication();
        if (!(instance instanceof AnonymousAuthenticationToken)) {
            OAuth2Authentication authorization = (OAuth2Authentication) instance;
            if (authorization != null && authorization.getUserAuthentication() != null) {
                Map<String, Object> details = (Map<String, Object>) authorization.getUserAuthentication().getDetails();
                if (details != null && !details.isEmpty() && details.containsKey(Constants.PRINCIPAL_KEY)) {
                    Map<String, Object> authMap = (Map<String, Object>) details.get(Constants.PRINCIPAL_KEY);
                    if (authMap != null && !authMap.isEmpty()) {
                        if (authMap.containsKey(USER_KEY)) {
                            RequestContext context = RequestContext.getCurrentContext();
                            context.addZuulRequestHeader(Constants.USER_ID_HEADER, authMap.get(USER_KEY).toString());
                            if (authMap.containsKey(Constants.ACCOUNT_HEADER))
                                context.addZuulRequestHeader(Constants.ACCOUNT_HEADER, authMap.get(Constants.ACCOUNT_HEADER).toString());
                        }
                    }
                }
                log.debug(details.get(Constants.PRINCIPAL_KEY).toString());
            }
        }
        return null;
    }

}
