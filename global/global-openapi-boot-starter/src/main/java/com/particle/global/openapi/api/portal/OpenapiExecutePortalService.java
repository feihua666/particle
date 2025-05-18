package com.particle.global.openapi.api.portal;

import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;

/**
 * <p>
 * 开放接口入口服务
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 14:35
 */
public interface OpenapiExecutePortalService {


	/**
	 * 执行入口
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	<T> T execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext);
}
