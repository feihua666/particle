package com.particle.workflow.app.execution.executor;

import com.particle.workflow.domain.execution.gateway.WorkflowExecutionGateway;
import com.particle.workflow.infrastructure.execution.service.IWorkflowExecutionService;
import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流执行实例 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
@Validated
public class WorkflowExecutionCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionGateway workflowExecutionGateway;
	private IWorkflowExecutionService iWorkflowExecutionService;
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
}
