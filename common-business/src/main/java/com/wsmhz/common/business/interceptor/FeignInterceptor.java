package com.wsmhz.common.business.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FeignInterceptor implements RequestInterceptor {

    @Value("${wsmhz-micro-service.feign.proxy.header:Cookie,Authorization}")
    private String[] proxyHeader;

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes)
                .ifPresent(attr -> Arrays
                        .stream(proxyHeader)
                        .forEach(name -> Optional.ofNullable(attr.getRequest().getHeader(name))
                                .ifPresent(value -> template.header(name, value))));
    }
}
