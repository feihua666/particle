package com.particle.workflow.infrastructure.definition.structmapping;

import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.workflow.domain.definition.WorkflowProject;
import com.particle.workflow.domain.definition.WorkflowProjectId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 工作流项目 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowProjectInfrastructureStructMapping {
	public static WorkflowProjectInfrastructureStructMapping instance = Mappers.getMapper( WorkflowProjectInfrastructureStructMapping.class );

	protected WorkflowProjectId map(Long id){
		if (id == null) {
			return null;
		}
		return WorkflowProjectId.of(id);
	}
	protected Long map(WorkflowProjectId workflowProjectId){
		if (workflowProjectId == null) {
			return null;
		}
		return workflowProjectId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowProjectInfrastructureStructMapping#map(java.lang.Long)}
	 * @param workflowProjectDO
	 * @return
	 */
	public abstract WorkflowProject workflowProjectDOToWorkflowProject(@MappingTarget WorkflowProject workflowProject,WorkflowProjectDO workflowProjectDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowProjectInfrastructureStructMapping#map(WorkflowProjectId)}
	 * @param workflowProject
	 * @return
	 */
	public abstract WorkflowProjectDO workflowProjectToWorkflowProjectDO(WorkflowProject workflowProject);

}
