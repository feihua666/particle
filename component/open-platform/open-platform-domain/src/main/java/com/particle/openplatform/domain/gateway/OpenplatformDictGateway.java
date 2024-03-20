package com.particle.openplatform.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.Map;

/**
 * <p>
 * 开放接口字典
 * </p>
 *
 * @author yangwei
 * @since 2024-03-19 18:05:33
 */
public interface OpenplatformDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	/**
	 * key为id，value为value
	 * @param groupCode
	 * @return
	 */
	Map<Long, String> getItemsByGroupCode(String groupCode);
}
