package com.particle.workflow.app.definition.executor;

import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流定义 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
@Validated
public class WorkflowDefinitionCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionGateway workflowDefinitionGateway;
	private IWorkflowDefinitionService iWorkflowDefinitionService;
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
}
