package com.wsmhz.common.business.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created By TangBiJing On 2019/4/2
 * Description: 一些业务上的工具方法
 */
public class WebUtil {

    /**
     * @return 获得当前请求
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 获取客户端IP
     */
    public static String getClientIP() {
        HttpServletRequest req = getRequest();
        String ip = Optional
                .ofNullable(req.getHeader("X-Real-IP"))
                .orElse(req.getHeader("X-Forwarded-For"));
        return Optional
                .ofNullable(ip)
                .orElse(req.getRemoteAddr()).split(",")[0];
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

}
