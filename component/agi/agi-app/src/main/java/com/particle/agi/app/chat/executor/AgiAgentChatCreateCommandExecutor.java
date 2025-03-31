package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.agi.domain.chat.AgiAgentChat;
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
 * 智能体对话 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
@Validated
public class AgiAgentChatCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatGateway agiAgentChatGateway;

	/**
	 * 执行智能体对话添加指令
	 * @param agiAgentChatCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatVO> execute(@Valid AgiAgentChatCreateCommand agiAgentChatCreateCommand) {
		AgiAgentChat agiAgentChat = createByAgiAgentChatCreateCommand(agiAgentChatCreateCommand);
		agiAgentChat.setAddControl(agiAgentChatCreateCommand);
		boolean save = agiAgentChatGateway.save(agiAgentChat);
		if (save) {
			return SingleResponse.of(AgiAgentChatAppStructMapping.instance.toAgiAgentChatVO(agiAgentChat));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话创建指令创建智能体对话模型
	 * @param agiAgentChatCreateCommand
	 * @return
	 */
	private AgiAgentChat createByAgiAgentChatCreateCommand(AgiAgentChatCreateCommand agiAgentChatCreateCommand){
		AgiAgentChat agiAgentChat = AgiAgentChat.create();
		AgiAgentChatCreateCommandToAgiAgentChatMapping.instance.fillAgiAgentChatByAgiAgentChatCreateCommand(agiAgentChat, agiAgentChatCreateCommand);
		return agiAgentChat;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentChatCreateCommandToAgiAgentChatMapping{
		AgiAgentChatCreateCommandToAgiAgentChatMapping instance = Mappers.getMapper( AgiAgentChatCreateCommandToAgiAgentChatMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChat
		 * @param agiAgentChatCreateCommand
		 */
		void fillAgiAgentChatByAgiAgentChatCreateCommand(@MappingTarget AgiAgentChat agiAgentChat, AgiAgentChatCreateCommand agiAgentChatCreateCommand);
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
