package com.particle.workflow.app.definition.executor;

import com.particle.workflow.domain.definition.gateway.WorkflowProjectGateway;
import com.particle.workflow.infrastructure.definition.service.IWorkflowProjectService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流项目 指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
@Validated
public class WorkflowProjectCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowProjectGateway workflowProjectGateway;
	private IWorkflowProjectService iWorkflowProjectService;
	/**
	 * 注入使用set方法
	 * @param workflowProjectGateway
	 */
	@Autowired
	public void setWorkflowProjectGateway(WorkflowProjectGateway workflowProjectGateway) {
		this.workflowProjectGateway = workflowProjectGateway;
	}
	@Autowired
	public void setIWorkflowProjectService(IWorkflowProjectService iWorkflowProjectService) {
		this.iWorkflowProjectService = iWorkflowProjectService;
	}
}
