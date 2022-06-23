package com.particle.global.catchlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 标记该注解的 spring 组件 可以被 catch 并打印日志
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 18:48
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CatchAndLog {
}
