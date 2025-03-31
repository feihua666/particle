package com.particle.agi.app.agent.executor.representation;

import com.particle.agi.app.agent.structmapping.AgiAgentAppStructMapping;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentQueryListCommand;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.agi.infrastructure.agent.service.IAgiAgentService;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentPageQueryCommand;
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
 * 智能体 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Component
@Validated
public class AgiAgentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAgentService iAgiAgentService;

	/**
	 * 执行 智能体 列表查询指令
	 * @param agiAgentQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAgentVO> execute(@Valid AgiAgentQueryListCommand agiAgentQueryListCommand) {
		List<AgiAgentDO> agiAgentDO = iAgiAgentService.list(agiAgentQueryListCommand);
		List<AgiAgentVO> agiAgentVOs = AgiAgentAppStructMapping.instance.agiAgentDOsToAgiAgentVOs(agiAgentDO);
		return MultiResponse.of(agiAgentVOs);
	}
	/**
	 * 执行 智能体 分页查询指令
	 * @param agiAgentPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAgentVO> execute(@Valid AgiAgentPageQueryCommand agiAgentPageQueryCommand) {
		Page<AgiAgentDO> page = iAgiAgentService.listPage(agiAgentPageQueryCommand);
		return AgiAgentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 智能体 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAgentVO> executeDetail(IdCommand detailCommand) {
		AgiAgentDO byId = iAgiAgentService.getById(detailCommand.getId());
		AgiAgentVO agiAgentVO = AgiAgentAppStructMapping.instance.agiAgentDOToAgiAgentVO(byId);
		return SingleResponse.of(agiAgentVO);
	}
	/**
	 * 执行 智能体 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAgentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiAgentDO byId = iAgiAgentService.getById(detailForUpdateCommand.getId());
		AgiAgentVO agiAgentVO = AgiAgentAppStructMapping.instance.agiAgentDOToAgiAgentVO(byId);
		return SingleResponse.of(agiAgentVO);
	}


	@Autowired
	public void setIAgiAgentService(IAgiAgentService iAgiAgentService) {
		this.iAgiAgentService = iAgiAgentService;
	}
}
