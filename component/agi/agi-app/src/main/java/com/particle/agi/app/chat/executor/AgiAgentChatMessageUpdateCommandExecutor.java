package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
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
 * 智能体对话消息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentChatMessageUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageGateway agiAgentChatMessageGateway;

	/**
	 * 执行 智能体对话消息 更新指令
	 * @param agiAgentChatMessageUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageVO> execute(@Valid AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand) {
		AgiAgentChatMessage agiAgentChatMessage = createByAgiAgentChatMessageUpdateCommand(agiAgentChatMessageUpdateCommand);
		agiAgentChatMessage.setUpdateControl(agiAgentChatMessageUpdateCommand);
		boolean save = agiAgentChatMessageGateway.save(agiAgentChatMessage);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageAppStructMapping.instance.toAgiAgentChatMessageVO(agiAgentChatMessage));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息更新指令创建智能体对话消息模型
	 * @param agiAgentChatMessageUpdateCommand
	 * @return
	 */
	private AgiAgentChatMessage createByAgiAgentChatMessageUpdateCommand(AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand){
		AgiAgentChatMessage agiAgentChatMessage = AgiAgentChatMessage.create();
		AgiAgentChatMessageUpdateCommandToAgiAgentChatMessageMapping.instance.fillAgiAgentChatMessageByAgiAgentChatMessageUpdateCommand(agiAgentChatMessage, agiAgentChatMessageUpdateCommand);
		return agiAgentChatMessage;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentChatMessageUpdateCommandToAgiAgentChatMessageMapping{
		AgiAgentChatMessageUpdateCommandToAgiAgentChatMessageMapping instance = Mappers.getMapper(AgiAgentChatMessageUpdateCommandToAgiAgentChatMessageMapping.class );

		default AgiAgentChatMessageId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentChatMessageId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessage
		 * @param agiAgentChatMessageUpdateCommand
		 */
		void fillAgiAgentChatMessageByAgiAgentChatMessageUpdateCommand(@MappingTarget AgiAgentChatMessage agiAgentChatMessage, AgiAgentChatMessageUpdateCommand agiAgentChatMessageUpdateCommand);
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
