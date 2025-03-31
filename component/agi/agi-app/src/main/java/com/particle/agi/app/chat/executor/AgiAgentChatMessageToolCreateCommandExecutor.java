package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
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
 * 智能体对话消息工具 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
@Validated
public class AgiAgentChatMessageToolCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolGateway agiAgentChatMessageToolGateway;

	/**
	 * 执行智能体对话消息工具添加指令
	 * @param agiAgentChatMessageToolCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolVO> execute(@Valid AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand) {
		AgiAgentChatMessageTool agiAgentChatMessageTool = createByAgiAgentChatMessageToolCreateCommand(agiAgentChatMessageToolCreateCommand);
		agiAgentChatMessageTool.setAddControl(agiAgentChatMessageToolCreateCommand);
		boolean save = agiAgentChatMessageToolGateway.save(agiAgentChatMessageTool);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageToolAppStructMapping.instance.toAgiAgentChatMessageToolVO(agiAgentChatMessageTool));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息工具创建指令创建智能体对话消息工具模型
	 * @param agiAgentChatMessageToolCreateCommand
	 * @return
	 */
	private AgiAgentChatMessageTool createByAgiAgentChatMessageToolCreateCommand(AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand){
		AgiAgentChatMessageTool agiAgentChatMessageTool = AgiAgentChatMessageTool.create();
		AgiAgentChatMessageToolCreateCommandToAgiAgentChatMessageToolMapping.instance.fillAgiAgentChatMessageToolByAgiAgentChatMessageToolCreateCommand(agiAgentChatMessageTool, agiAgentChatMessageToolCreateCommand);
		return agiAgentChatMessageTool;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentChatMessageToolCreateCommandToAgiAgentChatMessageToolMapping{
		AgiAgentChatMessageToolCreateCommandToAgiAgentChatMessageToolMapping instance = Mappers.getMapper( AgiAgentChatMessageToolCreateCommandToAgiAgentChatMessageToolMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageTool
		 * @param agiAgentChatMessageToolCreateCommand
		 */
		void fillAgiAgentChatMessageToolByAgiAgentChatMessageToolCreateCommand(@MappingTarget AgiAgentChatMessageTool agiAgentChatMessageTool, AgiAgentChatMessageToolCreateCommand agiAgentChatMessageToolCreateCommand);
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
