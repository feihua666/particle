package com.particle.global.light.share.mybatis.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的关键字，适用于boolean类型字段
 * 仅限调用IBaseService中的重载方法有效
 * nulls empty nullsOrEmpty 只能 有一个为true，否则按顺序生效
 * @author yangwei
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface QueryNull {
	/**
	 * 目标字段名，java字段名，驼峰转下划线
 	 * @return
	 */
	String value();
	/**
	 * 默认查询null，定义为 nulls 是因为 null为关键字定义不了
	 * @return
	 */
	boolean nulls() default true;

	/**
	 * 默认查询空字符串，仅适合目标 {@link QueryNull#value()} 字段为字符串
	 * @return
	 */
	boolean empty() default false;

	/**
	 * null或empty， 仅适合目标 {@link QueryNull#value()} 字段为字符串
	 * @return
	 */
	boolean nullsOrEmpty() default false;
}
