package com.particle.workflow.app.execution.executor.representation;

import com.particle.workflow.app.execution.structmapping.WorkflowExecutionNodeAppStructMapping;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodeQueryListCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.client.execution.dto.command.representation.WorkflowExecutionNodePageQueryCommand;
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
 * 工作流节点执行实例 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
@Validated
public class WorkflowExecutionNodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IWorkflowExecutionNodeService iWorkflowExecutionNodeService;

	/**
	 * 执行 工作流节点执行实例 列表查询指令
	 * @param workflowExecutionNodeQueryListCommand
	 * @return
	 */
	public MultiResponse<WorkflowExecutionNodeVO> execute(@Valid WorkflowExecutionNodeQueryListCommand workflowExecutionNodeQueryListCommand) {
		List<WorkflowExecutionNodeDO> workflowExecutionNodeDO = iWorkflowExecutionNodeService.list(workflowExecutionNodeQueryListCommand);
		List<WorkflowExecutionNodeVO> workflowExecutionNodeVOs = WorkflowExecutionNodeAppStructMapping.instance.workflowExecutionNodeDOsToWorkflowExecutionNodeVOs(workflowExecutionNodeDO);
		return MultiResponse.of(workflowExecutionNodeVOs);
	}
	/**
	 * 执行 工作流节点执行实例 分页查询指令
	 * @param workflowExecutionNodePageQueryCommand
	 * @return
	 */
	public PageResponse<WorkflowExecutionNodeVO> execute(@Valid WorkflowExecutionNodePageQueryCommand workflowExecutionNodePageQueryCommand) {
		Page<WorkflowExecutionNodeDO> page = iWorkflowExecutionNodeService.listPage(workflowExecutionNodePageQueryCommand);
		return WorkflowExecutionNodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 工作流节点执行实例 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionNodeVO> executeDetail(CommonIdCommand detailCommand) {
		WorkflowExecutionNodeDO byId = iWorkflowExecutionNodeService.getById(detailCommand.getId());
		WorkflowExecutionNodeVO workflowExecutionNodeVO = WorkflowExecutionNodeAppStructMapping.instance.workflowExecutionNodeDOToWorkflowExecutionNodeVO(byId);
		return SingleResponse.of(workflowExecutionNodeVO);
	}
	/**
	 * 执行 工作流节点执行实例 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionNodeVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		WorkflowExecutionNodeDO byId = iWorkflowExecutionNodeService.getById(detailForUpdateCommand.getId());
		WorkflowExecutionNodeVO workflowExecutionNodeVO = WorkflowExecutionNodeAppStructMapping.instance.workflowExecutionNodeDOToWorkflowExecutionNodeVO(byId);
		return SingleResponse.of(workflowExecutionNodeVO);
	}


	@Autowired
	public void setIWorkflowExecutionNodeService(IWorkflowExecutionNodeService iWorkflowExecutionNodeService) {
		this.iWorkflowExecutionNodeService = iWorkflowExecutionNodeService;
	}
}
