package com.particle.message.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.message.app.structmapping.MessageTemplateAppStructMapping;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;
import com.particle.message.domain.gateway.MessageTemplateGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
public class MessageTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private MessageTemplateGateway messageTemplateGateway;

	/**
	 * 执行 消息模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<MessageTemplateVO> execute(@Valid IdCommand deleteCommand) {
		MessageTemplateId messageTemplateId = MessageTemplateId.of(deleteCommand.getId());
		MessageTemplate byId = messageTemplateGateway.getById(messageTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = messageTemplateGateway.delete(messageTemplateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(MessageTemplateAppStructMapping.instance.toMessageTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
