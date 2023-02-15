package com.particle.lowcode.domain.generator.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 功能字典
 * </p>
 *
 * @author yangwei
 * @since 2023-02-15
 */
public interface LowcodeDictGateway extends IGateway {

	String getDictValueById(Long typeDictId);
}
