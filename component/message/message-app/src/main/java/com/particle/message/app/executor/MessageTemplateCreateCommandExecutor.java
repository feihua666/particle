package com.particle.message.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.message.app.structmapping.MessageTemplateAppStructMapping;
import com.particle.message.client.dto.command.MessageTemplateCreateCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.gateway.MessageTemplateGateway;
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
 * 消息模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Component
@Validated
public class MessageTemplateCreateCommandExecutor  extends AbstractBaseExecutor {

	private MessageTemplateGateway messageTemplateGateway;

	/**
	 * 执行消息模板添加指令
	 * @param messageTemplateCreateCommand
	 * @return
	 */
	public SingleResponse<MessageTemplateVO> execute(@Valid MessageTemplateCreateCommand messageTemplateCreateCommand) {
		MessageTemplate messageTemplate = createByMessageTemplateCreateCommand(messageTemplateCreateCommand);
		messageTemplate.setAddControl(messageTemplateCreateCommand);
		boolean save = messageTemplateGateway.save(messageTemplate);
		if (save) {
			return SingleResponse.of(MessageTemplateAppStructMapping.instance.toMessageTemplateVO(messageTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据消息模板创建指令创建消息模板模型
	 * @param messageTemplateCreateCommand
	 * @return
	 */
	private MessageTemplate createByMessageTemplateCreateCommand(MessageTemplateCreateCommand messageTemplateCreateCommand){
		MessageTemplate messageTemplate = MessageTemplate.create();
		MessageTemplateCreateCommandToMessageTemplateMapping.instance.fillMessageTemplateByMessageTemplateCreateCommand(messageTemplate, messageTemplateCreateCommand);
		return messageTemplate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  MessageTemplateCreateCommandToMessageTemplateMapping{
		MessageTemplateCreateCommandToMessageTemplateMapping instance = Mappers.getMapper( MessageTemplateCreateCommandToMessageTemplateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param messageTemplate
		 * @param messageTemplateCreateCommand
		 */
		void fillMessageTemplateByMessageTemplateCreateCommand(@MappingTarget MessageTemplate messageTemplate, MessageTemplateCreateCommand messageTemplateCreateCommand);
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
