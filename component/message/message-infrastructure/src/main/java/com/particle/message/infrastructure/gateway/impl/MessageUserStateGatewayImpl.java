package com.particle.message.infrastructure.gateway.impl;

import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import com.particle.message.domain.gateway.MessageUserStateGateway;
import com.particle.message.infrastructure.service.IMessageUserStateService;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.infrastructure.structmapping.MessageUserStateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户消息读取状态 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Component
public class MessageUserStateGatewayImpl extends AbstractBaseGatewayImpl<MessageUserStateId,MessageUserState> implements MessageUserStateGateway {

	private IMessageUserStateService iMessageUserStateService;

	@Override
	public MessageUserState getById(MessageUserStateId messageUserStateId) {
		MessageUserStateDO byId = iMessageUserStateService.getById(messageUserStateId.getId());
		MessageUserState messageUserState = DomainFactory.create(MessageUserState.class);
		messageUserState = MessageUserStateInfrastructureStructMapping.instance. messageUserStateDOToMessageUserState(messageUserState,byId);
		return messageUserState;
	}

	@Override
	public boolean doSave(MessageUserState messageUserState) {
		MessageUserStateDO messageUserStateDO = MessageUserStateInfrastructureStructMapping.instance.messageUserStateToMessageUserStateDO(messageUserState);
		if (messageUserStateDO.getId() == null) {
			messageUserStateDO.setAddControl(messageUserState.getAddControl());
			MessageUserStateDO add = iMessageUserStateService.add(messageUserStateDO);
			messageUserState.setId(MessageUserStateId.of(add.getId()));
			return add != null;
		}
		messageUserStateDO.setUpdateControl(messageUserState.getUpdateControl());
		MessageUserStateDO update = iMessageUserStateService.update(messageUserStateDO);
		return update != null;
	}

	@Override
	public boolean delete(MessageUserStateId messageUserStateId) {
		return iMessageUserStateService.deleteById(messageUserStateId.getId());
	}


	@Autowired
	public void setIMessageUserStateService(IMessageUserStateService iMessageUserStateService) {
		this.iMessageUserStateService = iMessageUserStateService;
	}
}
