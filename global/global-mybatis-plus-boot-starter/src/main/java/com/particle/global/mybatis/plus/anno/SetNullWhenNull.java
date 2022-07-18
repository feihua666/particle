package com.particle.global.mybatis.plus.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个更新字段的值，如果该字段为null则更新为null
 * 仅限调用IBaseService中的重载方法有效
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface SetNullWhenNull {
}
