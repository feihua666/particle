package com.particle.user.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 用户字典
 * </p>
 *
 * @author yangwei
 * @since 2022-12-06 15:58
 */
public interface UserDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);
}
