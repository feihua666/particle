package com.particle.workflow.app.execution.executor;

import com.particle.workflow.domain.execution.gateway.WorkflowExecutionNodeGateway;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionNodeService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流节点执行实例 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
@Validated
public class WorkflowExecutionNodeCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionNodeGateway workflowExecutionNodeGateway;
	private IWorkflowExecutionNodeService iWorkflowExecutionNodeService;
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
