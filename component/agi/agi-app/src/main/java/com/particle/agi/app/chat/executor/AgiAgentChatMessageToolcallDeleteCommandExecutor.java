package com.particle.agi.app.chat.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolcallAppStructMapping;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolcallGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息工具调用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
@Validated
public class AgiAgentChatMessageToolcallDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway;
	private IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService;

	/**
	 * 执行 智能体对话消息工具调用 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolcallVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId = AgiAgentChatMessageToolcallId.of(deleteCommand.getId());
		AgiAgentChatMessageToolcall byId = agiAgentChatMessageToolcallGateway.getById(agiAgentChatMessageToolcallId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentChatMessageToolcallGateway.delete(agiAgentChatMessageToolcallId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentChatMessageToolcallAppStructMapping.instance.toAgiAgentChatMessageToolcallVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
