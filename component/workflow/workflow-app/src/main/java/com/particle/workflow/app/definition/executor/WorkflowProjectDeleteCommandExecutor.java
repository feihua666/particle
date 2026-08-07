package com.particle.workflow.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.workflow.app.definition.structmapping.WorkflowProjectAppStructMapping;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.WorkflowProjectId;
import com.particle.workflow.domain.definition.gateway.WorkflowProjectGateway;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import com.particle.workflow.infrastructure.definition.service.IWorkflowProjectService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 工作流项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
@Validated
public class WorkflowProjectDeleteCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowProjectGateway workflowProjectGateway;
	private IWorkflowProjectService iWorkflowProjectService;
	private WorkflowDefinitionDeleteCommandExecutor workflowDefinitionDeleteCommandExecutor;
	private IWorkflowDefinitionService iWorkflowDefinitionService;

	/**
	 * 执行 工作流项目 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<WorkflowProjectVO> execute(@Valid CommonIdCommand deleteCommand) {
		WorkflowProjectId workflowProjectId = WorkflowProjectId.of(deleteCommand.getId());
		WorkflowProject byId = workflowProjectGateway.getById(workflowProjectId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = workflowProjectGateway.delete(workflowProjectId,deleteCommand);
		if (delete) {
			// 删除成功后，将流程定义也删除
			List<WorkflowDefinitionDO> workflowDefinitionDOList = iWorkflowDefinitionService.getByWorkflowProjectId(workflowProjectId.getId());
			workflowDefinitionDOList.forEach(workflowDefinitionDO -> {
				workflowDefinitionDeleteCommandExecutor.execute(CommonIdCommand.create(workflowDefinitionDO.getId()));
			});
			return SingleResponse.of(WorkflowProjectAppStructMapping.instance.toWorkflowProjectVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
	@Autowired
	public void setWorkflowDefinitionDeleteCommandExecutor(WorkflowDefinitionDeleteCommandExecutor workflowDefinitionDeleteCommandExecutor) {
		this.workflowDefinitionDeleteCommandExecutor = workflowDefinitionDeleteCommandExecutor;
	}
	@Autowired
	public void setIWorkflowDefinitionService(IWorkflowDefinitionService iWorkflowDefinitionService) {
		this.iWorkflowDefinitionService = iWorkflowDefinitionService;
	}
}
