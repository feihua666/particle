package com.particle.openplatform.adapter.globalopenapi;

/**
 * <p>
 * 主要是解耦依赖问题，从oauth2 registered client 获取密码
 * </p>
 *
 * @author yangwei
 * @since 2023-08-08 16:41
 */
public interface OpenplatformOpenapiClientSecretProvider {

	/**
	 * 根据客户端id获取客户端加密密码
	 * 该加密密码应该不带加密类型前缀如：如果存储的是{noop}xxxxx，则应该返回xxxxx，因为不需要验证密码，只是用密码参与摘要算法计算
	 * @param clientId
	 * @return
	 */
	String getClientSecretByClientId(String clientId);
}
