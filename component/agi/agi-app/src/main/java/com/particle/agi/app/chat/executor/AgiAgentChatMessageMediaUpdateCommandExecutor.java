package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageMediaAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageMediaUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.AgiAgentChatMessageMediaId;
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
 * 智能体对话消息媒体 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentChatMessageMediaUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageMediaGateway agiAgentChatMessageMediaGateway;

	/**
	 * 执行 智能体对话消息媒体 更新指令
	 * @param agiAgentChatMessageMediaUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageMediaVO> execute(@Valid AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand) {
		AgiAgentChatMessageMedia agiAgentChatMessageMedia = createByAgiAgentChatMessageMediaUpdateCommand(agiAgentChatMessageMediaUpdateCommand);
		agiAgentChatMessageMedia.setUpdateControl(agiAgentChatMessageMediaUpdateCommand);
		boolean save = agiAgentChatMessageMediaGateway.save(agiAgentChatMessageMedia);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageMediaAppStructMapping.instance.toAgiAgentChatMessageMediaVO(agiAgentChatMessageMedia));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息媒体更新指令创建智能体对话消息媒体模型
	 * @param agiAgentChatMessageMediaUpdateCommand
	 * @return
	 */
	private AgiAgentChatMessageMedia createByAgiAgentChatMessageMediaUpdateCommand(AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand){
		AgiAgentChatMessageMedia agiAgentChatMessageMedia = AgiAgentChatMessageMedia.create();
		AgiAgentChatMessageMediaUpdateCommandToAgiAgentChatMessageMediaMapping.instance.fillAgiAgentChatMessageMediaByAgiAgentChatMessageMediaUpdateCommand(agiAgentChatMessageMedia, agiAgentChatMessageMediaUpdateCommand);
		return agiAgentChatMessageMedia;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentChatMessageMediaUpdateCommandToAgiAgentChatMessageMediaMapping{
		AgiAgentChatMessageMediaUpdateCommandToAgiAgentChatMessageMediaMapping instance = Mappers.getMapper(AgiAgentChatMessageMediaUpdateCommandToAgiAgentChatMessageMediaMapping.class );

		default AgiAgentChatMessageMediaId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentChatMessageMediaId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageMedia
		 * @param agiAgentChatMessageMediaUpdateCommand
		 */
		void fillAgiAgentChatMessageMediaByAgiAgentChatMessageMediaUpdateCommand(@MappingTarget AgiAgentChatMessageMedia agiAgentChatMessageMedia, AgiAgentChatMessageMediaUpdateCommand agiAgentChatMessageMediaUpdateCommand);
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
