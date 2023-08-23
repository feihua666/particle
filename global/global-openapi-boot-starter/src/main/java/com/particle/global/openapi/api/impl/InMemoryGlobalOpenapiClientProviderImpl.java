package com.particle.global.openapi.api.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.GlobalOpenapiClientProvider;
import com.particle.global.openapi.data.OpenapiClient;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 内存存储
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 14:57
 */
public class InMemoryGlobalOpenapiClientProviderImpl implements GlobalOpenapiClientProvider {

	private List<OpenapiClient> clients;

	public InMemoryGlobalOpenapiClientProviderImpl(List<OpenapiClient> clients) {
		this.clients = clients;
	}

	@Override
	public OpenapiClient getOpenapiClientByClientId(String clientId,boolean includeSecret,boolean includeAuthorities) {
		if (CollectionUtil.isNotEmpty(clients)) {
			return clients.stream().filter(item -> Objects.equals(clientId, item.getClientId())).findFirst().orElse(null);
		}
		return null;
	}
}
