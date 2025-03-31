package com.particle.agi.app.chat.executor.representation;

import com.particle.agi.app.chat.structmapping.AgiAgentChatAppStructMapping;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatVO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatPageQueryCommand;
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
 * 智能体对话 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Component
@Validated
public class AgiAgentChatQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentChatService iAgiAgentChatService;

	/**
	 * 执行 智能体对话 列表查询指令
	 * @param agiAgentChatQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentChatVO> execute(@Valid AgiAgentChatQueryListCommand agiAgentChatQueryListCommand) {
		List<AgiAgentChatDO> agiAgentChatDO = iAgiAgentChatService.list(agiAgentChatQueryListCommand);
		List<AgiAgentChatVO> agiAgentChatVOs = AgiAgentChatAppStructMapping.instance.agiAgentChatDOsToAgiAgentChatVOs(agiAgentChatDO);
		return MultiResponse.of(agiAgentChatVOs);
	}
	/**
	 * 执行 智能体对话 分页查询指令
	 * @param agiAgentChatPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentChatVO> execute(@Valid AgiAgentChatPageQueryCommand agiAgentChatPageQueryCommand) {
		Page<AgiAgentChatDO> page = iAgiAgentChatService.listPage(agiAgentChatPageQueryCommand);
		return AgiAgentChatAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体对话 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatVO> executeDetail(IdCommand detailCommand) {
		AgiAgentChatDO byId = iAgiAgentChatService.getById(detailCommand.getId());
		AgiAgentChatVO agiAgentChatVO = AgiAgentChatAppStructMapping.instance.agiAgentChatDOToAgiAgentChatVO(byId);
		return SingleResponse.of(agiAgentChatVO);
	}
	/**
	 * 执行 智能体对话 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentChatDO byId = iAgiAgentChatService.getById(detailForUpdateCommand.getId());
		AgiAgentChatVO agiAgentChatVO = AgiAgentChatAppStructMapping.instance.agiAgentChatDOToAgiAgentChatVO(byId);
		return SingleResponse.of(agiAgentChatVO);
	}


	@Autowired
	public void setIAgiAgentChatService(IAgiAgentChatService iAgiAgentChatService) {
		this.iAgiAgentChatService = iAgiAgentChatService;
	}
}
