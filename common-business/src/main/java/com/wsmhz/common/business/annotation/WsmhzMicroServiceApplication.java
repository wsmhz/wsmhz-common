package com.wsmhz.common.business.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.lang.annotation.*;

/**
 * Created By TangBiJing On 2019/4/30
 * Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@EnableAsync
@MapperScan("com.wsmhz.**.mapper")
@ComponentScan("com.wsmhz")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableTransactionManagement
public @interface WsmhzMicroServiceApplication {
}
