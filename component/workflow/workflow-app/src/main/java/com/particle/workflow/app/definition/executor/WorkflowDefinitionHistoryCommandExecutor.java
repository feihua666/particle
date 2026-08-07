package com.particle.workflow.app.definition.executor;

import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionHistoryGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流定义历史 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
@Validated
public class WorkflowDefinitionHistoryCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway;
	private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;
	/**
	 * 注入使用set方法
	 * @param workflowDefinitionHistoryGateway
	 */
	@Autowired
	public void setWorkflowDefinitionHistoryGateway(WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway) {
		this.workflowDefinitionHistoryGateway = workflowDefinitionHistoryGateway;
	}
	@Autowired
	public void setIWorkflowDefinitionHistoryService(IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService) {
		this.iWorkflowDefinitionHistoryService = iWorkflowDefinitionHistoryService;
	}
}
