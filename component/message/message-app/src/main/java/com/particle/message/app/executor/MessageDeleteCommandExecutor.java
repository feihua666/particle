package com.particle.message.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.message.app.structmapping.MessageAppStructMapping;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;
import com.particle.message.domain.gateway.MessageGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class MessageDeleteCommandExecutor  extends AbstractBaseExecutor {

	private MessageGateway messageGateway;

	/**
	 * 执行 消息 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<MessageVO> execute(@Valid IdCommand deleteCommand) {
		MessageId messageId = MessageId.of(deleteCommand.getId());
		Message byId = messageGateway.getById(messageId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = messageGateway.delete(messageId,deleteCommand);
		if (delete) {
			return SingleResponse.of(MessageAppStructMapping.instance.toMessageVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
