package com.particle.agi.app.chat.executor.representation;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolAppStructMapping;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolVO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolPageQueryCommand;
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
 * 智能体对话消息工具 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Component
@Validated
public class AgiAgentChatMessageToolQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService;

	/**
	 * 执行 智能体对话消息工具 列表查询指令
	 * @param agiAgentChatMessageToolQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentChatMessageToolVO> execute(@Valid AgiAgentChatMessageToolQueryListCommand agiAgentChatMessageToolQueryListCommand) {
		List<AgiAgentChatMessageToolDO> agiAgentChatMessageToolDO = iAgiAgentChatMessageToolService.list(agiAgentChatMessageToolQueryListCommand);
		List<AgiAgentChatMessageToolVO> agiAgentChatMessageToolVOs = AgiAgentChatMessageToolAppStructMapping.instance.agiAgentChatMessageToolDOsToAgiAgentChatMessageToolVOs(agiAgentChatMessageToolDO);
		return MultiResponse.of(agiAgentChatMessageToolVOs);
	}
	/**
	 * 执行 智能体对话消息工具 分页查询指令
	 * @param agiAgentChatMessageToolPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageToolVO> execute(@Valid AgiAgentChatMessageToolPageQueryCommand agiAgentChatMessageToolPageQueryCommand) {
		Page<AgiAgentChatMessageToolDO> page = iAgiAgentChatMessageToolService.listPage(agiAgentChatMessageToolPageQueryCommand);
		return AgiAgentChatMessageToolAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体对话消息工具 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolVO> executeDetail(IdCommand detailCommand) {
		AgiAgentChatMessageToolDO byId = iAgiAgentChatMessageToolService.getById(detailCommand.getId());
		AgiAgentChatMessageToolVO agiAgentChatMessageToolVO = AgiAgentChatMessageToolAppStructMapping.instance.agiAgentChatMessageToolDOToAgiAgentChatMessageToolVO(byId);
		return SingleResponse.of(agiAgentChatMessageToolVO);
	}
	/**
	 * 执行 智能体对话消息工具 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentChatMessageToolDO byId = iAgiAgentChatMessageToolService.getById(detailForUpdateCommand.getId());
		AgiAgentChatMessageToolVO agiAgentChatMessageToolVO = AgiAgentChatMessageToolAppStructMapping.instance.agiAgentChatMessageToolDOToAgiAgentChatMessageToolVO(byId);
		return SingleResponse.of(agiAgentChatMessageToolVO);
	}


	@Autowired
	public void setIAgiAgentChatMessageToolService(IAgiAgentChatMessageToolService iAgiAgentChatMessageToolService) {
		this.iAgiAgentChatMessageToolService = iAgiAgentChatMessageToolService;
	}
}
