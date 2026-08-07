package com.particle.workflow.app.definition.executor;

import com.particle.workflow.app.definition.structmapping.WorkflowProjectAppStructMapping;
import com.particle.workflow.client.definition.dto.command.WorkflowProjectUpdateCommand;
import com.particle.workflow.client.definition.dto.data.WorkflowProjectVO;
import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.WorkflowProjectId;
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
 * 工作流项目 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class WorkflowProjectUpdateCommandExecutor  extends AbstractBaseExecutor {

	private WorkflowProjectGateway workflowProjectGateway;

	/**
	 * 执行 工作流项目 更新指令
	 * @param workflowProjectUpdateCommand
	 * @return
	 */
	public SingleResponse<WorkflowProjectVO> execute(@Valid WorkflowProjectUpdateCommand workflowProjectUpdateCommand) {
		WorkflowProject workflowProject = createByWorkflowProjectUpdateCommand(workflowProjectUpdateCommand);
		workflowProject.setUpdateControl(workflowProjectUpdateCommand);
		boolean save = workflowProjectGateway.save(workflowProject);
		if (save) {
			return SingleResponse.of(WorkflowProjectAppStructMapping.instance.toWorkflowProjectVO(workflowProject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据工作流项目更新指令创建工作流项目模型
	 * @param workflowProjectUpdateCommand
	 * @return
	 */
	private WorkflowProject createByWorkflowProjectUpdateCommand(WorkflowProjectUpdateCommand workflowProjectUpdateCommand){
		WorkflowProject workflowProject = WorkflowProject.create();
		WorkflowProjectUpdateCommandToWorkflowProjectMapping.instance.fillWorkflowProjectByWorkflowProjectUpdateCommand(workflowProject, workflowProjectUpdateCommand);
		return workflowProject;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface WorkflowProjectUpdateCommandToWorkflowProjectMapping{
		WorkflowProjectUpdateCommandToWorkflowProjectMapping instance = Mappers.getMapper(WorkflowProjectUpdateCommandToWorkflowProjectMapping.class );

		default WorkflowProjectId map(Long id){
			if (id == null) {
				return null;
			}
			return WorkflowProjectId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param workflowProject
		 * @param workflowProjectUpdateCommand
		 */
		void fillWorkflowProjectByWorkflowProjectUpdateCommand(@MappingTarget WorkflowProject workflowProject, WorkflowProjectUpdateCommand workflowProjectUpdateCommand);
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
