package com.particle.agi.app.chat.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageAppStructMapping;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
@Validated
public class AgiAgentChatMessageDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageGateway agiAgentChatMessageGateway;
	private IAgiAgentChatMessageService iAgiAgentChatMessageService;

	/**
	 * 执行 智能体对话消息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentChatMessageId agiAgentChatMessageId = AgiAgentChatMessageId.of(deleteCommand.getId());
		AgiAgentChatMessage byId = agiAgentChatMessageGateway.getById(agiAgentChatMessageId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentChatMessageGateway.delete(agiAgentChatMessageId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentChatMessageAppStructMapping.instance.toAgiAgentChatMessageVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
