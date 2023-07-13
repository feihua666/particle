package com.particle.message.app.executor;

import com.particle.message.app.structmapping.MessageAppStructMapping;
import com.particle.message.client.dto.command.MessageUpdateCommand;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;
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
 * 消息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class MessageUpdateCommandExecutor  extends AbstractBaseExecutor {

	private MessageGateway messageGateway;

	/**
	 * 执行 消息 更新指令
	 * @param messageUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageVO> execute(@Valid MessageUpdateCommand messageUpdateCommand) {
		Message message = createByMessageUpdateCommand(messageUpdateCommand);
		message.caculateShortContent();
		message.setUpdateControl(messageUpdateCommand);
		boolean save = messageGateway.save(message);
		if (save) {
			return SingleResponse.of(MessageAppStructMapping.instance.toMessageVO(message));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据消息更新指令创建消息模型
	 * @param messageUpdateCommand
	 * @return
	 */
	private Message createByMessageUpdateCommand(MessageUpdateCommand messageUpdateCommand){
		Message message = Message.create();
		MessageUpdateCommandToMessageMapping.instance.fillMessageByMessageUpdateCommand(message, messageUpdateCommand);
		return message;
	}

	@Mapper
	interface MessageUpdateCommandToMessageMapping{
		MessageUpdateCommandToMessageMapping instance = Mappers.getMapper(MessageUpdateCommandToMessageMapping.class );

		default MessageId map(Long id){
			if (id == null) {
				return null;
			}
			return MessageId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param message
		 * @param messageUpdateCommand
		 */
		void fillMessageByMessageUpdateCommand(@MappingTarget Message message, MessageUpdateCommand messageUpdateCommand);
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
