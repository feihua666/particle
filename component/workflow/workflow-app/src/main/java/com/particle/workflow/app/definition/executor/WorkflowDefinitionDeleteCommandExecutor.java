package com.particle.workflow.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionAppStructMapping;
import com.particle.workflow.app.execution.executor.WorkflowExecutionDeleteCommandExecutor;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionGateway;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 工作流定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
@Validated
public class WorkflowDefinitionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionGateway workflowDefinitionGateway;
	private IWorkflowDefinitionService iWorkflowDefinitionService;
	private WorkflowDefinitionHistoryDeleteCommandExecutor workflowDefinitionHistoryDeleteCommandExecutor;
	private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;

	private WorkflowExecutionDeleteCommandExecutor workflowExecutionDeleteCommandExecutor;
	private IWorkflowExecutionService iWorkflowExecutionService;


	/**
	 * 执行 工作流定义 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionVO> execute(@Valid CommonIdCommand deleteCommand) {
		WorkflowDefinitionId workflowDefinitionId = WorkflowDefinitionId.of(deleteCommand.getId());
		WorkflowDefinition byId = workflowDefinitionGateway.getById(workflowDefinitionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = workflowDefinitionGateway.delete(workflowDefinitionId,deleteCommand);
		if (delete) {
			// 删除成功后，将历史也删除
			List<WorkflowDefinitionHistoryDO> workflowDefinitionHistoryDOList = iWorkflowDefinitionHistoryService.getByWorkflowDefinitionId(workflowDefinitionId.getId());
			workflowDefinitionHistoryDOList.forEach(workflowDefinitionHistoryDO ->
					workflowDefinitionHistoryDeleteCommandExecutor.execute(CommonIdCommand.create(workflowDefinitionHistoryDO.getId()))
			);
			// 删除成功后，将执行实例也删除
			List<WorkflowExecutionDO> workflowExecutionDOList = iWorkflowExecutionService.getByWorkflowDefinitionId(workflowDefinitionId.getId());
			workflowExecutionDOList.forEach(workflowExecutionDO ->
					workflowExecutionDeleteCommandExecutor.execute(CommonIdCommand.create(workflowExecutionDO.getId()))
			);
			return SingleResponse.of(WorkflowDefinitionAppStructMapping.instance.toWorkflowDefinitionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param workflowDefinitionGateway
	 */
	@Autowired
	public void setWorkflowDefinitionGateway(WorkflowDefinitionGateway workflowDefinitionGateway) {
		this.workflowDefinitionGateway = workflowDefinitionGateway;
	}
	@Autowired
	public void setIWorkflowDefinitionService(IWorkflowDefinitionService iWorkflowDefinitionService) {
		this.iWorkflowDefinitionService = iWorkflowDefinitionService;
	}
	@Autowired
	public void setWorkflowDefinitionHistoryDeleteCommandExecutor(WorkflowDefinitionHistoryDeleteCommandExecutor workflowDefinitionHistoryDeleteCommandExecutor) {
		this.workflowDefinitionHistoryDeleteCommandExecutor = workflowDefinitionHistoryDeleteCommandExecutor;
	}
	@Autowired
	public void setIWorkflowDefinitionHistoryService(IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService) {
		this.iWorkflowDefinitionHistoryService = iWorkflowDefinitionHistoryService;
	}
	@Autowired
	public void setWorkflowExecutionDeleteCommandExecutor(WorkflowExecutionDeleteCommandExecutor workflowExecutionDeleteCommandExecutor) {
		this.workflowExecutionDeleteCommandExecutor = workflowExecutionDeleteCommandExecutor;
	}
	@Autowired
	public void setIWorkflowExecutionService(IWorkflowExecutionService iWorkflowExecutionService) {
		this.iWorkflowExecutionService = iWorkflowExecutionService;
	}
}
