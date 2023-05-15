package com.particle.func.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.Map;

/**
 * <p>
 * 功能字典
 * </p>
 *
 * @author yangwei
 * @since 2022-12-06 15:58
 */
public interface FuncDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	/**
	 * key为id，value为value
	 * @param groupCode
	 * @return
	 */
	Map<Long, String> getItemsByGroupCode(String groupCode);
}
