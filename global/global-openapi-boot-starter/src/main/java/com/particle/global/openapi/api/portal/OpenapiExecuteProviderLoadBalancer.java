package com.particle.global.openapi.api.portal;

import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;

/**
 * <p>
 * 开放接口执行提供商负载策略实现
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 11:06
 */
public interface OpenapiExecuteProviderLoadBalancer {

	/**
	 * 是否支持
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	boolean support(OpenapiCommand openapiCommand, OpenapiContext openapiContext);

	/**
	 * 执行
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	public <T> T  execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext);
}
