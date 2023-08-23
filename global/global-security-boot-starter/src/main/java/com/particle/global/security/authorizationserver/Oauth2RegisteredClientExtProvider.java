package com.particle.global.security.authorizationserver;

import java.util.List;

/**
 * <p>
 * oauth2 注册客户端扩展
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 17:14
 */
public interface Oauth2RegisteredClientExtProvider {

	/**
	 * 获取额外的扩展访问范围
	 * @param clientId
	 * @return
	 */
	List<String> getExtScopesByClientId(String clientId);
}
