package com.particle.agi.app.agent.executor;

import com.particle.agi.app.agent.structmapping.AgiAgentAppStructMapping;
import com.particle.agi.client.agent.dto.command.AgiAgentUpdateCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
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
 * 智能体 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentGateway agiAgentGateway;

	/**
	 * 执行 智能体 更新指令
	 * @param agiAgentUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentVO> execute(@Valid AgiAgentUpdateCommand agiAgentUpdateCommand) {
		AgiAgent agiAgent = createByAgiAgentUpdateCommand(agiAgentUpdateCommand);
		agiAgent.setUpdateControl(agiAgentUpdateCommand);
		boolean save = agiAgentGateway.save(agiAgent);
		if (save) {
			return SingleResponse.of(AgiAgentAppStructMapping.instance.toAgiAgentVO(agiAgent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体更新指令创建智能体模型
	 * @param agiAgentUpdateCommand
	 * @return
	 */
	private AgiAgent createByAgiAgentUpdateCommand(AgiAgentUpdateCommand agiAgentUpdateCommand){
		AgiAgent agiAgent = AgiAgent.create();
		AgiAgentUpdateCommandToAgiAgentMapping.instance.fillAgiAgentByAgiAgentUpdateCommand(agiAgent, agiAgentUpdateCommand);
		return agiAgent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentUpdateCommandToAgiAgentMapping{
		AgiAgentUpdateCommandToAgiAgentMapping instance = Mappers.getMapper(AgiAgentUpdateCommandToAgiAgentMapping.class );

		default AgiAgentId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgent
		 * @param agiAgentUpdateCommand
		 */
		void fillAgiAgentByAgiAgentUpdateCommand(@MappingTarget AgiAgent agiAgent, AgiAgentUpdateCommand agiAgentUpdateCommand);
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
