package com.particle.workflow.infrastructure.definition.structmapping;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistory;
import com.particle.workflow.domain.definition.WorkflowDefinitionHistoryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 工作流定义历史 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowDefinitionHistoryInfrastructureStructMapping {
	public static WorkflowDefinitionHistoryInfrastructureStructMapping instance = Mappers.getMapper( WorkflowDefinitionHistoryInfrastructureStructMapping.class );

	protected WorkflowDefinitionHistoryId map(Long id){
		if (id == null) {
			return null;
		}
		return WorkflowDefinitionHistoryId.of(id);
	}
	protected Long map(WorkflowDefinitionHistoryId workflowDefinitionHistoryId){
		if (workflowDefinitionHistoryId == null) {
			return null;
		}
		return workflowDefinitionHistoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionHistoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param workflowDefinitionHistoryDO
	 * @return
	 */
	public abstract WorkflowDefinitionHistory workflowDefinitionHistoryDOToWorkflowDefinitionHistory(@MappingTarget WorkflowDefinitionHistory workflowDefinitionHistory,WorkflowDefinitionHistoryDO workflowDefinitionHistoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowDefinitionHistoryInfrastructureStructMapping#map(WorkflowDefinitionHistoryId)}
	 * @param workflowDefinitionHistory
	 * @return
	 */
	public abstract WorkflowDefinitionHistoryDO workflowDefinitionHistoryToWorkflowDefinitionHistoryDO(WorkflowDefinitionHistory workflowDefinitionHistory);

}
