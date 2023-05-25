package com.particle.message.app.api.impl;

import com.particle.message.app.executor.MessageCreateCommandExecutor;
import com.particle.message.app.executor.MessageDeleteCommandExecutor;
import com.particle.message.app.executor.MessageUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.message.client.dto.command.MessageUpdateCommand;
import com.particle.message.client.api.IMessageApplicationService;
import com.particle.message.client.dto.command.MessageCreateCommand;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 消息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Transactional
@Service
@CatchAndLog
public class MessageApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageApplicationService {

	private MessageCreateCommandExecutor messageCreateCommandExecutor;

	private MessageDeleteCommandExecutor messageDeleteCommandExecutor;

	private MessageUpdateCommandExecutor messageUpdateCommandExecutor;


	@Override
	public SingleResponse<MessageVO> create(MessageCreateCommand messageCreateCommand) {
		return messageCreateCommandExecutor.execute(messageCreateCommand);
	}

	@Override
	public SingleResponse<MessageVO> delete(IdCommand deleteCommand) {
		return messageDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<MessageVO> update(MessageUpdateCommand messageUpdateCommand) {
		return messageUpdateCommandExecutor.execute(messageUpdateCommand);
	}

	@Autowired
	public void setMessageCreateCommandExecutor(MessageCreateCommandExecutor messageCreateCommandExecutor) {
		this.messageCreateCommandExecutor = messageCreateCommandExecutor;
	}

	@Autowired
	public void setMessageDeleteCommandExecutor(MessageDeleteCommandExecutor messageDeleteCommandExecutor) {
		this.messageDeleteCommandExecutor = messageDeleteCommandExecutor;
	}
	@Autowired
	public void setMessageUpdateCommandExecutor(MessageUpdateCommandExecutor messageUpdateCommandExecutor) {
		this.messageUpdateCommandExecutor = messageUpdateCommandExecutor;
	}

}
