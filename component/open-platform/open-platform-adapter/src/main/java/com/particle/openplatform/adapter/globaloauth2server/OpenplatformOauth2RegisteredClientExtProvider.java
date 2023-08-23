package com.particle.openplatform.adapter.globaloauth2server;

import com.particle.global.security.authorizationserver.Oauth2RegisteredClientExtProvider;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 开放接口提供app与接口访问范围
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 17:20
 */
@Component
public class OpenplatformOauth2RegisteredClientExtProvider implements Oauth2RegisteredClientExtProvider {

	@Autowired
	private IOpenplatformAppService iOpenplatformAppService;

	@Override
	public List<String> getExtScopesByClientId(String clientId) {
		return iOpenplatformAppService.getPermissionsByAppId(clientId);
	}
}
