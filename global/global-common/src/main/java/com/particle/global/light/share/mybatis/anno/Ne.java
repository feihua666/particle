package com.particle.global.light.share.mybatis.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的关键字 不等于
 * 仅限调用IBaseService中的重载方法有效
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface Ne {
    /**
     * 字段名，java字段名，驼峰转下划线
     * @return
     */
    String value() default "";
}
