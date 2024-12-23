package com.particle.message.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;
import com.particle.message.domain.gateway.MessageGateway;
import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.infrastructure.service.IMessageService;
import com.particle.message.infrastructure.structmapping.MessageInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Component
public class MessageGatewayImpl extends AbstractBaseGatewayImpl<MessageId,Message> implements MessageGateway {

	private IMessageService iMessageService;

	@Override
	public Message getById(MessageId messageId) {
		MessageDO byId = iMessageService.getById(messageId.getId());
		Message message = DomainFactory.create(Message.class);
		message = MessageInfrastructureStructMapping.instance. messageDOToMessage(message,byId);
		return message;
	}

	@Override
	public boolean doSave(Message message) {
		MessageDO messageDO = MessageInfrastructureStructMapping.instance.messageToMessageDO(message);
		if (messageDO.getId() == null) {
			messageDO.setAddControl(message.getAddControl());
			MessageDO add = iMessageService.add(messageDO);
			message.setId(MessageId.of(add.getId()));
			return add != null;
		}
		messageDO.setUpdateControl(message.getUpdateControl());
		MessageDO update = iMessageService.update(messageDO);
		return update != null;
	}

	@Override
	public boolean delete(MessageId messageId) {
		return iMessageService.deleteById(messageId.getId());
	}

	@Override
	public boolean delete(MessageId id, IdCommand idCommand) {
		return iMessageService.deleteById(idCommand);
	}

	@Autowired
	public void setIMessageService(IMessageService iMessageService) {
		this.iMessageService = iMessageService;
	}
}
