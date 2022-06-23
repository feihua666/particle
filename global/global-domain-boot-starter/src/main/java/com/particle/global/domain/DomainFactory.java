package com.particle.global.domain;

/**
 * <p>
 * domain 领域模型通用工厂，从 spring 容器中获取
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 17:43
 */
public class DomainFactory {

	public static <T> T create(Class<T> entityClz){
		return ApplicationContextHelper.getBean(entityClz);
	}

}
