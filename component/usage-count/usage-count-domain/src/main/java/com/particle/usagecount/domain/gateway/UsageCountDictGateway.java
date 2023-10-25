package com.particle.usagecount.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 使用次数字典
 * </p>
 *
 * @author yangwei
 * @since 2023-10-23 11:12:49
 */
public interface UsageCountDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
