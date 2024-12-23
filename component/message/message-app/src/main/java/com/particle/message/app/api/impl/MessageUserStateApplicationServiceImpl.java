package com.particle.message.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.executor.MessageUserStateCreateCommandExecutor;
import com.particle.message.app.executor.MessageUserStateDeleteCommandExecutor;
import com.particle.message.app.executor.MessageUserStateUpdateCommandExecutor;
import com.particle.message.client.api.IMessageUserStateApplicationService;
import com.particle.message.client.dto.command.MessageUserStateCreateCommand;
import com.particle.message.client.dto.command.MessageUserStateUpdateCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 用户消息读取状态 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Transactional
@Service
@CatchAndLog
public class MessageUserStateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageUserStateApplicationService {

	private MessageUserStateCreateCommandExecutor messageUserStateCreateCommandExecutor;

	private MessageUserStateDeleteCommandExecutor messageUserStateDeleteCommandExecutor;

	private MessageUserStateUpdateCommandExecutor messageUserStateUpdateCommandExecutor;


	@Override
	public SingleResponse<MessageUserStateVO> create(MessageUserStateCreateCommand messageUserStateCreateCommand) {
		return messageUserStateCreateCommandExecutor.execute(messageUserStateCreateCommand);
	}

	@Override
	public SingleResponse<MessageUserStateVO> delete(IdCommand deleteCommand) {
		return messageUserStateDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<MessageUserStateVO> update(MessageUserStateUpdateCommand messageUserStateUpdateCommand) {
		return messageUserStateUpdateCommandExecutor.execute(messageUserStateUpdateCommand);
	}

	@Autowired
	public void setMessageUserStateCreateCommandExecutor(MessageUserStateCreateCommandExecutor messageUserStateCreateCommandExecutor) {
		this.messageUserStateCreateCommandExecutor = messageUserStateCreateCommandExecutor;
	}

	@Autowired
	public void setMessageUserStateDeleteCommandExecutor(MessageUserStateDeleteCommandExecutor messageUserStateDeleteCommandExecutor) {
		this.messageUserStateDeleteCommandExecutor = messageUserStateDeleteCommandExecutor;
	}
	@Autowired
	public void setMessageUserStateUpdateCommandExecutor(MessageUserStateUpdateCommandExecutor messageUserStateUpdateCommandExecutor) {
		this.messageUserStateUpdateCommandExecutor = messageUserStateUpdateCommandExecutor;
	}

}
