package com.particle.func.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

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
}
