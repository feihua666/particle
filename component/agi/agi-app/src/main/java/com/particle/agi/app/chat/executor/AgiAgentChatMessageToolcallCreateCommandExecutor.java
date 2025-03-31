package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolcallAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallCreateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.gateway.AgiAgentChatMessageToolcallGateway;
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
 * 智能体对话消息工具调用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
@Validated
public class AgiAgentChatMessageToolcallCreateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway;

	/**
	 * 执行智能体对话消息工具调用添加指令
	 * @param agiAgentChatMessageToolcallCreateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolcallVO> execute(@Valid AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand) {
		AgiAgentChatMessageToolcall agiAgentChatMessageToolcall = createByAgiAgentChatMessageToolcallCreateCommand(agiAgentChatMessageToolcallCreateCommand);
		agiAgentChatMessageToolcall.setAddControl(agiAgentChatMessageToolcallCreateCommand);
		boolean save = agiAgentChatMessageToolcallGateway.save(agiAgentChatMessageToolcall);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageToolcallAppStructMapping.instance.toAgiAgentChatMessageToolcallVO(agiAgentChatMessageToolcall));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息工具调用创建指令创建智能体对话消息工具调用模型
	 * @param agiAgentChatMessageToolcallCreateCommand
	 * @return
	 */
	private AgiAgentChatMessageToolcall createByAgiAgentChatMessageToolcallCreateCommand(AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand){
		AgiAgentChatMessageToolcall agiAgentChatMessageToolcall = AgiAgentChatMessageToolcall.create();
		AgiAgentChatMessageToolcallCreateCommandToAgiAgentChatMessageToolcallMapping.instance.fillAgiAgentChatMessageToolcallByAgiAgentChatMessageToolcallCreateCommand(agiAgentChatMessageToolcall, agiAgentChatMessageToolcallCreateCommand);
		return agiAgentChatMessageToolcall;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  AgiAgentChatMessageToolcallCreateCommandToAgiAgentChatMessageToolcallMapping{
		AgiAgentChatMessageToolcallCreateCommandToAgiAgentChatMessageToolcallMapping instance = Mappers.getMapper( AgiAgentChatMessageToolcallCreateCommandToAgiAgentChatMessageToolcallMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageToolcall
		 * @param agiAgentChatMessageToolcallCreateCommand
		 */
		void fillAgiAgentChatMessageToolcallByAgiAgentChatMessageToolcallCreateCommand(@MappingTarget AgiAgentChatMessageToolcall agiAgentChatMessageToolcall, AgiAgentChatMessageToolcallCreateCommand agiAgentChatMessageToolcallCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiAgentChatMessageToolcallGateway
	 */
	@Autowired
	public void setAgiAgentChatMessageToolcallGateway(AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway) {
		this.agiAgentChatMessageToolcallGateway = agiAgentChatMessageToolcallGateway;
	}
}
