package com.particle.workflow.app.definition.executor.representation;

import com.particle.workflow.app.definition.structmapping.WorkflowProjectAppStructMapping;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectQueryListCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowProjectService;
import com.particle.workflow.client.definition.dto.command.representation.WorkflowProjectPageQueryCommand;
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
 * 工作流项目 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
@Validated
public class WorkflowProjectQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IWorkflowProjectService iWorkflowProjectService;

	/**
	 * 执行 工作流项目 列表查询指令
	 * @param workflowProjectQueryListCommand
	 * @return
	 */
	public MultiResponse<WorkflowProjectVO> execute(@Valid WorkflowProjectQueryListCommand workflowProjectQueryListCommand) {
		List<WorkflowProjectDO> workflowProjectDO = iWorkflowProjectService.list(workflowProjectQueryListCommand);
		List<WorkflowProjectVO> workflowProjectVOs = WorkflowProjectAppStructMapping.instance.workflowProjectDOsToWorkflowProjectVOs(workflowProjectDO);
		return MultiResponse.of(workflowProjectVOs);
	}
	/**
	 * 执行 工作流项目 分页查询指令
	 * @param workflowProjectPageQueryCommand
	 * @return
	 */
	public PageResponse<WorkflowProjectVO> execute(@Valid WorkflowProjectPageQueryCommand workflowProjectPageQueryCommand) {
		Page<WorkflowProjectDO> page = iWorkflowProjectService.listPage(workflowProjectPageQueryCommand);
		return WorkflowProjectAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 工作流项目 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<WorkflowProjectVO> executeDetail(CommonIdCommand detailCommand) {
		WorkflowProjectDO byId = iWorkflowProjectService.getById(detailCommand.getId());
		WorkflowProjectVO workflowProjectVO = WorkflowProjectAppStructMapping.instance.workflowProjectDOToWorkflowProjectVO(byId);
		return SingleResponse.of(workflowProjectVO);
	}
	/**
	 * 执行 工作流项目 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowProjectVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		WorkflowProjectDO byId = iWorkflowProjectService.getById(detailForUpdateCommand.getId());
		WorkflowProjectVO workflowProjectVO = WorkflowProjectAppStructMapping.instance.workflowProjectDOToWorkflowProjectVO(byId);
		return SingleResponse.of(workflowProjectVO);
	}


	@Autowired
	public void setIWorkflowProjectService(IWorkflowProjectService iWorkflowProjectService) {
		this.iWorkflowProjectService = iWorkflowProjectService;
	}
}
