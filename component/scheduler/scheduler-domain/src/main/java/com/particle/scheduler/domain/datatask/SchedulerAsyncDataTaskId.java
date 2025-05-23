package com.particle.scheduler.domain.datatask;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划异步任务数据 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
public class SchedulerAsyncDataTaskId extends Id {

	public SchedulerAsyncDataTaskId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划异步任务数据 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerAsyncDataTaskId of(Long id){
		return new SchedulerAsyncDataTaskId(id);
	}
}
