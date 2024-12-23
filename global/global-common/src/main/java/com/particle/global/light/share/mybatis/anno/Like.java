package com.particle.global.light.share.mybatis.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个查询条件的关键字，适用于字符串
 * 仅限调用IBaseService中的重载方法有效
 *
 * left 和 right 都为 true 进行 like 查询
 * left 和 right 都为 false 进行 likeLeft 查询
 * @author yangwei
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface Like {
	/**
	 * 默认不进行左模糊查询
	 * @return
	 */
	boolean left() default false;

	/**
	 * 默认右模糊查询
	 * @return
	 */
	boolean right() default true;
}
