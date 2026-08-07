package com.particle.workflow.app.execution.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.workflow.app.execution.structmapping.WorkflowExecutionNodeAppStructMapping;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.WorkflowExecutionNodeId;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionNodeGateway;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 工作流节点执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
@Validated
public class WorkflowExecutionNodeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionNodeGateway workflowExecutionNodeGateway;
	private IWorkflowExecutionNodeService iWorkflowExecutionNodeService;

	/**
	 * 执行 工作流节点执行实例 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionNodeVO> execute(@Valid CommonIdCommand deleteCommand) {
		WorkflowExecutionNodeId workflowExecutionNodeId = WorkflowExecutionNodeId.of(deleteCommand.getId());
		WorkflowExecutionNode byId = workflowExecutionNodeGateway.getById(workflowExecutionNodeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = workflowExecutionNodeGateway.delete(workflowExecutionNodeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(WorkflowExecutionNodeAppStructMapping.instance.toWorkflowExecutionNodeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param workflowExecutionNodeGateway
	 */
	@Autowired
	public void setWorkflowExecutionNodeGateway(WorkflowExecutionNodeGateway workflowExecutionNodeGateway) {
		this.workflowExecutionNodeGateway = workflowExecutionNodeGateway;
	}
	@Autowired
	public void setIWorkflowExecutionNodeService(IWorkflowExecutionNodeService iWorkflowExecutionNodeService) {
		this.iWorkflowExecutionNodeService = iWorkflowExecutionNodeService;
	}
}
