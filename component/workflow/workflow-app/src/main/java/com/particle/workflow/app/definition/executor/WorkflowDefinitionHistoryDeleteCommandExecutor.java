package com.particle.workflow.app.definition.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionHistoryAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateControlCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionHistoryGateway;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionHistoryService;
import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.infrastructure.definition.service.IWorkflowDefinitionService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 工作流定义历史 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Component
@Validated
public class WorkflowDefinitionHistoryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway;
	private IWorkflowDefinitionHistoryService iWorkflowDefinitionHistoryService;

	private IWorkflowDefinitionService iWorkflowDefinitionService;

	/**
	 * 执行 工作流定义历史 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> execute(@Valid CommonIdCommand deleteCommand) {
		WorkflowDefinitionHistoryId workflowDefinitionHistoryId = WorkflowDefinitionHistoryId.of(deleteCommand.getId());
		WorkflowDefinitionHistory byId = workflowDefinitionHistoryGateway.getById(workflowDefinitionHistoryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = workflowDefinitionHistoryGateway.delete(workflowDefinitionHistoryId,deleteCommand);
		if (delete) {
			// 删除后，确认一下流程定义里面是否还引用了该数据，如果引用了，设置为空
			WorkflowDefinitionDO workflowDefinitionDO = iWorkflowDefinitionService.getById(byId.getWorkflowDefinitionId());

			if (workflowDefinitionDO != null) {
				boolean needUpdate = false;
				WorkflowDefinitionUpdateControlCommand workflowDefinitionUpdateCommand = new WorkflowDefinitionUpdateControlCommand();
				workflowDefinitionUpdateCommand.setId(workflowDefinitionDO.getId());
				workflowDefinitionUpdateCommand.setVersion(workflowDefinitionDO.getVersion());
				workflowDefinitionUpdateCommand.setLatestPublishWorkflowDefinitionHistoryId(workflowDefinitionDO.getLatestPublishWorkflowDefinitionHistoryId());
				workflowDefinitionUpdateCommand.setDraftWorkflowDefinitionHistoryId(workflowDefinitionDO.getDraftWorkflowDefinitionHistoryId());

				if (deleteCommand.getId().equals(workflowDefinitionDO.getLatestPublishWorkflowDefinitionHistoryId())) {
					workflowDefinitionUpdateCommand.setLatestPublishWorkflowDefinitionHistoryId(null);
					needUpdate = true;
				}
				if (deleteCommand.getId().equals(workflowDefinitionDO.getDraftWorkflowDefinitionHistoryId())) {
					workflowDefinitionUpdateCommand.setDraftWorkflowDefinitionHistoryId(null);
					needUpdate = true;
				}
				if (needUpdate) {
					WorkflowDefinitionDO workflowDefinitionDOForUpdate = new WorkflowDefinitionDO();
					workflowDefinitionDOForUpdate.setId(workflowDefinitionUpdateCommand.getId());
					workflowDefinitionDOForUpdate.setUpdateControl(workflowDefinitionUpdateCommand);
					workflowDefinitionDOForUpdate.setVersion(workflowDefinitionUpdateCommand.getVersion());
					workflowDefinitionDOForUpdate.setLatestPublishWorkflowDefinitionHistoryId(workflowDefinitionUpdateCommand.getLatestPublishWorkflowDefinitionHistoryId());
					workflowDefinitionDOForUpdate.setDraftWorkflowDefinitionHistoryId(workflowDefinitionUpdateCommand.getDraftWorkflowDefinitionHistoryId());
					iWorkflowDefinitionService.update(workflowDefinitionDOForUpdate);
				}
			}
			return SingleResponse.of(WorkflowDefinitionHistoryAppStructMapping.instance.toWorkflowDefinitionHistoryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

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
	@Autowired
	public void setIWorkflowDefinitionService(IWorkflowDefinitionService iWorkflowDefinitionService) {
		this.iWorkflowDefinitionService = iWorkflowDefinitionService;
	}
}
