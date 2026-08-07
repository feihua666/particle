package com.particle.workflow.app.definition.executor;

import com.particle.global.exception.Assert;
import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionHistoryAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryUpdateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateControlCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionGateway;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionHistoryGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 工作流定义历史 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class WorkflowDefinitionHistoryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionHistoryGateway workflowDefinitionHistoryGateway;
	private WorkflowDefinitionGateway workflowDefinitionGateway;

	/**
	 * 执行 工作流定义历史 更新指令
	 * @param workflowDefinitionHistoryUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionHistoryVO> execute(@Valid WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand) {
		WorkflowDefinitionHistory workflowDefinitionHistorySaved = workflowDefinitionHistoryGateway.getById(WorkflowDefinitionHistoryId.of(workflowDefinitionHistoryUpdateCommand.getId()));
		Assert.isFalse(workflowDefinitionHistorySaved.getIsPublish(),"工作流定义历史已发布，不能修改");
		WorkflowDefinitionHistory workflowDefinitionHistory = createByWorkflowDefinitionHistoryUpdateCommand(workflowDefinitionHistoryUpdateCommand);
		workflowDefinitionHistory.setUpdateControl(workflowDefinitionHistoryUpdateCommand);
		boolean save = workflowDefinitionHistoryGateway.save(workflowDefinitionHistory);
		if (save) {
			// 如果更新为发布，则需要将工作流定义对应的已发布字段设置
			WorkflowDefinition workflowDefinition = workflowDefinitionGateway.getById(WorkflowDefinitionId.of(workflowDefinitionHistory.getWorkflowDefinitionId()));
			if (workflowDefinitionHistoryUpdateCommand.getIsPublish() != null && workflowDefinitionHistoryUpdateCommand.getIsPublish()
					&& workflowDefinitionHistoryUpdateCommand.getId().equals(workflowDefinition.getDraftWorkflowDefinitionHistoryId())) {
				workflowDefinition.changeLatestPublishWorkflowDefinitionHistoryId(workflowDefinitionHistoryUpdateCommand.getId());
				workflowDefinition.changeDraftWorkflowDefinitionHistoryId(null);

				// 一个更新控制对象，主要是更新时将草稿更新为null，因为草稿已经是发布状态了
				WorkflowDefinitionUpdateControlCommand workflowDefinitionUpdateControlCommand = new WorkflowDefinitionUpdateControlCommand();
				workflowDefinitionUpdateControlCommand.setLatestPublishWorkflowDefinitionHistoryId(workflowDefinition.getLatestPublishWorkflowDefinitionHistoryId());
				workflowDefinitionUpdateControlCommand.setDraftWorkflowDefinitionHistoryId(null);

				workflowDefinition.setUpdateControl(workflowDefinitionUpdateControlCommand);
				workflowDefinitionGateway.save(workflowDefinition);
			}
			return SingleResponse.of(WorkflowDefinitionHistoryAppStructMapping.instance.toWorkflowDefinitionHistoryVO(workflowDefinitionHistory));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流定义历史更新指令创建工作流定义历史模型
	 * @param workflowDefinitionHistoryUpdateCommand
	 * @return
	 */
	private WorkflowDefinitionHistory createByWorkflowDefinitionHistoryUpdateCommand(WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand){
		WorkflowDefinitionHistory workflowDefinitionHistory = WorkflowDefinitionHistory.create();
		WorkflowDefinitionHistoryUpdateCommandToWorkflowDefinitionHistoryMapping.instance.fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryUpdateCommand(workflowDefinitionHistory, workflowDefinitionHistoryUpdateCommand);
		return workflowDefinitionHistory;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface WorkflowDefinitionHistoryUpdateCommandToWorkflowDefinitionHistoryMapping{
		WorkflowDefinitionHistoryUpdateCommandToWorkflowDefinitionHistoryMapping instance = Mappers.getMapper(WorkflowDefinitionHistoryUpdateCommandToWorkflowDefinitionHistoryMapping.class );

		default WorkflowDefinitionHistoryId map(Long id){
			if (id == null) {
				return null;
			}
			return WorkflowDefinitionHistoryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowDefinitionHistory
		 * @param workflowDefinitionHistoryUpdateCommand
		 */
		void fillWorkflowDefinitionHistoryByWorkflowDefinitionHistoryUpdateCommand(@MappingTarget WorkflowDefinitionHistory workflowDefinitionHistory, WorkflowDefinitionHistoryUpdateCommand workflowDefinitionHistoryUpdateCommand);
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
	public void setWorkflowDefinitionGateway(WorkflowDefinitionGateway workflowDefinitionGateway) {
		this.workflowDefinitionGateway = workflowDefinitionGateway;
	}
}
