package com.particle.workflow.app.execution.executor;

import com.particle.workflow.app.execution.structmapping.WorkflowExecutionAppStructMapping;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionGateway;
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
 * 工作流执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Component
@Validated
public class WorkflowExecutionCreateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionGateway workflowExecutionGateway;

	/**
	 * 执行工作流执行实例添加指令
	 * @param workflowExecutionCreateCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionVO> execute(@Valid WorkflowExecutionCreateCommand workflowExecutionCreateCommand) {
		WorkflowExecution workflowExecution = createByWorkflowExecutionCreateCommand(workflowExecutionCreateCommand);
		workflowExecution.setAddControl(workflowExecutionCreateCommand);
		boolean save = workflowExecutionGateway.save(workflowExecution);
		if (save) {
			return SingleResponse.of(WorkflowExecutionAppStructMapping.instance.toWorkflowExecutionVO(workflowExecution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流执行实例创建指令创建工作流执行实例模型
	 * @param workflowExecutionCreateCommand
	 * @return
	 */
	private WorkflowExecution createByWorkflowExecutionCreateCommand(WorkflowExecutionCreateCommand workflowExecutionCreateCommand){
		WorkflowExecution workflowExecution = WorkflowExecution.create();
		WorkflowExecutionCreateCommandToWorkflowExecutionMapping.instance.fillWorkflowExecutionByWorkflowExecutionCreateCommand(workflowExecution, workflowExecutionCreateCommand);
		return workflowExecution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowExecutionCreateCommandToWorkflowExecutionMapping{
		WorkflowExecutionCreateCommandToWorkflowExecutionMapping instance = Mappers.getMapper( WorkflowExecutionCreateCommandToWorkflowExecutionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowExecution
		 * @param workflowExecutionCreateCommand
		 */
		void fillWorkflowExecutionByWorkflowExecutionCreateCommand(@MappingTarget WorkflowExecution workflowExecution, WorkflowExecutionCreateCommand workflowExecutionCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowExecutionGateway
	 */
	@Autowired
	public void setWorkflowExecutionGateway(WorkflowExecutionGateway workflowExecutionGateway) {
		this.workflowExecutionGateway = workflowExecutionGateway;
	}
}
