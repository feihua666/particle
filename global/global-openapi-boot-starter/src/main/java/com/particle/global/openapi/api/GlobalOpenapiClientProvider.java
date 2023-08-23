package com.particle.global.openapi.api;

import com.particle.global.openapi.data.OpenapiClient;

/**
 * <p>
 * 开放接口客户端提供商
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:10
 */
public interface GlobalOpenapiClientProvider {

	/**
	 * 根据客户端id获取客户端信息
	 * @param clientId
	 * @param includeSecret 是否包括密码，为了提高查询效率省去不必要的查询
	 * @param includeAuthorities 是否包括权限，为了提高查询效率省去不必要的查询
	 * @return
	 */
	OpenapiClient getOpenapiClientByClientId(String clientId,boolean includeSecret,boolean includeAuthorities);
}
