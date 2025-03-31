package com.particle.agi.app.chat.executor;

import com.particle.agi.domain.chat.gateway.AgiAgentChatGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
@Validated
public class AgiAgentChatCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatGateway agiAgentChatGateway;
	private IAgiAgentChatService iAgiAgentChatService;
	/**
	 * 注入使用set方法
	 * @param agiAgentChatGateway
	 */
	@Autowired
	public void setAgiAgentChatGateway(AgiAgentChatGateway agiAgentChatGateway) {
		this.agiAgentChatGateway = agiAgentChatGateway;
	}
	@Autowired
	public void setIAgiAgentChatService(IAgiAgentChatService iAgiAgentChatService) {
		this.iAgiAgentChatService = iAgiAgentChatService;
	}
}
