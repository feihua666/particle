package com.particle.openplatform.adapter.globalopenapi;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.GlobalOpenapiClientProvider;
import com.particle.global.openapi.data.OpenapiAlgorithmSecret;
import com.particle.global.openapi.data.OpenapiClient;
import com.particle.global.security.authorizationserver.Oauth2RegisteredClientExtProvider;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 开放平台实现客户端app存储
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 16:29
 */
@Component
public class OpenplatformJdbcOpenapiClientProvider implements GlobalOpenapiClientProvider {

	@Autowired
	private IOpenplatformAppService iOpenplatformAppService;

	@Autowired
	private OpenplatformOpenapiClientSecretProvider openplatformOpenapiClientSecretProvider;

	@Autowired(required = false)
	private RegisteredClientRepository registeredClientRepository;

	@Autowired(required = false)
	private Oauth2RegisteredClientExtProvider oauth2RegisteredClientExtProvider;

	@Override
	public OpenapiClient getOpenapiClientByClientId(String clientId,boolean includeSecret,boolean includeAuthorities) {
		OpenplatformAppDO appDO = iOpenplatformAppService.getByAppId(clientId);
		if (appDO == null) {
			return null;
		}
		String clientSecret = null;
		if (includeSecret) {
			clientSecret = openplatformOpenapiClientSecretProvider.getClientSecretByClientId(clientId);
		}
		Set<String> scopes = null;
		if (includeAuthorities) {
			scopes = getScopes(clientId);
		}
		return OpenapiClient.create(
				clientId,
				clientSecret,
				appDO.getIsCheckSignature(),
				OpenapiAlgorithmSecret.createFromJsonStr(appDO.getRequestAlgorithmSecretJson()),
				OpenapiAlgorithmSecret.createFromJsonStr(appDO.getResponseAlgorithmSecretJson()),
				scopes,
				appDO.getIsDisabled()
		);
	}

	/**
	 * 获取接口的权限
	 * @param clientId
	 * @return
	 */
	private Set<String> getScopes(String clientId) {
		/**
		 * 该查询结果已经包括了 oauth2RegisteredClientExtProvider 逻辑
		 */
		if (registeredClientRepository != null) {
			RegisteredClient byClientId = registeredClientRepository.findByClientId(clientId);
			return byClientId.getScopes();
		}
		if (oauth2RegisteredClientExtProvider != null) {
			List<String> extScopesByClientId = oauth2RegisteredClientExtProvider.getExtScopesByClientId(clientId);
			if (CollectionUtil.isNotEmpty(extScopesByClientId)) {
				return new HashSet<>(extScopesByClientId);
			}
		}
		return Collections.emptySet();
	}
}
