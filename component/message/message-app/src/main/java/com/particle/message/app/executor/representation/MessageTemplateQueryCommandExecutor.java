package com.particle.message.app.executor.representation;

import com.particle.message.app.structmapping.MessageTemplateAppStructMapping;
import com.particle.message.client.dto.command.representation.MessageTemplateQueryListCommand;
import com.particle.message.client.dto.data.MessageTemplateVO;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.message.infrastructure.service.IMessageTemplateService;
import com.particle.message.client.dto.command.representation.MessageTemplatePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 消息模板 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Component
@Validated
public class MessageTemplateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IMessageTemplateService iMessageTemplateService;

	/**
	 * 执行 消息模板 列表查询指令
	 * @param messageTemplateQueryListCommand
	 * @return
	 */
	public MultiResponse<MessageTemplateVO> execute(@Valid MessageTemplateQueryListCommand messageTemplateQueryListCommand) {
		List<MessageTemplateDO> messageTemplateDO = iMessageTemplateService.list(messageTemplateQueryListCommand);
		List<MessageTemplateVO> messageTemplateVOs = MessageTemplateAppStructMapping.instance.messageTemplateDOsToMessageTemplateVOs(messageTemplateDO);
		return MultiResponse.of(messageTemplateVOs);
	}
	/**
	 * 执行 消息模板 分页查询指令
	 * @param messageTemplatePageQueryCommand
	 * @return
	 */
	public PageResponse<MessageTemplateVO> execute(@Valid MessageTemplatePageQueryCommand messageTemplatePageQueryCommand) {
		Page<MessageTemplateDO> page = iMessageTemplateService.listPage(messageTemplatePageQueryCommand);
		return MessageTemplateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 消息模板 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<MessageTemplateVO> executeDetail(IdCommand detailCommand) {
		MessageTemplateDO byId = iMessageTemplateService.getById(detailCommand.getId());
		MessageTemplateVO messageTemplateVO = MessageTemplateAppStructMapping.instance.messageTemplateDOToMessageTemplateVO(byId);
		return SingleResponse.of(messageTemplateVO);
	}
	/**
	 * 执行 消息模板 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageTemplateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		MessageTemplateDO byId = iMessageTemplateService.getById(detailForUpdateCommand.getId());
		MessageTemplateVO messageTemplateVO = MessageTemplateAppStructMapping.instance.messageTemplateDOToMessageTemplateVO(byId);
		return SingleResponse.of(messageTemplateVO);
	}

	@Autowired
	public void setIMessageTemplateService(IMessageTemplateService iMessageTemplateService) {
		this.iMessageTemplateService = iMessageTemplateService;
	}
}
