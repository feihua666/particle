package com.particle.agi.app.chat.executor;

import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolcallGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息工具调用 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
@Validated
public class AgiAgentChatMessageToolcallCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway;
	private IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService;
	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageToolcallGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageToolcallGateway(AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway) {
		this.agiAgentChatMessageToolcallGateway = agiAgentChatMessageToolcallGateway;
	}
	@Autowired
	public void setIAgiAgentChatMessageToolcallService(IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService) {
		this.iAgiAgentChatMessageToolcallService = iAgiAgentChatMessageToolcallService;
	}
}
