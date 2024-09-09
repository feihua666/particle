package com.particle.scheduler.domain.temptask;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 任务计划临时任务运行记录日志 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
public class SchedulerTempTaskRunRecordLogId extends Id {

	public SchedulerTempTaskRunRecordLogId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 任务计划临时任务运行记录日志 领域模型id
	 * @param id
	 * @return
	 */
	public static SchedulerTempTaskRunRecordLogId of(Long id){
		return new SchedulerTempTaskRunRecordLogId(id);
	}
}
