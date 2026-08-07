package com.particle.workflow.infrastructure.execution.structmapping;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.workflow.domain.execution.WorkflowExecution;
import com.particle.workflow.domain.execution.WorkflowExecutionId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 工作流执行实例 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowExecutionInfrastructureStructMapping {
	public static WorkflowExecutionInfrastructureStructMapping instance = Mappers.getMapper( WorkflowExecutionInfrastructureStructMapping.class );

	protected WorkflowExecutionId map(Long id){
		if (id == null) {
			return null;
		}
		return WorkflowExecutionId.of(id);
	}
	protected Long map(WorkflowExecutionId workflowExecutionId){
		if (workflowExecutionId == null) {
			return null;
		}
		return workflowExecutionId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionInfrastructureStructMapping#map(java.lang.Long)}
	 * @param workflowExecutionDO
	 * @return
	 */
	public abstract WorkflowExecution workflowExecutionDOToWorkflowExecution(@MappingTarget WorkflowExecution workflowExecution,WorkflowExecutionDO workflowExecutionDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionInfrastructureStructMapping#map(WorkflowExecutionId)}
	 * @param workflowExecution
	 * @return
	 */
	public abstract WorkflowExecutionDO workflowExecutionToWorkflowExecutionDO(WorkflowExecution workflowExecution);

}
