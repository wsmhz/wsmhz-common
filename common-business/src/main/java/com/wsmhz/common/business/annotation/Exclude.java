package com.wsmhz.common.business.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Exclude {
    /**
     * @return 排除的路径
     */
    String[] value();
}
