package com.particle.workflow.app.execution.executor;

import com.particle.workflow.app.execution.structmapping.WorkflowExecutionNodeAppStructMapping;
import com.particle.workflow.client.execution.dto.command.WorkflowExecutionNodeCreateCommand;
import com.particle.workflow.client.execution.dto.data.WorkflowExecutionNodeVO;
import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.gateway.WorkflowExecutionNodeGateway;
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
 * 工作流节点执行实例 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Component
@Validated
public class WorkflowExecutionNodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowExecutionNodeGateway workflowExecutionNodeGateway;

	/**
	 * 执行工作流节点执行实例添加指令
	 * @param workflowExecutionNodeCreateCommand
	 * @return
	 */
	public SingleResponse<WorkflowExecutionNodeVO> execute(@Valid WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand) {
		WorkflowExecutionNode workflowExecutionNode = createByWorkflowExecutionNodeCreateCommand(workflowExecutionNodeCreateCommand);
		workflowExecutionNode.setAddControl(workflowExecutionNodeCreateCommand);
		boolean save = workflowExecutionNodeGateway.save(workflowExecutionNode);
		if (save) {
			return SingleResponse.of(WorkflowExecutionNodeAppStructMapping.instance.toWorkflowExecutionNodeVO(workflowExecutionNode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流节点执行实例创建指令创建工作流节点执行实例模型
	 * @param workflowExecutionNodeCreateCommand
	 * @return
	 */
	private WorkflowExecutionNode createByWorkflowExecutionNodeCreateCommand(WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand){
		WorkflowExecutionNode workflowExecutionNode = WorkflowExecutionNode.create();
		WorkflowExecutionNodeCreateCommandToWorkflowExecutionNodeMapping.instance.fillWorkflowExecutionNodeByWorkflowExecutionNodeCreateCommand(workflowExecutionNode, workflowExecutionNodeCreateCommand);
		return workflowExecutionNode;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowExecutionNodeCreateCommandToWorkflowExecutionNodeMapping{
		WorkflowExecutionNodeCreateCommandToWorkflowExecutionNodeMapping instance = Mappers.getMapper( WorkflowExecutionNodeCreateCommandToWorkflowExecutionNodeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowExecutionNode
		 * @param workflowExecutionNodeCreateCommand
		 */
		void fillWorkflowExecutionNodeByWorkflowExecutionNodeCreateCommand(@MappingTarget WorkflowExecutionNode workflowExecutionNode, WorkflowExecutionNodeCreateCommand workflowExecutionNodeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowExecutionNodeGateway
	 */
	@Autowired
	public void setWorkflowExecutionNodeGateway(WorkflowExecutionNodeGateway workflowExecutionNodeGateway) {
		this.workflowExecutionNodeGateway = workflowExecutionNodeGateway;
	}
}
