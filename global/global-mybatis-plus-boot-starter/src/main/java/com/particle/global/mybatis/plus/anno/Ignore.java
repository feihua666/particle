package com.particle.global.mybatis.plus.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 如果有该注解，忽略自定义处理
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface Ignore {
}
