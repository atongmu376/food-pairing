package com.phj.common.config.annotation;

import java.lang.annotation.*;

/**
 * @author 17583
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonObject {
    /**
     * 是否必须出现的参数
     */
    boolean required() default true;



    /**
     * 解析时用到的JSON的key
     */
    String value() default "";

}
