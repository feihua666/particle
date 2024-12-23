package com.particle.message.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.executor.MessageTemplateCreateCommandExecutor;
import com.particle.message.app.executor.MessageTemplateDeleteCommandExecutor;
import com.particle.message.app.executor.MessageTemplateUpdateCommandExecutor;
import com.particle.message.client.api.IMessageTemplateApplicationService;
import com.particle.message.client.dto.command.MessageTemplateCreateCommand;
import com.particle.message.client.dto.command.MessageTemplateUpdateCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 消息模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Transactional
@Service
@CatchAndLog
public class MessageTemplateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IMessageTemplateApplicationService {

	private MessageTemplateCreateCommandExecutor messageTemplateCreateCommandExecutor;

	private MessageTemplateDeleteCommandExecutor messageTemplateDeleteCommandExecutor;

	private MessageTemplateUpdateCommandExecutor messageTemplateUpdateCommandExecutor;


	@Override
	public SingleResponse<MessageTemplateVO> create(MessageTemplateCreateCommand messageTemplateCreateCommand) {
		return messageTemplateCreateCommandExecutor.execute(messageTemplateCreateCommand);
	}

	@Override
	public SingleResponse<MessageTemplateVO> delete(IdCommand deleteCommand) {
		return messageTemplateDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<MessageTemplateVO> update(MessageTemplateUpdateCommand messageTemplateUpdateCommand) {
		return messageTemplateUpdateCommandExecutor.execute(messageTemplateUpdateCommand);
	}

	@Autowired
	public void setMessageTemplateCreateCommandExecutor(MessageTemplateCreateCommandExecutor messageTemplateCreateCommandExecutor) {
		this.messageTemplateCreateCommandExecutor = messageTemplateCreateCommandExecutor;
	}

	@Autowired
	public void setMessageTemplateDeleteCommandExecutor(MessageTemplateDeleteCommandExecutor messageTemplateDeleteCommandExecutor) {
		this.messageTemplateDeleteCommandExecutor = messageTemplateDeleteCommandExecutor;
	}
	@Autowired
	public void setMessageTemplateUpdateCommandExecutor(MessageTemplateUpdateCommandExecutor messageTemplateUpdateCommandExecutor) {
		this.messageTemplateUpdateCommandExecutor = messageTemplateUpdateCommandExecutor;
	}

}
