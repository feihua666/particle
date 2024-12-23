package com.particle.openplatform.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * oauth2 客户端
 * </p>
 *
 * @author yangwei
 * @since 2024-09-14 16:49:25
 */
public interface OpenplatformOauth2RegisteredClientGateway extends IGateway {

	/**
	 * 获取appId对应的密钥
	 * @param appId
	 * @return
	 */
	String getSecretByAppId(String appId);

}
