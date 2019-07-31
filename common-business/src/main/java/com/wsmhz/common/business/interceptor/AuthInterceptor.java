package com.wsmhz.common.business.interceptor;

import com.wsmhz.common.business.annotation.UnAuth;
import com.wsmhz.common.business.response.ResponseCode;
import com.wsmhz.common.business.response.ServerResponse;
import com.wsmhz.common.business.utils.JsonUtil;
import com.wsmhz.common.business.utils.OAuthUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By TangBiJing On 2019/5/15
 * Description:
 */
@Slf4j
@Component
@ConditionalOnBean(OAuthUtil.class)
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private OAuthUtil oAuthUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            val unAuth = ((HandlerMethod) handler).getMethodAnnotation(UnAuth.class);
            if (unAuth != null || oAuthUtil.checkToken(request)) {
                return true;
            }
            log.info("未登录拦截路径为：{}", request.getRequestURI());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JsonUtil.objToString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请先登录")));
            return false;
        }
        return true;
    }
}
