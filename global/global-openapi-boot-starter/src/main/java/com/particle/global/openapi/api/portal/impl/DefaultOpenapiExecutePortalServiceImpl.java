package com.particle.global.openapi.api.portal.impl;

import com.particle.global.exception.ExceptionFactory;
import com.particle.global.openapi.api.portal.OpenapiExecutePortalService;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 默认开放接口入口实现
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 16:41
 */
public class DefaultOpenapiExecutePortalServiceImpl implements OpenapiExecutePortalService {

	@Autowired
	private List<OpenapiExecuteProviderLoadBalancer>  openapiExecuteProviderLoadBalancers;

	@Override
	public <T> T  execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		return providerLoadBalance(openapiCommand,openapiContext);
	}

	/**
	 * 针对多个provider时的负载策略
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	public <T> T  providerLoadBalance(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		for (OpenapiExecuteProviderLoadBalancer openapiExecuteProviderLoadBalancer : openapiExecuteProviderLoadBalancers) {
			if (openapiExecuteProviderLoadBalancer.support(openapiCommand,openapiContext)) {
				return openapiExecuteProviderLoadBalancer.execute(openapiCommand, openapiContext);
			}
		}
		throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_LOADBALANCER_NOT_EXIST);
	}
}
