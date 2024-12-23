package com.particle.message.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;

/**
 * <p>
 * 消息 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
public interface MessageGateway extends IBaseGateway<MessageId,Message> {
}
