package com.particle.global.openapi.api.portal.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.openapi.api.portal.OpenapiExecutePortalService;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 默认开放接口入口实现
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 16:41
 */
public class DefaultOpenapiExecutePortalServiceImpl implements OpenapiExecutePortalService {

	@Autowired(required = false)
	private List<OpenapiExecuteProvider> openapiExecuteProviders;
	@Autowired
	private List<OpenapiExecuteProviderLoadBalancer>  openapiExecuteProviderLoadBalancers;

	@Override
	public Object execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		ApiInfo apiInfo = openapiContext.getApiInfo();
		if (apiInfo == null) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.URL_NOT_FOUND);
		}

		String apiCode = apiInfo.getApiCode();
		List<OpenapiExecuteProvider> openapiExecuteProviderList = null;
		if (CollectionUtil.isNotEmpty(openapiExecuteProviders)) {
			openapiExecuteProviderList = openapiExecuteProviders.stream().filter(item -> item.supportApi(apiCode)).collect(Collectors.toList());
		}
		if (CollectionUtil.isEmpty(openapiExecuteProviderList)) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.URL_NOT_FOUND);
		}
		return provierLoadBalance(openapiExecuteProviderList,openapiCommand,openapiContext);
	}

	/**
	 * 针对多个provider时的负载策略
	 * @param openapiExecuteProviders
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	public Object provierLoadBalance(List<OpenapiExecuteProvider> openapiExecuteProviders,OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		for (OpenapiExecuteProviderLoadBalancer openapiExecuteProviderLoadBalancer : openapiExecuteProviderLoadBalancers) {
			if (openapiExecuteProviderLoadBalancer.support(openapiExecuteProviders,openapiCommand,openapiContext)) {
				return openapiExecuteProviderLoadBalancer.execute(openapiExecuteProviders, openapiCommand, openapiContext);
			}
		}
		throw ExceptionFactory.bizException(ErrorCodeOpenapiEnum.OPENAPI_LOADBALANCER_NOT_EXIST);

	}
}
