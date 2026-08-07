package com.particle.workflow.app.definition.executor;

import com.particle.workflow.app.definition.structmapping.WorkflowProjectAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectCreateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.gateway.WorkflowProjectGateway;
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
 * 工作流项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Component
@Validated
public class WorkflowProjectCreateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowProjectGateway workflowProjectGateway;

	/**
	 * 执行工作流项目添加指令
	 * @param workflowProjectCreateCommand
	 * @return
	 */
	public SingleResponse<WorkflowProjectVO> execute(@Valid WorkflowProjectCreateCommand workflowProjectCreateCommand) {
		WorkflowProject workflowProject = createByWorkflowProjectCreateCommand(workflowProjectCreateCommand);
		workflowProject.setAddControl(workflowProjectCreateCommand);

		workflowProject.changeUserId(workflowProjectCreateCommand.getLoginUserId());
		workflowProject.initForAdd();
		boolean save = workflowProjectGateway.save(workflowProject);
		if (save) {
			return SingleResponse.of(WorkflowProjectAppStructMapping.instance.toWorkflowProjectVO(workflowProject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流项目创建指令创建工作流项目模型
	 * @param workflowProjectCreateCommand
	 * @return
	 */
	private WorkflowProject createByWorkflowProjectCreateCommand(WorkflowProjectCreateCommand workflowProjectCreateCommand){
		WorkflowProject workflowProject = WorkflowProject.create();
		WorkflowProjectCreateCommandToWorkflowProjectMapping.instance.fillWorkflowProjectByWorkflowProjectCreateCommand(workflowProject, workflowProjectCreateCommand);
		return workflowProject;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  WorkflowProjectCreateCommandToWorkflowProjectMapping{
		WorkflowProjectCreateCommandToWorkflowProjectMapping instance = Mappers.getMapper( WorkflowProjectCreateCommandToWorkflowProjectMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowProject
		 * @param workflowProjectCreateCommand
		 */
		void fillWorkflowProjectByWorkflowProjectCreateCommand(@MappingTarget WorkflowProject workflowProject, WorkflowProjectCreateCommand workflowProjectCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param workflowProjectGateway
	 */
	@Autowired
	public void setWorkflowProjectGateway(WorkflowProjectGateway workflowProjectGateway) {
		this.workflowProjectGateway = workflowProjectGateway;
	}
}
