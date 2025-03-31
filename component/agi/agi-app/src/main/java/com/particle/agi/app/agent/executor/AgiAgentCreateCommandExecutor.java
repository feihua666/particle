package com.particle.agi.app.agent.executor;

import com.particle.agi.app.agent.structmapping.AgiAgentAppStructMapping;
import com.particle.agi.client.agent.dto.command.AgiAgentCreateCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.gateway.AgiAgentGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class AgiAgentCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentGateway agiAgentGateway;

	/**
	 * 执行智能体添加指令
	 * @param agiAgentCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentVO> execute(@Valid AgiAgentCreateCommand agiAgentCreateCommand) {
		AgiAgent agiAgent = createByAgiAgentCreateCommand(agiAgentCreateCommand);
		agiAgent.setAddControl(agiAgentCreateCommand);
		boolean save = agiAgentGateway.save(agiAgent);
		if (save) {
			return SingleResponse.of(AgiAgentAppStructMapping.instance.toAgiAgentVO(agiAgent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体创建指令创建智能体模型
	 * @param agiAgentCreateCommand
	 * @return
	 */
	private AgiAgent createByAgiAgentCreateCommand(AgiAgentCreateCommand agiAgentCreateCommand){
		AgiAgent agiAgent = AgiAgent.create();
		AgiAgentCreateCommandToAgiAgentMapping.instance.fillAgiAgentByAgiAgentCreateCommand(agiAgent, agiAgentCreateCommand);
		return agiAgent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentCreateCommandToAgiAgentMapping{
		AgiAgentCreateCommandToAgiAgentMapping instance = Mappers.getMapper( AgiAgentCreateCommandToAgiAgentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgent
		 * @param agiAgentCreateCommand
		 */
		void fillAgiAgentByAgiAgentCreateCommand(@MappingTarget AgiAgent agiAgent, AgiAgentCreateCommand agiAgentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentGateway
	 */
	@Autowired
	public void setAgiAgentGateway(AgiAgentGateway agiAgentGateway) {
		this.agiAgentGateway = agiAgentGateway;
	}
}
