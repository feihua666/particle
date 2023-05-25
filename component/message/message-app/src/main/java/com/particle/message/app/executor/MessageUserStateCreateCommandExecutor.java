package com.particle.message.app.executor;

import com.particle.message.app.structmapping.MessageUserStateAppStructMapping;
import com.particle.message.client.dto.command.MessageUserStateCreateCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.gateway.MessageUserStateGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 用户消息读取状态 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Component
@Validated
public class MessageUserStateCreateCommandExecutor  extends AbstractBaseExecutor {

	private MessageUserStateGateway messageUserStateGateway;

	/**
	 * 执行用户消息读取状态添加指令
	 * @param messageUserStateCreateCommand
	 * @return
	 */
	public SingleResponse<MessageUserStateVO> execute(@Valid MessageUserStateCreateCommand messageUserStateCreateCommand) {
		MessageUserState messageUserState = createByMessageUserStateCreateCommand(messageUserStateCreateCommand);
		messageUserState.setAddControl(messageUserStateCreateCommand);
		boolean save = messageUserStateGateway.save(messageUserState);
		if (save) {
			return SingleResponse.of(MessageUserStateAppStructMapping.instance.toMessageUserStateVO(messageUserState));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据用户消息读取状态创建指令创建用户消息读取状态模型
	 * @param messageUserStateCreateCommand
	 * @return
	 */
	private MessageUserState createByMessageUserStateCreateCommand(MessageUserStateCreateCommand messageUserStateCreateCommand){
		MessageUserState messageUserState = MessageUserState.create();
		MessageUserStateCreateCommandToMessageUserStateMapping.instance.fillMessageUserStateByMessageUserStateCreateCommand(messageUserState, messageUserStateCreateCommand);
		return messageUserState;
	}

	@Mapper
	interface  MessageUserStateCreateCommandToMessageUserStateMapping{
		MessageUserStateCreateCommandToMessageUserStateMapping instance = Mappers.getMapper( MessageUserStateCreateCommandToMessageUserStateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param messageUserState
		 * @param messageUserStateCreateCommand
		 */
		void fillMessageUserStateByMessageUserStateCreateCommand(@MappingTarget MessageUserState messageUserState, MessageUserStateCreateCommand messageUserStateCreateCommand);
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
