package com.particle.global.openapi.api;

import java.util.Set;

/**
 * <p>
 * 提供额外的配置，主是要实现java编程配置匹配的开放接口
 * </p>
 *
 * @author yangwei
 * @since 2023-08-15 09:28
 */
public interface GlobalOpenapiUrlPatternConfigure {

	/**
	 * 配置url pattern 如：/user/**
	 * @return
	 */
	Set<String> urlPatterns();
}
