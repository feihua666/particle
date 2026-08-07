package com.particle.workflow.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.workflow.app.execution.structmapping.WorkflowExecutionAppStructMapping;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionGateway;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 工作流执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
@Validated
public class WorkflowExecutionDeleteCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionGateway workflowExecutionGateway;
	private IWorkflowExecutionService iWorkflowExecutionService;
	private WorkflowExecutionNodeDeleteCommandExecutor workflowExecutionNodeDeleteCommandExecutor;
	private IWorkflowExecutionNodeService iWorkflowExecutionNodeService;

	/**
	 * 执行 工作流执行实例 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionVO> execute(@Valid CommonIdCommand deleteCommand) {
		WorkflowExecutionId workflowExecutionId = WorkflowExecutionId.of(deleteCommand.getId());
		WorkflowExecution byId = workflowExecutionGateway.getById(workflowExecutionId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = workflowExecutionGateway.delete(workflowExecutionId,deleteCommand);
		if (delete) {
			// 删除成功后，将执行节点实例删除
			List<WorkflowExecutionNodeDO> workflowExecutionNodeDOList = iWorkflowExecutionNodeService.getByWorkflowExecutionId(workflowExecutionId.getId());
			workflowExecutionNodeDOList.forEach(workflowExecutionNodeDO -> {
				workflowExecutionNodeDeleteCommandExecutor.execute(CommonIdCommand.create(workflowExecutionNodeDO.getId()));
			});
			return SingleResponse.of(WorkflowExecutionAppStructMapping.instance.toWorkflowExecutionVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param workflowExecutionGateway
	 */
	@Autowired
	public void setWorkflowExecutionGateway(WorkflowExecutionGateway workflowExecutionGateway) {
		this.workflowExecutionGateway = workflowExecutionGateway;
	}
	@Autowired
	public void setIWorkflowExecutionService(IWorkflowExecutionService iWorkflowExecutionService) {
		this.iWorkflowExecutionService = iWorkflowExecutionService;
	}
	@Autowired
	public void setWorkflowExecutionNodeDeleteCommandExecutor(WorkflowExecutionNodeDeleteCommandExecutor workflowExecutionNodeDeleteCommandExecutor) {
		this.workflowExecutionNodeDeleteCommandExecutor = workflowExecutionNodeDeleteCommandExecutor;
	}
	@Autowired
	public void setIWorkflowExecutionNodeService(IWorkflowExecutionNodeService iWorkflowExecutionNodeService) {
		this.iWorkflowExecutionNodeService = iWorkflowExecutionNodeService;
	}
}
