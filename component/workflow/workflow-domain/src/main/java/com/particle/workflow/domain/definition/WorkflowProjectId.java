package com.particle.workflow.domain.definition;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 工作流项目 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
public class WorkflowProjectId extends Id {

	public WorkflowProjectId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 工作流项目 领域模型id
	 * @param id
	 * @return
	 */
	public static WorkflowProjectId of(Long id){
		return new WorkflowProjectId(id);
	}
}
