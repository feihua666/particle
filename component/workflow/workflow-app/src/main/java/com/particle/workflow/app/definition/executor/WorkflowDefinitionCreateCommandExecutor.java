package com.particle.workflow.app.definition.executor;

import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionCreateCommand;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionHistoryCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionHistoryVO;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.gateway.WorkflowDefinitionGateway;
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
 * 工作流定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Component
@Validated
public class WorkflowDefinitionCreateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionGateway workflowDefinitionGateway;
	private WorkflowDefinitionHistoryCreateCommandExecutor workflowDefinitionHistoryCreateCommandExecutor;

	/**
	 * 执行工作流定义添加指令
	 * @param workflowDefinitionCreateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionVO> execute(@Valid WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand) {
		WorkflowDefinition workflowDefinition = createByWorkflowDefinitionCreateCommand(workflowDefinitionCreateCommand);
		workflowDefinition.setAddControl(workflowDefinitionCreateCommand);
		boolean save = workflowDefinitionGateway.save(workflowDefinition);
		if (save) {
			// 添加后创建历史记录
			WorkflowDefinitionHistoryCreateCommand workflowDefinitionHistoryCreateCommand =
					WorkflowDefinitionHistoryCreateCommand.create(workflowDefinition.getId().getId(), null, null);
			// 刚添加的是草稿
			SingleResponse<WorkflowDefinitionHistoryVO> workflowDefinitionHistoryVOSingleResponse =
					workflowDefinitionHistoryCreateCommandExecutor.execute(workflowDefinitionHistoryCreateCommand);
			// 再更新一下草稿版本
			workflowDefinition.changeDraftWorkflowDefinitionHistoryId(workflowDefinitionHistoryVOSingleResponse.getData().getId());
			workflowDefinitionGateway.save(workflowDefinition);
			// 获取一下最新的数据，因为再更新，数据版本会变，这时获取一下保证数据最新
			workflowDefinition = workflowDefinitionGateway.getById(workflowDefinition.getId());
			return SingleResponse.of(WorkflowDefinitionAppStructMapping.instance.toWorkflowDefinitionVO(workflowDefinition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流定义创建指令创建工作流定义模型
	 * @param workflowDefinitionCreateCommand
	 * @return
	 */
	private WorkflowDefinition createByWorkflowDefinitionCreateCommand(WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand){
		WorkflowDefinition workflowDefinition = WorkflowDefinition.create();
		WorkflowDefinitionCreateCommandToWorkflowDefinitionMapping.instance.fillWorkflowDefinitionByWorkflowDefinitionCreateCommand(workflowDefinition, workflowDefinitionCreateCommand);
		return workflowDefinition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowDefinitionCreateCommandToWorkflowDefinitionMapping{
		WorkflowDefinitionCreateCommandToWorkflowDefinitionMapping instance = Mappers.getMapper( WorkflowDefinitionCreateCommandToWorkflowDefinitionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowDefinition
		 * @param workflowDefinitionCreateCommand
		 */
		void fillWorkflowDefinitionByWorkflowDefinitionCreateCommand(@MappingTarget WorkflowDefinition workflowDefinition, WorkflowDefinitionCreateCommand workflowDefinitionCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowDefinitionGateway
	 */
	@Autowired
	public void setWorkflowDefinitionGateway(WorkflowDefinitionGateway workflowDefinitionGateway) {
		this.workflowDefinitionGateway = workflowDefinitionGateway;
	}
	@Autowired
	public void setWorkflowDefinitionHistoryCreateCommandExecutor(WorkflowDefinitionHistoryCreateCommandExecutor workflowDefinitionHistoryCreateCommandExecutor) {
		this.workflowDefinitionHistoryCreateCommandExecutor = workflowDefinitionHistoryCreateCommandExecutor;
	}
}
