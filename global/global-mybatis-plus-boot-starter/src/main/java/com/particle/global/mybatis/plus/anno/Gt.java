package com.particle.global.mybatis.plus.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的关键字
 * 仅限调用IBaseService中的重载方法有效
 * 大于
 * 举例说明：
 * 表示 create > createAtStart
 * 查询用户的创建时间大于2020-03-17 23:18:01
 * @Gt("create")
 * createAtStart
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface Gt {
    // 字段名，java字段名，驼峰转下划线
    String value();
    // 是否包括等于，如果包括就是大于等于
    boolean eq() default false;
}
