package com.particle.agi.app.chat.executor.representation;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageAppStructMapping;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageVO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessagePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 智能体对话消息 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Component
@Validated
public class AgiAgentChatMessageQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentChatMessageService iAgiAgentChatMessageService;

	/**
	 * 执行 智能体对话消息 列表查询指令
	 * @param agiAgentChatMessageQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentChatMessageVO> execute(@Valid AgiAgentChatMessageQueryListCommand agiAgentChatMessageQueryListCommand) {
		List<AgiAgentChatMessageDO> agiAgentChatMessageDO = iAgiAgentChatMessageService.list(agiAgentChatMessageQueryListCommand);
		List<AgiAgentChatMessageVO> agiAgentChatMessageVOs = AgiAgentChatMessageAppStructMapping.instance.agiAgentChatMessageDOsToAgiAgentChatMessageVOs(agiAgentChatMessageDO);
		return MultiResponse.of(agiAgentChatMessageVOs);
	}
	/**
	 * 执行 智能体对话消息 分页查询指令
	 * @param agiAgentChatMessagePageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageVO> execute(@Valid AgiAgentChatMessagePageQueryCommand agiAgentChatMessagePageQueryCommand) {
		Page<AgiAgentChatMessageDO> page = iAgiAgentChatMessageService.listPage(agiAgentChatMessagePageQueryCommand);
		return AgiAgentChatMessageAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体对话消息 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageVO> executeDetail(IdCommand detailCommand) {
		AgiAgentChatMessageDO byId = iAgiAgentChatMessageService.getById(detailCommand.getId());
		AgiAgentChatMessageVO agiAgentChatMessageVO = AgiAgentChatMessageAppStructMapping.instance.agiAgentChatMessageDOToAgiAgentChatMessageVO(byId);
		return SingleResponse.of(agiAgentChatMessageVO);
	}
	/**
	 * 执行 智能体对话消息 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentChatMessageDO byId = iAgiAgentChatMessageService.getById(detailForUpdateCommand.getId());
		AgiAgentChatMessageVO agiAgentChatMessageVO = AgiAgentChatMessageAppStructMapping.instance.agiAgentChatMessageDOToAgiAgentChatMessageVO(byId);
		return SingleResponse.of(agiAgentChatMessageVO);
	}


	@Autowired
	public void setIAgiAgentChatMessageService(IAgiAgentChatMessageService iAgiAgentChatMessageService) {
		this.iAgiAgentChatMessageService = iAgiAgentChatMessageService;
	}
}
