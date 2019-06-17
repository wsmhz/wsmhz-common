package com.wsmhz.common.business.interceptor;

import com.wsmhz.common.business.annotation.RequestLimit;
import com.wsmhz.common.business.response.ServerResponse;
import com.wsmhz.common.business.utils.WebUtil;
import com.wsmhz.common.business.utils.JsonUtil;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 请求限制，防止频繁恶意刷接口
 */
@Configuration
@ConditionalOnProperty(name = "wsmhz-micro-service-common.http.limit.enabled", havingValue = "true")
public class LimitInterceptor extends HandlerInterceptorAdapter {
    private static final String MARK = "LIMIT";
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            val limit = ((HandlerMethod) handler).getMethodAnnotation(RequestLimit.class);
            if (limit == null || !limit.enable()) {
                return true;
            }
            String authorization = request.getHeader("Authorization");
            String key = "LimitInterceptor:" + request.getRequestURI();
            if (authorization != null && authorization.contains("bearer")) {
                String token = authorization.substring("Bearer".length() + 1);
                key = key + ":" + token;
            } else {
                key = key + ":" + WebUtil.getClientIP();
            }
            String value = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isNotBlank(value)) {
                response.getOutputStream().write(JsonUtil.objToString(ServerResponse.createBySuccessMessage("请勿重复提交请求!")).getBytes(StandardCharsets.UTF_8));
                return false;
            }
            redisTemplate.opsForValue().set(key, MARK, limit.value(), TimeUnit.SECONDS);
            return true;
        }
        return true;
    }
}
