package com.particle.agi.app.chat.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageMediaAppStructMapping;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.AgiAgentChatMessageMediaId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageMediaGateway;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体对话消息媒体 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
@Validated
public class AgiAgentChatMessageMediaDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway;
	private IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService;

	/**
	 * 执行 智能体对话消息媒体 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageMediaVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentChatMessageMediaId agiAgentChatMessageMediaId = AgiAgentChatMessageMediaId.of(deleteCommand.getId());
		AgiAgentChatMessageMedia byId = agiAgentChatMessageMediaGateway.getById(agiAgentChatMessageMediaId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentChatMessageMediaGateway.delete(agiAgentChatMessageMediaId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentChatMessageMediaAppStructMapping.instance.toAgiAgentChatMessageMediaVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
