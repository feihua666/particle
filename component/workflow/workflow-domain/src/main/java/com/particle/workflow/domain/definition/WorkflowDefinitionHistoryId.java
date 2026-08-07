package com.particle.workflow.domain.definition;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 工作流定义历史 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
public class WorkflowDefinitionHistoryId extends Id {

	public WorkflowDefinitionHistoryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 工作流定义历史 领域模型id
	 * @param id
	 * @return
	 */
	public static WorkflowDefinitionHistoryId of(Long id){
		return new WorkflowDefinitionHistoryId(id);
	}
}
