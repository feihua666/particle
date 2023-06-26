package com.particle.global.light.share.mybatis.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的排序
 * 仅限调用IBaseService中的重载方法有效
 */
@Target({ FIELD,TYPE})
@Retention(RUNTIME)
@Documented
public @interface OrderBy {
    /**
     * 字段名，java字段名，驼峰转下划线,默认使用注解的字段名
     * @return
     */
    String value() default "";

    /**
     * 升序还是降序，默认升级
     * @return
     */
    boolean asc() default true;

    /**
     * 排序
     * @return
     */
    int order() default 0;

}
