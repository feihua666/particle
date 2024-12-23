package com.particle.message.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;

/**
 * <p>
 * 消息模板 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
public interface MessageTemplateGateway extends IBaseGateway<MessageTemplateId,MessageTemplate> {
}
