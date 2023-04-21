package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 功能字典
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 13:58:17
 */
public interface TenantDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
