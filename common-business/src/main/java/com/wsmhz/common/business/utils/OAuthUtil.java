package com.wsmhz.common.business.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By TangBiJing On 2019/5/15
 * Description:
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "wsmhz.security.oauth2", name = "tokenStore", havingValue = "redis")
public class OAuthUtil {

    private static final String ACCESS = "access:";

    @Autowired
    private RedisUtil redisUtil;

    public boolean checkToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (authorization == null || ! authorization.contains("bearer")) {
            return false;
        }
        String token = authorization.substring("Bearer".length() + 1);
        return checkToken(token);
    }

    public boolean checkToken(String accessToken){
        return redisUtil.has(ACCESS + accessToken);
    }
}
