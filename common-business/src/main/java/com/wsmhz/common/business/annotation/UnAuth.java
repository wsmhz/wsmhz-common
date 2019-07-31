package com.wsmhz.common.business.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created By tangbj On 2019/7/30
 * Description: 不需要认证 作用在方法上
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface UnAuth {
}
