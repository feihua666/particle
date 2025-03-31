package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.agi.domain.chat.AgiAgentChat;
import com.particle.agi.domain.chat.AgiAgentChatId;
import com.particle.agi.domain.chat.gateway.AgiAgentChatGateway;
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
 * 智能体对话 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentChatUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatGateway agiAgentChatGateway;

	/**
	 * 执行 智能体对话 更新指令
	 * @param agiAgentChatUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatVO> execute(@Valid AgiAgentChatUpdateCommand agiAgentChatUpdateCommand) {
		AgiAgentChat agiAgentChat = createByAgiAgentChatUpdateCommand(agiAgentChatUpdateCommand);
		agiAgentChat.setUpdateControl(agiAgentChatUpdateCommand);
		boolean save = agiAgentChatGateway.save(agiAgentChat);
		if (save) {
			return SingleResponse.of(AgiAgentChatAppStructMapping.instance.toAgiAgentChatVO(agiAgentChat));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话更新指令创建智能体对话模型
	 * @param agiAgentChatUpdateCommand
	 * @return
	 */
	private AgiAgentChat createByAgiAgentChatUpdateCommand(AgiAgentChatUpdateCommand agiAgentChatUpdateCommand){
		AgiAgentChat agiAgentChat = AgiAgentChat.create();
		AgiAgentChatUpdateCommandToAgiAgentChatMapping.instance.fillAgiAgentChatByAgiAgentChatUpdateCommand(agiAgentChat, agiAgentChatUpdateCommand);
		return agiAgentChat;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentChatUpdateCommandToAgiAgentChatMapping{
		AgiAgentChatUpdateCommandToAgiAgentChatMapping instance = Mappers.getMapper(AgiAgentChatUpdateCommandToAgiAgentChatMapping.class );

		default AgiAgentChatId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentChatId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChat
		 * @param agiAgentChatUpdateCommand
		 */
		void fillAgiAgentChatByAgiAgentChatUpdateCommand(@MappingTarget AgiAgentChat agiAgentChat, AgiAgentChatUpdateCommand agiAgentChatUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentChatGateway
	 */
	@Autowired
	public void setAgiAgentChatGateway(AgiAgentChatGateway agiAgentChatGateway) {
		this.agiAgentChatGateway = agiAgentChatGateway;
	}
}
