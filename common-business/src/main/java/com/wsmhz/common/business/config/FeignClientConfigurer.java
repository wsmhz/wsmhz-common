package com.wsmhz.common.business.config;

import com.wsmhz.common.business.interceptor.FeignInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(name = "feign.RequestInterceptor")
public class FeignClientConfigurer {

    @Bean
    @ConditionalOnProperty(value = "wsmhz-micro-service-common.feign.proxy.enabled", havingValue = "true")
    public RequestInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }

    @Bean
    @ConditionalOnProperty(value = "wsmhz-micro-service-common.feign.debug", havingValue = "true")
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
