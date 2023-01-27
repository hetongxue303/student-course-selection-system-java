package com.hetongxue.aop.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 *
 * @author 何同学
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 操作
     */
    String operate() default "";

    /**
     * 详情
     */
    String detail() default "";

}