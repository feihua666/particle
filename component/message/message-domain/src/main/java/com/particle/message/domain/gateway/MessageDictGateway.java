package com.particle.message.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 消息使用到的字典防腐层
 * </p>
 *
 * @author yangwei
 * @since 2023-05-18 17:22
 */
public interface MessageDictGateway extends IGateway {

	Long getDictIdByGroupCodeAndItemValue(String groupCode, String value);

}
