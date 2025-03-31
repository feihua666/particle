package com.particle.agi.app.chat.executor.representation;

import com.particle.agi.app.chat.structmapping.AgiAgentChatMessageMediaAppStructMapping;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaQueryListCommand;
import com.particle.agi.client.chat.dto.data.AgiAgentChatMessageMediaVO;
import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.infrastructure.chat.service.IAgiAgentChatMessageMediaService;
import com.particle.agi.client.chat.dto.command.representation.AgiAgentChatMessageMediaPageQueryCommand;
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
 * 智能体对话消息媒体 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Component
@Validated
public class AgiAgentChatMessageMediaQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService;

	/**
	 * 执行 智能体对话消息媒体 列表查询指令
	 * @param agiAgentChatMessageMediaQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentChatMessageMediaVO> execute(@Valid AgiAgentChatMessageMediaQueryListCommand agiAgentChatMessageMediaQueryListCommand) {
		List<AgiAgentChatMessageMediaDO> agiAgentChatMessageMediaDO = iAgiAgentChatMessageMediaService.list(agiAgentChatMessageMediaQueryListCommand);
		List<AgiAgentChatMessageMediaVO> agiAgentChatMessageMediaVOs = AgiAgentChatMessageMediaAppStructMapping.instance.agiAgentChatMessageMediaDOsToAgiAgentChatMessageMediaVOs(agiAgentChatMessageMediaDO);
		return MultiResponse.of(agiAgentChatMessageMediaVOs);
	}
	/**
	 * 执行 智能体对话消息媒体 分页查询指令
	 * @param agiAgentChatMessageMediaPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentChatMessageMediaVO> execute(@Valid AgiAgentChatMessageMediaPageQueryCommand agiAgentChatMessageMediaPageQueryCommand) {
		Page<AgiAgentChatMessageMediaDO> page = iAgiAgentChatMessageMediaService.listPage(agiAgentChatMessageMediaPageQueryCommand);
		return AgiAgentChatMessageMediaAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体对话消息媒体 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageMediaVO> executeDetail(IdCommand detailCommand) {
		AgiAgentChatMessageMediaDO byId = iAgiAgentChatMessageMediaService.getById(detailCommand.getId());
		AgiAgentChatMessageMediaVO agiAgentChatMessageMediaVO = AgiAgentChatMessageMediaAppStructMapping.instance.agiAgentChatMessageMediaDOToAgiAgentChatMessageMediaVO(byId);
		return SingleResponse.of(agiAgentChatMessageMediaVO);
	}
	/**
	 * 执行 智能体对话消息媒体 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentChatMessageMediaVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentChatMessageMediaDO byId = iAgiAgentChatMessageMediaService.getById(detailForUpdateCommand.getId());
		AgiAgentChatMessageMediaVO agiAgentChatMessageMediaVO = AgiAgentChatMessageMediaAppStructMapping.instance.agiAgentChatMessageMediaDOToAgiAgentChatMessageMediaVO(byId);
		return SingleResponse.of(agiAgentChatMessageMediaVO);
	}


	@Autowired
	public void setIAgiAgentChatMessageMediaService(IAgiAgentChatMessageMediaService iAgiAgentChatMessageMediaService) {
		this.iAgiAgentChatMessageMediaService = iAgiAgentChatMessageMediaService;
	}
}
