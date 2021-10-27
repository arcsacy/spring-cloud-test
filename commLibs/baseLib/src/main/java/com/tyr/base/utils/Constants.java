package com.tyr.base.utils;

/**
 * @Interface : Constants
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/11 17:06
 * @Version : 1.0
 */
public interface Constants {
    String PUBLIC_KEY = "pubkey.txt";

    String PRINCIPAL_KEY = "principal";

    String USER_ID_HEADER = "user_id";

    String ACCOUNT_HEADER = "username";

    String[] SECURITY_IGNORED = new String[]{"/auth/**","/**/config-info/**", "/**/actuator/**", "/**/api-docs", "/swagger-resources/**", "/error/**", "/images/**", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.xml", "/**/*.json"};

    //Service
    String BUSINESS_SERVICE = "business-service";

}
