package com.particle.global.openapi.api.portal.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.data.ApiRuleInfo;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;

import java.util.List;

/**
 * <p>
 * 按指定的供应商处理
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 11:38
 */
public class OpenapiSpecifyExecuteProviderLoadBalancerImpl implements OpenapiExecuteProviderLoadBalancer {
	@Override
	public boolean support(List<OpenapiExecuteProvider> openapiExecuteProviders, OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		ApiInfo apiInfo = openapiContext.getApiInfo();
		ApiRuleInfo apiRuleInfo = apiInfo.getApiRuleInfo();
		return apiRuleInfo != null && StrUtil.isNotEmpty(apiRuleInfo.getProviderIdentifier());
	}

	@Override
	public Object execute(List<OpenapiExecuteProvider> openapiExecuteProviders, OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		String providerIdentifier = openapiContext.getApiInfo().getApiRuleInfo().getProviderIdentifier();
		for (OpenapiExecuteProvider openapiExecuteProvider : openapiExecuteProviders) {
			if (StrUtil.equals(openapiExecuteProvider.getProviderIdentifier(),providerIdentifier)) {
				return openapiExecuteProvider.execute(openapiCommand, openapiContext);
			}
		}
		// 如果能走到这里，一般是数据配置错误，指定了供应商但接口配置中已经不支持了（已经修改了数据）
		throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_EXECUTE_PROVIDER_NOT_EXIST);

	}
}
