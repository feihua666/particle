package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageGateway;
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
 * 智能体对话消息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
@Validated
public class AgiAgentChatMessageCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageGateway agiAgentChatMessageGateway;

	/**
	 * 执行智能体对话消息添加指令
	 * @param agiAgentChatMessageCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageVO> execute(@Valid AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand) {
		AgiAgentChatMessage agiAgentChatMessage = createByAgiAgentChatMessageCreateCommand(agiAgentChatMessageCreateCommand);
		agiAgentChatMessage.setAddControl(agiAgentChatMessageCreateCommand);
		boolean save = agiAgentChatMessageGateway.save(agiAgentChatMessage);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageAppStructMapping.instance.toAgiAgentChatMessageVO(agiAgentChatMessage));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息创建指令创建智能体对话消息模型
	 * @param agiAgentChatMessageCreateCommand
	 * @return
	 */
	private AgiAgentChatMessage createByAgiAgentChatMessageCreateCommand(AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand){
		AgiAgentChatMessage agiAgentChatMessage = AgiAgentChatMessage.create();
		AgiAgentChatMessageCreateCommandToAgiAgentChatMessageMapping.instance.fillAgiAgentChatMessageByAgiAgentChatMessageCreateCommand(agiAgentChatMessage, agiAgentChatMessageCreateCommand);
		return agiAgentChatMessage;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentChatMessageCreateCommandToAgiAgentChatMessageMapping{
		AgiAgentChatMessageCreateCommandToAgiAgentChatMessageMapping instance = Mappers.getMapper( AgiAgentChatMessageCreateCommandToAgiAgentChatMessageMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessage
		 * @param agiAgentChatMessageCreateCommand
		 */
		void fillAgiAgentChatMessageByAgiAgentChatMessageCreateCommand(@MappingTarget AgiAgentChatMessage agiAgentChatMessage, AgiAgentChatMessageCreateCommand agiAgentChatMessageCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageGateway(AgiAgentChatMessageGateway agiAgentChatMessageGateway) {
		this.agiAgentChatMessageGateway = agiAgentChatMessageGateway;
	}
}
