package com.particle.workflow.app.definition.executor.representation;

import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionHistoryAppStructMapping;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowDefinitionHistoryPageQueryCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
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
 * 工作流定义历史 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
@Validated
public class WorkflowDefinitionHistoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;

	/**
	 * 执行 工作流定义历史 列表查询指令
	 * @param workflowDefinitionHistoryQueryListCommand
	 * @return
	 */
	public MultiResponse<WorkflowDefinitionHistoryVO> execute(@Valid WorkflowDefinitionHistoryQueryListCommand workflowDefinitionHistoryQueryListCommand) {
		List<WorkflowDefinitionHistoryDO> workflowDefinitionHistoryDO = iWorkflowDefinitionHistoryService.list(workflowDefinitionHistoryQueryListCommand);
		List<WorkflowDefinitionHistoryVO> workflowDefinitionHistoryVOs = WorkflowDefinitionHistoryAppStructMapping.instance.workflowDefinitionHistoryDOsToWorkflowDefinitionHistoryVOs(workflowDefinitionHistoryDO);
		return MultiResponse.of(workflowDefinitionHistoryVOs);
	}
	/**
	 * 执行 工作流定义历史 分页查询指令
	 * @param workflowDefinitionHistoryPageQueryCommand
	 * @return
	 */
	public PageResponse<WorkflowDefinitionHistoryVO> execute(@Valid WorkflowDefinitionHistoryPageQueryCommand workflowDefinitionHistoryPageQueryCommand) {
		Page<WorkflowDefinitionHistoryDO> page = iWorkflowDefinitionHistoryService.listPage(workflowDefinitionHistoryPageQueryCommand);
		return WorkflowDefinitionHistoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 工作流定义历史 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> executeDetail(CommonIdCommand detailCommand) {
		WorkflowDefinitionHistoryDO byId = iWorkflowDefinitionHistoryService.getById(detailCommand.getId());
		WorkflowDefinitionHistoryVO workflowDefinitionHistoryVO = WorkflowDefinitionHistoryAppStructMapping.instance.workflowDefinitionHistoryDOToWorkflowDefinitionHistoryVO(byId);
		return SingleResponse.of(workflowDefinitionHistoryVO);
	}
	/**
	 * 执行 工作流定义历史 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		WorkflowDefinitionHistoryDO byId = iWorkflowDefinitionHistoryService.getById(detailForUpdateCommand.getId());
		WorkflowDefinitionHistoryVO workflowDefinitionHistoryVO = WorkflowDefinitionHistoryAppStructMapping.instance.workflowDefinitionHistoryDOToWorkflowDefinitionHistoryVO(byId);
		return SingleResponse.of(workflowDefinitionHistoryVO);
	}


	@Autowired
	public void setIWorkflowDefinitionHistoryService(IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService) {
		this.iWorkflowDefinitionHistoryService = iWorkflowDefinitionHistoryService;
	}
}
