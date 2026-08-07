package com.particle.workflow.app.execution.executor.representation;

import com.particle.workflow.app.execution.structmapping.WorkflowExecutionAppStructMapping;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionPageQueryCommand;
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
 * 工作流执行实例 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
@Validated
public class WorkflowExecutionQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IWorkflowExecutionService iWorkflowExecutionService;

	/**
	 * 执行 工作流执行实例 列表查询指令
	 * @param workflowExecutionQueryListCommand
	 * @return
	 */
	public MultiResponse<WorkflowExecutionVO> execute(@Valid WorkflowExecutionQueryListCommand workflowExecutionQueryListCommand) {
		List<WorkflowExecutionDO> workflowExecutionDO = iWorkflowExecutionService.list(workflowExecutionQueryListCommand);
		List<WorkflowExecutionVO> workflowExecutionVOs = WorkflowExecutionAppStructMapping.instance.workflowExecutionDOsToWorkflowExecutionVOs(workflowExecutionDO);
		return MultiResponse.of(workflowExecutionVOs);
	}
	/**
	 * 执行 工作流执行实例 分页查询指令
	 * @param workflowExecutionPageQueryCommand
	 * @return
	 */
	public PageResponse<WorkflowExecutionVO> execute(@Valid WorkflowExecutionPageQueryCommand workflowExecutionPageQueryCommand) {
		Page<WorkflowExecutionDO> page = iWorkflowExecutionService.listPage(workflowExecutionPageQueryCommand);
		return WorkflowExecutionAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 工作流执行实例 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionVO> executeDetail(CommonIdCommand detailCommand) {
		WorkflowExecutionDO byId = iWorkflowExecutionService.getById(detailCommand.getId());
		WorkflowExecutionVO workflowExecutionVO = WorkflowExecutionAppStructMapping.instance.workflowExecutionDOToWorkflowExecutionVO(byId);
		return SingleResponse.of(workflowExecutionVO);
	}
	/**
	 * 执行 工作流执行实例 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		WorkflowExecutionDO byId = iWorkflowExecutionService.getById(detailForUpdateCommand.getId());
		WorkflowExecutionVO workflowExecutionVO = WorkflowExecutionAppStructMapping.instance.workflowExecutionDOToWorkflowExecutionVO(byId);
		return SingleResponse.of(workflowExecutionVO);
	}


	@Autowired
	public void setIWorkflowExecutionService(IWorkflowExecutionService iWorkflowExecutionService) {
		this.iWorkflowExecutionService = iWorkflowExecutionService;
	}
}
