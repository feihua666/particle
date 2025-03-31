package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageMediaAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageMediaGateway;
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
 * 智能体对话消息媒体 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
@Validated
public class AgiAgentChatMessageMediaCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway;

	/**
	 * 执行智能体对话消息媒体添加指令
	 * @param agiAgentChatMessageMediaCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageMediaVO> execute(@Valid AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand) {
		AgiAgentChatMessageMedia agiAgentChatMessageMedia = createByAgiAgentChatMessageMediaCreateCommand(agiAgentChatMessageMediaCreateCommand);
		agiAgentChatMessageMedia.setAddControl(agiAgentChatMessageMediaCreateCommand);
		boolean save = agiAgentChatMessageMediaGateway.save(agiAgentChatMessageMedia);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageMediaAppStructMapping.instance.toAgiAgentChatMessageMediaVO(agiAgentChatMessageMedia));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息媒体创建指令创建智能体对话消息媒体模型
	 * @param agiAgentChatMessageMediaCreateCommand
	 * @return
	 */
	private AgiAgentChatMessageMedia createByAgiAgentChatMessageMediaCreateCommand(AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand){
		AgiAgentChatMessageMedia agiAgentChatMessageMedia = AgiAgentChatMessageMedia.create();
		AgiAgentChatMessageMediaCreateCommandToAgiAgentChatMessageMediaMapping.instance.fillAgiAgentChatMessageMediaByAgiAgentChatMessageMediaCreateCommand(agiAgentChatMessageMedia, agiAgentChatMessageMediaCreateCommand);
		return agiAgentChatMessageMedia;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentChatMessageMediaCreateCommandToAgiAgentChatMessageMediaMapping{
		AgiAgentChatMessageMediaCreateCommandToAgiAgentChatMessageMediaMapping instance = Mappers.getMapper( AgiAgentChatMessageMediaCreateCommandToAgiAgentChatMessageMediaMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageMedia
		 * @param agiAgentChatMessageMediaCreateCommand
		 */
		void fillAgiAgentChatMessageMediaByAgiAgentChatMessageMediaCreateCommand(@MappingTarget AgiAgentChatMessageMedia agiAgentChatMessageMedia, AgiAgentChatMessageMediaCreateCommand agiAgentChatMessageMediaCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageMediaGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageMediaGateway(AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway) {
		this.agiAgentChatMessageMediaGateway = agiAgentChatMessageMediaGateway;
	}
}
