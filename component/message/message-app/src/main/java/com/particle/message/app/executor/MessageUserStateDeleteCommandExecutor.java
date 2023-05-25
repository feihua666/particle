package com.particle.message.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.message.app.structmapping.MessageUserStateAppStructMapping;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import com.particle.message.domain.gateway.MessageUserStateGateway;
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
public class MessageUserStateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private MessageUserStateGateway messageUserStateGateway;

	/**
	 * 执行 用户消息读取状态 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<MessageUserStateVO> execute(@Valid IdCommand deleteCommand) {
		MessageUserStateId messageUserStateId = MessageUserStateId.of(deleteCommand.getId());
		MessageUserState byId = messageUserStateGateway.getById(messageUserStateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = messageUserStateGateway.delete(messageUserStateId);
		if (delete) {
			return SingleResponse.of(MessageUserStateAppStructMapping.instance.toMessageUserStateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
