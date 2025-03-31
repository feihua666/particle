package com.particle.agi.app.chat.executor;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolcallAppStructMapping;
import com.particle.agi.client.chat.dto.command.AgiAgentChatMessageToolcallUpdateCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
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
 * 智能体对话消息工具调用 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiAgentChatMessageToolcallUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiAgentChatMessageToolcallGateway agiAgentChatMessageToolcallGateway;

	/**
	 * 执行 智能体对话消息工具调用 更新指令
	 * @param agiAgentChatMessageToolcallUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolcallVO> execute(@Valid AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand) {
		AgiAgentChatMessageToolcall agiAgentChatMessageToolcall = createByAgiAgentChatMessageToolcallUpdateCommand(agiAgentChatMessageToolcallUpdateCommand);
		agiAgentChatMessageToolcall.setUpdateControl(agiAgentChatMessageToolcallUpdateCommand);
		boolean save = agiAgentChatMessageToolcallGateway.save(agiAgentChatMessageToolcall);
		if (save) {
			return SingleResponse.of(AgiAgentChatMessageToolcallAppStructMapping.instance.toAgiAgentChatMessageToolcallVO(agiAgentChatMessageToolcall));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据智能体对话消息工具调用更新指令创建智能体对话消息工具调用模型
	 * @param agiAgentChatMessageToolcallUpdateCommand
	 * @return
	 */
	private AgiAgentChatMessageToolcall createByAgiAgentChatMessageToolcallUpdateCommand(AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand){
		AgiAgentChatMessageToolcall agiAgentChatMessageToolcall = AgiAgentChatMessageToolcall.create();
		AgiAgentChatMessageToolcallUpdateCommandToAgiAgentChatMessageToolcallMapping.instance.fillAgiAgentChatMessageToolcallByAgiAgentChatMessageToolcallUpdateCommand(agiAgentChatMessageToolcall, agiAgentChatMessageToolcallUpdateCommand);
		return agiAgentChatMessageToolcall;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiAgentChatMessageToolcallUpdateCommandToAgiAgentChatMessageToolcallMapping{
		AgiAgentChatMessageToolcallUpdateCommandToAgiAgentChatMessageToolcallMapping instance = Mappers.getMapper(AgiAgentChatMessageToolcallUpdateCommandToAgiAgentChatMessageToolcallMapping.class );

		default AgiAgentChatMessageToolcallId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiAgentChatMessageToolcallId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiAgentChatMessageToolcall
		 * @param agiAgentChatMessageToolcallUpdateCommand
		 */
		void fillAgiAgentChatMessageToolcallByAgiAgentChatMessageToolcallUpdateCommand(@MappingTarget AgiAgentChatMessageToolcall agiAgentChatMessageToolcall, AgiAgentChatMessageToolcallUpdateCommand agiAgentChatMessageToolcallUpdateCommand);
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
