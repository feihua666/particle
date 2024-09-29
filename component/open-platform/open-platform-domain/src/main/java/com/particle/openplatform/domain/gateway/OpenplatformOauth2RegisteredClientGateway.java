package com.particle.openplatform.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemDTO;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemParam;

import java.util.List;
import java.util.Map;

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
