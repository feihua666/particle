package com.particle.agi.app.chat.executor.representation;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageToolcallAppStructMapping;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageToolcallVO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageToolcallService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageToolcallPageQueryCommand;
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
 * 智能体对话消息工具调用 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Component
@Validated
public class AgiAgentChatMessageToolcallQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService;

	/**
	 * 执行 智能体对话消息工具调用 列表查询指令
	 * @param agiAgentChatMessageToolcallQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentChatMessageToolcallVO> execute(@Valid AgiAgentChatMessageToolcallQueryListCommand agiAgentChatMessageToolcallQueryListCommand) {
		List<AgiAgentChatMessageToolcallDO> agiAgentChatMessageToolcallDO = iAgiAgentChatMessageToolcallService.list(agiAgentChatMessageToolcallQueryListCommand);
		List<AgiAgentChatMessageToolcallVO> agiAgentChatMessageToolcallVOs = AgiAgentChatMessageToolcallAppStructMapping.instance.agiAgentChatMessageToolcallDOsToAgiAgentChatMessageToolcallVOs(agiAgentChatMessageToolcallDO);
		return MultiResponse.of(agiAgentChatMessageToolcallVOs);
	}
	/**
	 * 执行 智能体对话消息工具调用 分页查询指令
	 * @param agiAgentChatMessageToolcallPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageToolcallVO> execute(@Valid AgiAgentChatMessageToolcallPageQueryCommand agiAgentChatMessageToolcallPageQueryCommand) {
		Page<AgiAgentChatMessageToolcallDO> page = iAgiAgentChatMessageToolcallService.listPage(agiAgentChatMessageToolcallPageQueryCommand);
		return AgiAgentChatMessageToolcallAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体对话消息工具调用 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolcallVO> executeDetail(IdCommand detailCommand) {
		AgiAgentChatMessageToolcallDO byId = iAgiAgentChatMessageToolcallService.getById(detailCommand.getId());
		AgiAgentChatMessageToolcallVO agiAgentChatMessageToolcallVO = AgiAgentChatMessageToolcallAppStructMapping.instance.agiAgentChatMessageToolcallDOToAgiAgentChatMessageToolcallVO(byId);
		return SingleResponse.of(agiAgentChatMessageToolcallVO);
	}
	/**
	 * 执行 智能体对话消息工具调用 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageToolcallVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentChatMessageToolcallDO byId = iAgiAgentChatMessageToolcallService.getById(detailForUpdateCommand.getId());
		AgiAgentChatMessageToolcallVO agiAgentChatMessageToolcallVO = AgiAgentChatMessageToolcallAppStructMapping.instance.agiAgentChatMessageToolcallDOToAgiAgentChatMessageToolcallVO(byId);
		return SingleResponse.of(agiAgentChatMessageToolcallVO);
	}


	@Autowired
	public void setIAgiAgentChatMessageToolcallService(IAgiAgentChatMessageToolcallService iAgiAgentChatMessageToolcallService) {
		this.iAgiAgentChatMessageToolcallService = iAgiAgentChatMessageToolcallService;
	}
}
