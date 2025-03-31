package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolGateway;
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
 * 智能体对话消息工具 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentChatMessageToolUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway;

	/**
	 * 执行 智能体对话消息工具 更新指令
	 * @param agiAgentChatMessageToolUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolVO> execute(@Valid AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand) {
		AgiAgentChatMessageTool agiAgentChatMessageTool = createByAgiAgentChatMessageToolUpdateCommand(agiAgentChatMessageToolUpdateCommand);
		agiAgentChatMessageTool.setUpdateControl(agiAgentChatMessageToolUpdateCommand);
		boolean save = agiAgentChatMessageToolGateway.save(agiAgentChatMessageTool);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageToolAppStructMapping.instance.toAgiAgentChatMessageToolVO(agiAgentChatMessageTool));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息工具更新指令创建智能体对话消息工具模型
	 * @param agiAgentChatMessageToolUpdateCommand
	 * @return
	 */
	private AgiAgentChatMessageTool createByAgiAgentChatMessageToolUpdateCommand(AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand){
		AgiAgentChatMessageTool agiAgentChatMessageTool = AgiAgentChatMessageTool.create();
		AgiAgentChatMessageToolUpdateCommandToAgiAgentChatMessageToolMapping.instance.fillAgiAgentChatMessageToolByAgiAgentChatMessageToolUpdateCommand(agiAgentChatMessageTool, agiAgentChatMessageToolUpdateCommand);
		return agiAgentChatMessageTool;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentChatMessageToolUpdateCommandToAgiAgentChatMessageToolMapping{
		AgiAgentChatMessageToolUpdateCommandToAgiAgentChatMessageToolMapping instance = Mappers.getMapper(AgiAgentChatMessageToolUpdateCommandToAgiAgentChatMessageToolMapping.class );

		default AgiAgentChatMessageToolId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentChatMessageToolId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageTool
		 * @param agiAgentChatMessageToolUpdateCommand
		 */
		void fillAgiAgentChatMessageToolByAgiAgentChatMessageToolUpdateCommand(@MappingTarget AgiAgentChatMessageTool agiAgentChatMessageTool, AgiAgentChatMessageToolUpdateCommand agiAgentChatMessageToolUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageToolGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageToolGateway(AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway) {
		this.agiAgentChatMessageToolGateway = agiAgentChatMessageToolGateway;
	}
}
