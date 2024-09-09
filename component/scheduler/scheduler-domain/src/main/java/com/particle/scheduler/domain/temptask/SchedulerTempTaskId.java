package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划临时任务 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
public class SchedulerTempTaskId extends Id {

	public SchedulerTempTaskId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划临时任务 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerTempTaskId of(Long id){
		return new SchedulerTempTaskId(id);
	}
}
