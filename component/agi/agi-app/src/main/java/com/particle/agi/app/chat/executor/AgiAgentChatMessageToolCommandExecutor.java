package com.particle.agi.app.chat.executor;

import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息工具 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
@Validated
public class AgiAgentChatMessageToolCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway;
	private IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService;
	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageToolGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageToolGateway(AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway) {
		this.agiAgentChatMessageToolGateway = agiAgentChatMessageToolGateway;
	}
	@Autowired
	public void setIAgiAgentChatMessageToolService(IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService) {
		this.iAgiAgentChatMessageToolService = iAgiAgentChatMessageToolService;
	}
}
