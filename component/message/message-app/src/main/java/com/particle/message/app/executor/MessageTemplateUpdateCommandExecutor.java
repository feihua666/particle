package com.particle.message.app.executor;

import com.particle.message.app.structmapping.MessageTemplateAppStructMapping;
import com.particle.message.client.dto.command.MessageTemplateUpdateCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;
import com.particle.message.domain.gateway.MessageTemplateGateway;
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
 * 消息模板 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class MessageTemplateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private MessageTemplateGateway messageTemplateGateway;

	/**
	 * 执行 消息模板 更新指令
	 * @param messageTemplateUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageTemplateVO> execute(@Valid MessageTemplateUpdateCommand messageTemplateUpdateCommand) {
		MessageTemplate messageTemplate = createByMessageTemplateUpdateCommand(messageTemplateUpdateCommand);
		messageTemplate.setUpdateControl(messageTemplateUpdateCommand);
		boolean save = messageTemplateGateway.save(messageTemplate);
		if (save) {
			return SingleResponse.of(MessageTemplateAppStructMapping.instance.toMessageTemplateVO(messageTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param messageTemplateUpdateCommand
	 * @return
	 */
	private MessageTemplate createByMessageTemplateUpdateCommand(MessageTemplateUpdateCommand messageTemplateUpdateCommand){
		MessageTemplate messageTemplate = MessageTemplate.create();
		MessageTemplateUpdateCommandToMessageTemplateMapping.instance.fillMessageTemplateByMessageTemplateUpdateCommand(messageTemplate, messageTemplateUpdateCommand);
		return messageTemplate;
	}

	@Mapper
	interface MessageTemplateUpdateCommandToMessageTemplateMapping{
		MessageTemplateUpdateCommandToMessageTemplateMapping instance = Mappers.getMapper(MessageTemplateUpdateCommandToMessageTemplateMapping.class );

		default MessageTemplateId map(Long id){
			if (id == null) {
				return null;
			}
			return MessageTemplateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param messageTemplate
		 * @param messageTemplateUpdateCommand
		 */
		void fillMessageTemplateByMessageTemplateUpdateCommand(@MappingTarget MessageTemplate messageTemplate, MessageTemplateUpdateCommand messageTemplateUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param messageTemplateGateway
	 */
	@Autowired
	public void setMessageTemplateGateway(MessageTemplateGateway messageTemplateGateway) {
		this.messageTemplateGateway = messageTemplateGateway;
	}
}
