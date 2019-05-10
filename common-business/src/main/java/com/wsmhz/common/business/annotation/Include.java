package com.wsmhz.common.business.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Include {
    /**
     * @return 包含的路径
     */
    String[] value();
}
