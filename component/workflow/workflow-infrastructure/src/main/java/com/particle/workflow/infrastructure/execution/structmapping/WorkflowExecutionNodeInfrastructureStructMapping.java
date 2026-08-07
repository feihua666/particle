package com.particle.workflow.infrastructure.execution.structmapping;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.workflow.domain.execution.WorkflowExecutionNode;
import com.particle.workflow.domain.execution.WorkflowExecutionNodeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 工作流节点执行实例 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class WorkflowExecutionNodeInfrastructureStructMapping {
	public static WorkflowExecutionNodeInfrastructureStructMapping instance = Mappers.getMapper( WorkflowExecutionNodeInfrastructureStructMapping.class );

	protected WorkflowExecutionNodeId map(Long id){
		if (id == null) {
			return null;
		}
		return WorkflowExecutionNodeId.of(id);
	}
	protected Long map(WorkflowExecutionNodeId workflowExecutionNodeId){
		if (workflowExecutionNodeId == null) {
			return null;
		}
		return workflowExecutionNodeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionNodeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param workflowExecutionNodeDO
	 * @return
	 */
	public abstract WorkflowExecutionNode workflowExecutionNodeDOToWorkflowExecutionNode(@MappingTarget WorkflowExecutionNode workflowExecutionNode,WorkflowExecutionNodeDO workflowExecutionNodeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link WorkflowExecutionNodeInfrastructureStructMapping#map(WorkflowExecutionNodeId)}
	 * @param workflowExecutionNode
	 * @return
	 */
	public abstract WorkflowExecutionNodeDO workflowExecutionNodeToWorkflowExecutionNodeDO(WorkflowExecutionNode workflowExecutionNode);

}
