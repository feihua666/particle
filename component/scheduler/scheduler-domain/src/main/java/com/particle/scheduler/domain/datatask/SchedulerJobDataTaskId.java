package com.particle.scheduler.domain.datatask;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划任务数据 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
public class SchedulerJobDataTaskId extends Id {

	public SchedulerJobDataTaskId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划任务数据 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerJobDataTaskId of(Long id){
		return new SchedulerJobDataTaskId(id);
	}
}
