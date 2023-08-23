package com.particle.component.adapter.oauth2authorization;

import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.service.IOauth2RegisteredClientService;
import com.particle.openplatform.adapter.globalopenapi.OpenplatformOpenapiClientSecretProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 获取客户端密码
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 16:53
 */
public class Oauth2RegisteredClientOpenapiClientSecretProvider implements OpenplatformOpenapiClientSecretProvider {

	@Autowired
	private IOauth2RegisteredClientService iOauth2RegisteredClientService;

	@Override
	public String getClientSecretByClientId(String clientId) {
		Oauth2RegisteredClientDO secretByClientId = iOauth2RegisteredClientService.getSecretByClientId(clientId);
		if (secretByClientId == null) {
			return null;
		}
		return secretByClientId.getClientSecret();
	}
}
