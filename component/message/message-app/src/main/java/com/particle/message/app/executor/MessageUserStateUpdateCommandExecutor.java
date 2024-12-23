package com.particle.message.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.message.app.structmapping.MessageUserStateAppStructMapping;
import com.particle.message.client.dto.command.MessageUserStateUpdateCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import com.particle.message.domain.gateway.MessageUserStateGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户消息读取状态 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class MessageUserStateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private MessageUserStateGateway messageUserStateGateway;

	/**
	 * 执行 用户消息读取状态 更新指令
	 * @param messageUserStateUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageUserStateVO> execute(@Valid MessageUserStateUpdateCommand messageUserStateUpdateCommand) {
		MessageUserState messageUserState = createByMessageUserStateUpdateCommand(messageUserStateUpdateCommand);
		messageUserState.setUpdateControl(messageUserStateUpdateCommand);
		boolean save = messageUserStateGateway.save(messageUserState);
		if (save) {
			return SingleResponse.of(MessageUserStateAppStructMapping.instance.toMessageUserStateVO(messageUserState));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户消息读取状态更新指令创建用户消息读取状态模型
	 * @param messageUserStateUpdateCommand
	 * @return
	 */
	private MessageUserState createByMessageUserStateUpdateCommand(MessageUserStateUpdateCommand messageUserStateUpdateCommand){
		MessageUserState messageUserState = MessageUserState.create();
		MessageUserStateUpdateCommandToMessageUserStateMapping.instance.fillMessageUserStateByMessageUserStateUpdateCommand(messageUserState, messageUserStateUpdateCommand);
		return messageUserState;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface MessageUserStateUpdateCommandToMessageUserStateMapping{
		MessageUserStateUpdateCommandToMessageUserStateMapping instance = Mappers.getMapper(MessageUserStateUpdateCommandToMessageUserStateMapping.class );

		default MessageUserStateId map(Long id){
			if (id == null) {
				return null;
			}
			return MessageUserStateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param messageUserState
		 * @param messageUserStateUpdateCommand
		 */
		void fillMessageUserStateByMessageUserStateUpdateCommand(@MappingTarget MessageUserState messageUserState, MessageUserStateUpdateCommand messageUserStateUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param messageUserStateGateway
	 */
	@Autowired
	public void setMessageUserStateGateway(MessageUserStateGateway messageUserStateGateway) {
		this.messageUserStateGateway = messageUserStateGateway;
	}
}
