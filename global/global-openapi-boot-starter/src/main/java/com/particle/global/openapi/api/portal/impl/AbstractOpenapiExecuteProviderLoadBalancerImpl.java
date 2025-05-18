package com.particle.global.openapi.api.portal.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.portal.AdditionalOpenapiExecuteProviderFactory;
import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;
import com.particle.global.openapi.api.portal.OpenapiExecuteProviderLoadBalancer;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 基础的供应商处理
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 14:09:55
 */
public abstract class AbstractOpenapiExecuteProviderLoadBalancerImpl implements OpenapiExecuteProviderLoadBalancer {

	@Autowired(required = false)
	private List<OpenapiExecuteProvider> openapiExecuteProviders;

	@Autowired(required = false)
	private List<AdditionalOpenapiExecuteProviderFactory> additionalOpenapiExecuteProviderFactories;
	@Override
	public <T> T execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
		return doExecute(openapiCommand, openapiContext);
	}

	/**
	 * 执行
	 *
	 * @param openapiCommand
	 * @param openapiContext
	 * @param <T>
	 * @return
	 */
	public abstract <T> T doExecute(OpenapiCommand openapiCommand, OpenapiContext openapiContext);

	/**
	 * 获取所有提供商
	 * @return
	 */
	protected List<OpenapiExecuteProvider> fetchOpenapiExecuteProviders() {
		if (CollectionUtil.isEmpty(additionalOpenapiExecuteProviderFactories)) {
			return openapiExecuteProviders;
		}

		int openapiExecuteProvidersSize = openapiExecuteProviders == null ? 0 : openapiExecuteProviders.size();
		int additionalProvidersSize = 0;

		// 遍历工厂集合，预估额外提供商的数量
		for (AdditionalOpenapiExecuteProviderFactory factory : additionalOpenapiExecuteProviderFactories) {
			List<OpenapiExecuteProvider> additionalProviders = factory.additionalOpenapiExecuteProviders();
			if (CollectionUtil.isNotEmpty(additionalProviders)) {
				additionalProvidersSize += additionalProviders.size();
			}
		}

		// 根据预估的总大小初始化集合
		List<OpenapiExecuteProvider> allOpenapiExecuteProviders = new ArrayList<>(openapiExecuteProvidersSize + additionalProvidersSize);

		// 添加已有的提供商
		if (CollectionUtil.isNotEmpty(openapiExecuteProviders)) {
			allOpenapiExecuteProviders.addAll(openapiExecuteProviders);
		}

		// 添加额外的提供商
		for (AdditionalOpenapiExecuteProviderFactory factory : additionalOpenapiExecuteProviderFactories) {
			List<OpenapiExecuteProvider> additionalProviders = factory.additionalOpenapiExecuteProviders();
			if (CollectionUtil.isNotEmpty(additionalProviders)) {
				allOpenapiExecuteProviders.addAll(additionalProviders);
			}
		}

		return allOpenapiExecuteProviders;
	}
}
