package com.particle.message.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.structmapping.MessageAppStructMapping;
import com.particle.message.client.dto.command.representation.MessagePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageQueryListCommand;
import com.particle.message.client.dto.data.MessageVO;
import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.infrastructure.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 消息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Component
@Validated
public class MessageQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IMessageService iMessageService;

	/**
	 * 执行 消息 列表查询指令
	 * @param messageQueryListCommand
	 * @return
	 */
	public MultiResponse<MessageVO> execute(@Valid MessageQueryListCommand messageQueryListCommand) {
		List<MessageDO> messageDO = iMessageService.list(messageQueryListCommand);
		List<MessageVO> messageVOs = MessageAppStructMapping.instance.messageDOsToMessageVOs(messageDO);
		return MultiResponse.of(messageVOs);
	}
	/**
	 * 执行 消息 分页查询指令
	 * @param messagePageQueryCommand
	 * @return
	 */
	public PageResponse<MessageVO> execute(@Valid MessagePageQueryCommand messagePageQueryCommand) {
		Page<MessageDO> page = iMessageService.listPage(messagePageQueryCommand);
		return MessageAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 消息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<MessageVO> executeDetail(IdCommand detailCommand) {
		MessageDO byId = iMessageService.getById(detailCommand.getId());
		MessageVO messageVO = MessageAppStructMapping.instance.messageDOToMessageVO(byId);
		return SingleResponse.of(messageVO);
	}
	/**
	 * 执行 消息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		MessageDO byId = iMessageService.getById(detailForUpdateCommand.getId());
		MessageVO messageVO = MessageAppStructMapping.instance.messageDOToMessageVO(byId);
		return SingleResponse.of(messageVO);
	}

	@Autowired
	public void setIMessageService(IMessageService iMessageService) {
		this.iMessageService = iMessageService;
	}
}
