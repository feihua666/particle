package com.particle.workflow.app.definition.executor;

import com.particle.workflow.app.definition.structmapping.WorkflowDefinitionAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowDefinitionUpdateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowDefinitionVO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
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
 * 工作流定义 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class WorkflowDefinitionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowDefinitionGateway workflowDefinitionGateway;

	/**
	 * 执行 工作流定义 更新指令
	 * @param workflowDefinitionUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowDefinitionVO> execute(@Valid WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand) {
		WorkflowDefinition workflowDefinition = createByWorkflowDefinitionUpdateCommand(workflowDefinitionUpdateCommand);
		workflowDefinition.setUpdateControl(workflowDefinitionUpdateCommand);
		boolean save = workflowDefinitionGateway.save(workflowDefinition);
		if (save) {
			return SingleResponse.of(WorkflowDefinitionAppStructMapping.instance.toWorkflowDefinitionVO(workflowDefinition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流定义更新指令创建工作流定义模型
	 * @param workflowDefinitionUpdateCommand
	 * @return
	 */
	private WorkflowDefinition createByWorkflowDefinitionUpdateCommand(WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand){
		WorkflowDefinition workflowDefinition = WorkflowDefinition.create();
		WorkflowDefinitionUpdateCommandToWorkflowDefinitionMapping.instance.fillWorkflowDefinitionByWorkflowDefinitionUpdateCommand(workflowDefinition, workflowDefinitionUpdateCommand);
		return workflowDefinition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface WorkflowDefinitionUpdateCommandToWorkflowDefinitionMapping{
		WorkflowDefinitionUpdateCommandToWorkflowDefinitionMapping instance = Mappers.getMapper(WorkflowDefinitionUpdateCommandToWorkflowDefinitionMapping.class );

		default WorkflowDefinitionId map(Long id){
			if (id == null) {
				return null;
			}
			return WorkflowDefinitionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowDefinition
		 * @param workflowDefinitionUpdateCommand
		 */
		void fillWorkflowDefinitionByWorkflowDefinitionUpdateCommand(@MappingTarget WorkflowDefinition workflowDefinition, WorkflowDefinitionUpdateCommand workflowDefinitionUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowDefinitionGateway
	 */
	@Autowired
	public void setWorkflowDefinitionGateway(WorkflowDefinitionGateway workflowDefinitionGateway) {
		this.workflowDefinitionGateway = workflowDefinitionGateway;
	}
}
