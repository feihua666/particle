package com.particle.agi.app.chat.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolAppStructMapping;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息工具 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
@Validated
public class AgiAgentChatMessageToolDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway;
	private IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService;

	/**
	 * 执行 智能体对话消息工具 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentChatMessageToolId agiAgentChatMessageToolId = AgiAgentChatMessageToolId.of(deleteCommand.getId());
		AgiAgentChatMessageTool byId = agiAgentChatMessageToolGateway.getById(agiAgentChatMessageToolId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentChatMessageToolGateway.delete(agiAgentChatMessageToolId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentChatMessageToolAppStructMapping.instance.toAgiAgentChatMessageToolVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
