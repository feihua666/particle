package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划临时任务运行记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
public class SchedulerTempTaskRunRecordId extends Id {

	public SchedulerTempTaskRunRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划临时任务运行记录 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerTempTaskRunRecordId of(Long id){
		return new SchedulerTempTaskRunRecordId(id);
	}
}
