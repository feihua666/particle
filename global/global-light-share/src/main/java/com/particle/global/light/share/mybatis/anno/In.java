package com.particle.global.light.share.mybatis.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的关键字 类型必须为集合
 * 仅限调用IBaseService中的重载方法有效
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface In {
}
