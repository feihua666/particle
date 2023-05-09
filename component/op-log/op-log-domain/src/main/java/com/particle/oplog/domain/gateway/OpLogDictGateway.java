package com.particle.oplog.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.Map;

/**
 * <p>
 * 操作日志字典
 * </p>
 *
 * @author yangwei
 * @since 2022-12-06 15:58
 */
public interface OpLogDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	/**
	 * key为value，value为id
	 * @param groupCode
	 * @return
	 */
	Map<String, Long> getItemsByGroupCode(String groupCode);
}
