package com.particle.global.security.authorizationserver;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 自定义jdbc注册客户端，为客户端获取提供额外功能，主要是提供额外的scopoe接口配置（由开放平台业务组件提供）
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 17:05
 */
public class CustomJdbcRegisteredClientRepository extends JdbcRegisteredClientRepository {

	@Autowired(required = false)
	private Oauth2RegisteredClientExtProvider oauth2RegisteredClientExtProvider;

	/**
	 * Constructs a {@code JdbcRegisteredClientRepository} using the provided parameters.
	 *
	 * @param jdbcOperations the JDBC operations
	 */
	public CustomJdbcRegisteredClientRepository(JdbcOperations jdbcOperations) {
		super(jdbcOperations);
	}

	@Override
	public RegisteredClient findById(String id) {
		RegisteredClient registeredClient = super.findById(id);
		fill(registeredClient);
		return registeredClient;
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		RegisteredClient registeredClient = super.findByClientId(clientId);
		fill(registeredClient);
		return registeredClient;
	}

	/**
	 * 额外扩展
	 * @param registeredClient
	 */
	private void fill(RegisteredClient registeredClient) {
		if (registeredClient == null) {
			return;
		}
		List<String> additionalScopes = additionalScopes(registeredClient.getClientId());
		if (CollectionUtil.isNotEmpty(additionalScopes)) {
			Set<String> newScopes = new HashSet<>();

			Set<String> scopes = registeredClient.getScopes();
			if(CollectionUtil.isNotEmpty(scopes)){
				newScopes.addAll(scopes);
				newScopes.addAll(additionalScopes);
			}
			Set<String> unmodifiableSet = Collections.unmodifiableSet(
					new HashSet<>(newScopes));

			ReflectUtil.setFieldValue(registeredClient,"scopes",unmodifiableSet);
		}
	}

	/**
	 * 获取额外数据范围
	 * @param clientId
	 * @return
	 */
	private List<String> additionalScopes(String clientId) {
		if (oauth2RegisteredClientExtProvider == null) {
			return Collections.emptyList();
		}
		return oauth2RegisteredClientExtProvider.getExtScopesByClientId(clientId);
	}
}
