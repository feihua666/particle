package com.particle.message.app.executor;

import com.particle.message.app.structmapping.MessageAppStructMapping;
import com.particle.message.client.dto.command.MessageCreateCommand;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.message.domain.Message;
import com.particle.message.domain.gateway.MessageGateway;
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
 * 消息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Component
@Validated
public class MessageCreateCommandExecutor  extends AbstractBaseExecutor {

	private MessageGateway messageGateway;

	/**
	 * 执行消息添加指令
	 * @param messageCreateCommand
	 * @return
	 */
	public SingleResponse<MessageVO> execute(@Valid MessageCreateCommand messageCreateCommand) {
		Message message = createByMessageCreateCommand(messageCreateCommand);
		message.changeSendStatusToNotSend();
		message.caculateShortContent();
		message.setAddControl(messageCreateCommand);
		boolean save = messageGateway.save(message);
		if (save) {
			return SingleResponse.of(MessageAppStructMapping.instance.toMessageVO(message));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据消息创建指令创建消息模型
	 * @param messageCreateCommand
	 * @return
	 */
	private Message createByMessageCreateCommand(MessageCreateCommand messageCreateCommand){
		Message message = Message.create();
		MessageCreateCommandToMessageMapping.instance.fillMessageByMessageCreateCommand(message, messageCreateCommand);
		return message;
	}

	@Mapper
	interface  MessageCreateCommandToMessageMapping{
		MessageCreateCommandToMessageMapping instance = Mappers.getMapper( MessageCreateCommandToMessageMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param message
		 * @param messageCreateCommand
		 */
		void fillMessageByMessageCreateCommand(@MappingTarget Message message, MessageCreateCommand messageCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param messageGateway
	 */
	@Autowired
	public void setMessageGateway(MessageGateway messageGateway) {
		this.messageGateway = messageGateway;
	}
}
