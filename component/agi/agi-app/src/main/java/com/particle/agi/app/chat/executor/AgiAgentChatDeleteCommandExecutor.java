package com.particle.agi.app.chat.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.chat.structmapping.AgiAgentChatAppStructMapping;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.agi.domain.chat.AgiAgentChat;
import com.particle.agi.domain.chat.AgiAgentChatId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
@Validated
public class AgiAgentChatDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatGateway agiAgentChatGateway;
	private IAgiAgentChatService iAgiAgentChatService;

	/**
	 * 执行 智能体对话 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentChatId agiAgentChatId = AgiAgentChatId.of(deleteCommand.getId());
		AgiAgentChat byId = agiAgentChatGateway.getById(agiAgentChatId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentChatGateway.delete(agiAgentChatId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentChatAppStructMapping.instance.toAgiAgentChatVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
