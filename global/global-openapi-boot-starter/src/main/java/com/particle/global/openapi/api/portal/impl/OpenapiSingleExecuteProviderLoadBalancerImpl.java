package com.particle.global.openapi.api.portal.impl;

import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;

import java.util.List;

/**
 * <p>
 * 当只有一个供应商时，直接使用
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 11:38
 */
public class OpenapiSingleExecuteProviderLoadBalancerImpl implements OpenapiExecuteProviderLoadBalancer {
	@Override
	public boolean support(List<OpenapiExecuteProvider> openapiExecuteProviders, OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		return openapiExecuteProviders.size() == 1;
	}

	@Override
	public Object execute(List<OpenapiExecuteProvider> openapiExecuteProviders, OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		return openapiExecuteProviders.iterator().next().execute(openapiCommand, openapiContext);
	}
}
