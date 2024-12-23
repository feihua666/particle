package com.particle.message.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.message.app.structmapping.MessageUserStateAppStructMapping;
import com.particle.message.client.dto.command.representation.MessageUserStatePageQueryCommand;
import com.particle.message.client.dto.command.representation.MessageUserStateQueryListCommand;
import com.particle.message.client.dto.data.MessageUserStateVO;
import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.infrastructure.service.IMessageUserStateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 用户消息读取状态 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Component
@Validated
public class MessageUserStateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IMessageUserStateService iMessageUserStateService;

	/**
	 * 执行 用户消息读取状态 列表查询指令
	 * @param messageUserStateQueryListCommand
	 * @return
	 */
	public MultiResponse<MessageUserStateVO> execute(@Valid MessageUserStateQueryListCommand messageUserStateQueryListCommand) {
		List<MessageUserStateDO> messageUserStateDO = iMessageUserStateService.list(messageUserStateQueryListCommand);
		List<MessageUserStateVO> messageUserStateVOs = MessageUserStateAppStructMapping.instance.messageUserStateDOsToMessageUserStateVOs(messageUserStateDO);
		return MultiResponse.of(messageUserStateVOs);
	}
	/**
	 * 执行 用户消息读取状态 分页查询指令
	 * @param messageUserStatePageQueryCommand
	 * @return
	 */
	public PageResponse<MessageUserStateVO> execute(@Valid MessageUserStatePageQueryCommand messageUserStatePageQueryCommand) {
		Page<MessageUserStateDO> page = iMessageUserStateService.listPage(messageUserStatePageQueryCommand);
		return MessageUserStateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 用户消息读取状态 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<MessageUserStateVO> executeDetail(IdCommand detailCommand) {
		MessageUserStateDO byId = iMessageUserStateService.getById(detailCommand.getId());
		MessageUserStateVO messageUserStateVO = MessageUserStateAppStructMapping.instance.messageUserStateDOToMessageUserStateVO(byId);
		return SingleResponse.of(messageUserStateVO);
	}
	/**
	 * 执行 用户消息读取状态 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<MessageUserStateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		MessageUserStateDO byId = iMessageUserStateService.getById(detailForUpdateCommand.getId());
		MessageUserStateVO messageUserStateVO = MessageUserStateAppStructMapping.instance.messageUserStateDOToMessageUserStateVO(byId);
		return SingleResponse.of(messageUserStateVO);
	}

	@Autowired
	public void setIMessageUserStateService(IMessageUserStateService iMessageUserStateService) {
		this.iMessageUserStateService = iMessageUserStateService;
	}
}
