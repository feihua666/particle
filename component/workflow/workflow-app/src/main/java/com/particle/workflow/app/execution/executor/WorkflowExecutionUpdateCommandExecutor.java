package com.particle.workflow.app.execution.executor;

import com.particle.workflow.app.execution.structmapping.WorkflowExecutionAppStructMapping;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionUpdateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionVO;
import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
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
 * 工作流执行实例 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class WorkflowExecutionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionGateway workflowExecutionGateway;

	/**
	 * 执行 工作流执行实例 更新指令
	 * @param workflowExecutionUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionVO> execute(@Valid WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand) {
		WorkflowExecution workflowExecution = createByWorkflowExecutionUpdateCommand(workflowExecutionUpdateCommand);
		workflowExecution.setUpdateControl(workflowExecutionUpdateCommand);
		boolean save = workflowExecutionGateway.save(workflowExecution);
		if (save) {
			return SingleResponse.of(WorkflowExecutionAppStructMapping.instance.toWorkflowExecutionVO(workflowExecution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流执行实例更新指令创建工作流执行实例模型
	 * @param workflowExecutionUpdateCommand
	 * @return
	 */
	private WorkflowExecution createByWorkflowExecutionUpdateCommand(WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand){
		WorkflowExecution workflowExecution = WorkflowExecution.create();
		WorkflowExecutionUpdateCommandToWorkflowExecutionMapping.instance.fillWorkflowExecutionByWorkflowExecutionUpdateCommand(workflowExecution, workflowExecutionUpdateCommand);
		return workflowExecution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface WorkflowExecutionUpdateCommandToWorkflowExecutionMapping{
		WorkflowExecutionUpdateCommandToWorkflowExecutionMapping instance = Mappers.getMapper(WorkflowExecutionUpdateCommandToWorkflowExecutionMapping.class );

		default WorkflowExecutionId map(Long id){
			if (id == null) {
				return null;
			}
			return WorkflowExecutionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowExecution
		 * @param workflowExecutionUpdateCommand
		 */
		void fillWorkflowExecutionByWorkflowExecutionUpdateCommand(@MappingTarget WorkflowExecution workflowExecution, WorkflowExecutionUpdateCommand workflowExecutionUpdateCommand);
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
