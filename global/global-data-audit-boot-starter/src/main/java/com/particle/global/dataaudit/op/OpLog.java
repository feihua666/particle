package com.particle.global.dataaudit.op;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 操作日志注解
 * 操作日志一般从应用程序入口开始记录，一般一个接口就算一次操作，操作日志并不建议查询使用，一般使用增、删、改的情况记录日志
 * 标记该注解的 spring 组件，切面拦截并记录日志，建议注释在 controller 方法上
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 14:45:07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
	/**
	 * 操作名称，如：添加用户
	 * @return
	 */
	String name();

	/**
	 * 所属模块，一般意义而言是操作名称的一个分组,可参考 {@link OpLogModule}
	 * 这里没有定义成枚举，因为 {@link OpLogModule} 只是一个子集，仅供参考
	 * @return
	 */
	String module();


	/**
	 * 操作类型，标识一个操作类型 可参考 {@link OpLogType}
	 * 这里没有定义成枚举，因为 {@link OpLogType} 只是一个子集，仅供参考
	 * @return
	 */
	String type();


	/**
	 * 忽略数据审计功能，默认不忽略
	 * 在一些处理多数据时，只记录操作日志，但不想记录数据审计日志，可以忽略
	 * @return
	 */
	boolean ignoreDataAuditPublish() default false;

}
