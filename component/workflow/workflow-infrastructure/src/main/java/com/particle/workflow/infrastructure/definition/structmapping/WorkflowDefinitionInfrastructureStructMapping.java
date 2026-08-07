package com.particle.workflow.infrastructure.definition.structmapping;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.workflow.domain.definition.WorkflowDefinition;
import com.particle.workflow.domain.definition.WorkflowDefinitionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 工作流定义 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowDefinitionInfrastructureStructMapping {
	public static WorkflowDefinitionInfrastructureStructMapping instance = Mappers.getMapper( WorkflowDefinitionInfrastructureStructMapping.class );

	protected WorkflowDefinitionId map(Long id){
		if (id == null) {
			return null;
		}
		return WorkflowDefinitionId.of(id);
	}
	protected Long map(WorkflowDefinitionId workflowDefinitionId){
		if (workflowDefinitionId == null) {
			return null;
		}
		return workflowDefinitionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param workflowDefinitionDO
	 * @return
	 */
	public abstract WorkflowDefinition workflowDefinitionDOToWorkflowDefinition(@MappingTarget WorkflowDefinition workflowDefinition,WorkflowDefinitionDO workflowDefinitionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionInfrastructureStructMapping#map(WorkflowDefinitionId)}
	 * @param workflowDefinition
	 * @return
	 */
	public abstract WorkflowDefinitionDO workflowDefinitionToWorkflowDefinitionDO(WorkflowDefinition workflowDefinition);

}
