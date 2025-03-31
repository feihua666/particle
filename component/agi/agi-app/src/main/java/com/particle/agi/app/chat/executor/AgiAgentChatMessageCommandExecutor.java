package com.particle.agi.app.chat.executor;

import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
@Validated
public class AgiAgentChatMessageCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageGateway agiAgentChatMessageGateway;
	private IAgiAgentChatMessageService iAgiAgentChatMessageService;
	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageGateway(AgiAgentChatMessageGateway agiAgentChatMessageGateway) {
		this.agiAgentChatMessageGateway = agiAgentChatMessageGateway;
	}
	@Autowired
	public void setIAgiAgentChatMessageService(IAgiAgentChatMessageService iAgiAgentChatMessageService) {
		this.iAgiAgentChatMessageService = iAgiAgentChatMessageService;
	}
}
