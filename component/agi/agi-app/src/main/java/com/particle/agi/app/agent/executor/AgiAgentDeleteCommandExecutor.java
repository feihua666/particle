package com.particle.agi.app.agent.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.agent.structmapping.AgiAgentAppStructMapping;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import com.particle.agi.domain.agent.gateway.AgiAgentGateway;
import com.particle.agi.infrastructure.agent.service.IAgiAgentService;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 智能体 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Component
@Validated
public class AgiAgentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentGateway agiAgentGateway;
	private IAgiAgentService iAgiAgentService;

	/**
	 * 执行 智能体 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiAgentVO> execute(@Valid IdCommand deleteCommand) {
		AgiAgentId agiAgentId = AgiAgentId.of(deleteCommand.getId());
		AgiAgent byId = agiAgentGateway.getById(agiAgentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiAgentGateway.delete(agiAgentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(AgiAgentAppStructMapping.instance.toAgiAgentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param agiAgentGateway
	 */
	@Autowired
	public void setAgiAgentGateway(AgiAgentGateway agiAgentGateway) {
		this.agiAgentGateway = agiAgentGateway;
	}
	@Autowired
	public void setIAgiAgentService(IAgiAgentService iAgiAgentService) {
		this.iAgiAgentService = iAgiAgentService;
	}
}
