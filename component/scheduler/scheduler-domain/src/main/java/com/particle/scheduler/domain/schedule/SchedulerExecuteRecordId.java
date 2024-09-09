package com.particle.scheduler.domain.schedule;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划执行记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
public class SchedulerExecuteRecordId extends Id {

	public SchedulerExecuteRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划执行记录 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerExecuteRecordId of(Long id){
		return new SchedulerExecuteRecordId(id);
	}
}
