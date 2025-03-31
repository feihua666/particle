package com.particle.agi.app.chat.executor;

import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageMediaGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息媒体 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
@Validated
public class AgiAgentChatMessageMediaCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway;
	private IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService;
	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageMediaGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageMediaGateway(AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway) {
		this.agiAgentChatMessageMediaGateway = agiAgentChatMessageMediaGateway;
	}
	@Autowired
	public void setIAgiAgentChatMessageMediaService(IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService) {
		this.iAgiAgentChatMessageMediaService = iAgiAgentChatMessageMediaService;
	}
}
