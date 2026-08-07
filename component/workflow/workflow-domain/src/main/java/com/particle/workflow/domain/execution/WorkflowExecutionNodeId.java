package com.particle.workflow.domain.execution;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 工作流节点执行实例 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
public class WorkflowExecutionNodeId extends Id {

	public WorkflowExecutionNodeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 工作流节点执行实例 领域模型id
	 * @param id
	 * @return
	 */
	public static WorkflowExecutionNodeId of(Long id){
		return new WorkflowExecutionNodeId(id);
	}
}
