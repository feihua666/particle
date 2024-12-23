package com.particle.message.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;
import com.particle.message.domain.gateway.MessageTemplateGateway;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.message.infrastructure.service.IMessageTemplateService;
import com.particle.message.infrastructure.structmapping.MessageTemplateInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息模板 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Component
public class MessageTemplateGatewayImpl extends AbstractBaseGatewayImpl<MessageTemplateId,MessageTemplate> implements MessageTemplateGateway {

	private IMessageTemplateService iMessageTemplateService;

	@Override
	public MessageTemplate getById(MessageTemplateId messageTemplateId) {
		MessageTemplateDO byId = iMessageTemplateService.getById(messageTemplateId.getId());
		MessageTemplate messageTemplate = DomainFactory.create(MessageTemplate.class);
		messageTemplate = MessageTemplateInfrastructureStructMapping.instance. messageTemplateDOToMessageTemplate(messageTemplate,byId);
		return messageTemplate;
	}

	@Override
	public boolean doSave(MessageTemplate messageTemplate) {
		MessageTemplateDO messageTemplateDO = MessageTemplateInfrastructureStructMapping.instance.messageTemplateToMessageTemplateDO(messageTemplate);
		if (messageTemplateDO.getId() == null) {
			messageTemplateDO.setAddControl(messageTemplate.getAddControl());
			MessageTemplateDO add = iMessageTemplateService.add(messageTemplateDO);
			messageTemplate.setId(MessageTemplateId.of(add.getId()));
			return add != null;
		}
		messageTemplateDO.setUpdateControl(messageTemplate.getUpdateControl());
		MessageTemplateDO update = iMessageTemplateService.update(messageTemplateDO);
		return update != null;
	}

	@Override
	public boolean delete(MessageTemplateId messageTemplateId) {
		return iMessageTemplateService.deleteById(messageTemplateId.getId());
	}

	@Override
	public boolean delete(MessageTemplateId id, IdCommand idCommand) {
		return iMessageTemplateService.deleteById(idCommand);
	}

	@Autowired
	public void setIMessageTemplateService(IMessageTemplateService iMessageTemplateService) {
		this.iMessageTemplateService = iMessageTemplateService;
	}
}
